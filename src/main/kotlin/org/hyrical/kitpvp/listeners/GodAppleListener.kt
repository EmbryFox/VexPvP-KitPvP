package org.hyrical.kitpvp.listeners

import net.evilblock.cubed.util.time.TimeUtil
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
        println(event.item.durability.toString())

        if (event.item.type != Material.GOLDEN_APPLE) return

        if (event.item.durability != 1.toShort()) return

        if (cooldowns.containsKey(event.player.uniqueId)) {
            if ((cooldowns[event.player.uniqueId]?.plus((1000 * 90)))!! > System.currentTimeMillis()) {
                event.player sendMessage "&cYou cannot do that for another &l${TimeUtil.formatIntoDetailedString(
                    getCooldown(event.player)!!)}&c."
                event.isCancelled = true
                return
            }
        }

        cooldowns[event.player.uniqueId] = System.currentTimeMillis()
        event.player sendMessage "&fYou are now on &dGod Apple &fcooldown for &51:00&f."
    }

    fun isOnCooldown(player: Player): Boolean {
        if (!cooldowns.containsKey(player.uniqueId)) return false

        if ((cooldowns[player.uniqueId]?.plus((1000 * 60)))!! > System.currentTimeMillis()) {
            return true
        }
        return false
    }

    fun getCooldown(player: Player): Int? {
        return (cooldowns[player.uniqueId]?.plus((1000 * 60))?.minus(System.currentTimeMillis()))?.div(1000)?.toInt()
    }
}