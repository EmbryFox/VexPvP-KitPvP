package org.hyrical.kitpvp.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate

class ChatListener : Listener {

    @EventHandler
    fun chat(event: AsyncPlayerChatEvent){
        val player = event.player
        val message = event.message

        if (event.isCancelled) return

        val profile = player.getProfile()

        event.format = translate("")
    }

}