package org.hyrical.kitpvp.abilities

import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.sendMessage
import java.util.UUID

abstract class AbstractAbility : Listener {

    val cooldowns: HashMap<UUID, Long> = hashMapOf()

    init {
        Bukkit.getServer().pluginManager.registerEvents(this, KitPvP())
    }

    abstract fun getIdentifier(): String

    abstract fun getItem(): ItemStack

    abstract fun getCooldown(): Long

    abstract fun onRightClick(player: Player)

    @EventHandler
    fun onRightClickEvent(event: PlayerInteractEvent) {
        if ((event.action == Action.RIGHT_CLICK_AIR) || (event.action == Action.RIGHT_CLICK_BLOCK)) {
            if (event.item == getItem()) {
                if (isOnCooldown(event.player)) {
                    event.player sendMessage "&cYou cannot use this for another &l${TimeUtil.formatIntoDetailedString((((cooldowns[event.player.uniqueId]!! + getCooldown()) - System.currentTimeMillis()) / 1000).toInt())}&c."
                    return
                }
                onRightClick(event.player)
                cooldowns[event.player.uniqueId] = System.currentTimeMillis()
            }
        }
    }

    fun isOnCooldown(player: Player): Boolean {
        if (!cooldowns.containsKey(player.uniqueId)) return false

        return cooldowns[player.uniqueId]!! + getCooldown() > System.currentTimeMillis()
    }
}