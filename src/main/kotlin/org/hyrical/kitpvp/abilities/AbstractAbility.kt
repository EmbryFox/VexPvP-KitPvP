package org.hyrical.kitpvp.abilities

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.KitPvP

abstract class AbstractAbility : Listener {

    init {
        Bukkit.getServer().pluginManager.registerEvents(this, KitPvP())
    }

    abstract fun getIdentifier(): String

    abstract fun getItem(): ItemStack

    abstract fun onTrigger(player: Player)

    abstract fun onRightClick(player: Player)

    @EventHandler
    fun onRightClickEvent(event: PlayerInteractEvent) {
        if ((event.action == Action.RIGHT_CLICK_AIR) || (event.action == Action.RIGHT_CLICK_BLOCK)) {
            if (event.item == getItem()) {
                onRightClick(event.player)
            }
        }
    }
}