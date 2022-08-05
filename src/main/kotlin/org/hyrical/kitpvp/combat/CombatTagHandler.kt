package org.hyrical.kitpvp.combat

import me.activated.core.plugin.AquaCoreAPI
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.StringJoiner
import java.util.UUID
import java.util.concurrent.TimeUnit

object CombatTagHandler : Listener {

    val combatTags: MutableMap<UUID, Long> = mutableMapOf()

    @EventHandler(ignoreCancelled = true)
    fun onCombatTag(event: EntityDamageByEntityEvent) {
        if (event.entity !is Player || event.damager !is Player) return

        val victimData = AquaCoreAPI.INSTANCE.getPlayerData(event.entity.uniqueId)
        val attackerData = AquaCoreAPI.INSTANCE.getPlayerData(event.damager.uniqueId)

        if (victimData.isVanished || victimData.isInStaffMode) return
        if (attackerData.isVanished || attackerData.isInStaffMode) return

        combatTags[event.entity.uniqueId] = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20)
        combatTags[event.damager.uniqueId] = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        if (!event.player.isCombatTagged()) return

        event.player.health = 0.0
        combatTags.remove(event.player.uniqueId)
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        combatTags.remove(event.entity.uniqueId)
    }
}

fun Player.isCombatTagged(): Boolean {
    if (CombatTagHandler.combatTags[uniqueId] == null) return false

    if (CombatTagHandler.combatTags[uniqueId]!! < System.currentTimeMillis()) {
        CombatTagHandler.combatTags.remove(uniqueId)
        return false
    }

    return true
}

fun Player.getCombatTagFormatted(): String {
    return ((CombatTagHandler.combatTags[uniqueId]!! - System.currentTimeMillis()) / 1000).toString()
}