package org.hyrical.kitpvp.listeners

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.hyrical.kitpvp.sendMessage
import java.util.UUID

object GodAppleListener : Listener {

    val cooldowns = mutableMapOf<UUID, Long>()

    @EventHandler
    fun onConsume(event: PlayerItemConsumeEvent) {
        if (event.item.type != Material.GOLDEN_APPLE) return

        if (event.item.durability != 1.toShort()) return

        if (!cooldowns.containsKey(event.player.uniqueId)) return

        if ((cooldowns[event.player.uniqueId]?.plus((1000 * 60)))!! > System.currentTimeMillis()) {
            event.player sendMessage "&cYou cannot do that for another &e${(cooldowns[event.player.uniqueId]?.plus((1000 * 60))?.minus(System.currentTimeMillis()))?.div(1000)?.toInt()}&c seconds."
            event.isCancelled = true
            return
        }

        cooldowns[event.player.uniqueId] = System.currentTimeMillis()
        event.player sendMessage "&eYou have consumed a enchanted golden apple and are now on cooldown for 1 minute."
    }

    fun isOnCooldown(player: Player): Boolean {
        if ((cooldowns[player.uniqueId]?.plus((1000 * 60)))!! > System.currentTimeMillis()) {
            return true
        }
        return false
    }

    fun getCooldown(player: Player): Int {
        return cooldowns[player.uniqueId]?.toInt()?.div(1000) ?: return 0
    }
}