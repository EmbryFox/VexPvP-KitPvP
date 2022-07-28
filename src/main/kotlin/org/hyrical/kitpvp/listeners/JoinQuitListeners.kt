package org.hyrical.kitpvp.listeners

import me.activated.core.plugin.AquaCoreAPI
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.translate

class JoinQuitListeners : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        translate("&8[&a+&8] &r${AquaCoreAPI.INSTANCE.getPlayerData(event.player.uniqueId).nameColor}${event.player.name} &r&7#${KitPvP.instance!!.config.getInt("joins")}")
        if (event.player.hasPlayedBefore()) return

        KitPvP.instance!!.config.set("joins", KitPvP.instance.config.getInt("joins") + 1)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        event.quitMessage = null
    }
}