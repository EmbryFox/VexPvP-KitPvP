package org.hyrical.kitpvp.listeners

import me.activated.core.plugin.AquaCoreAPI
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.translate

class JoinQuitListeners : Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    fun onJoin(event: PlayerJoinEvent) {
        Bukkit.broadcastMessage(translate("&8[&a+&8] &r${AquaCoreAPI.INSTANCE.getPlayerData(event.player.uniqueId).nameColor}${event.player.name}"))
        if (event.player.hasPlayedBefore()) return

        event.player.teleport(Location(Bukkit.getWorld("world"), 0.5, 111.0, -7.5))
        KitPvP.instance.config.set("joins", KitPvP.instance.config.getInt("joins") + 1)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        event.quitMessage = null
    }
}