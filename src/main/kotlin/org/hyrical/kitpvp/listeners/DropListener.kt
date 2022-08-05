package org.hyrical.kitpvp.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class DropListener : Listener {

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        event.drops.clear()
    }
}