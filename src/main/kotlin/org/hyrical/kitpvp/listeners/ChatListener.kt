package org.hyrical.kitpvp.listeners

import me.activated.core.plugin.AquaCoreAPI
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate

class ChatListener : Listener {

    @EventHandler
    fun chat(event: AsyncPlayerChatEvent){
        val player = event.player

        if (event.isCancelled) return

        val profile = player.getProfile()

        val tag = AquaCoreAPI.INSTANCE.getTag(player.uniqueId)
        val tagPrefix = if (AquaCoreAPI.INSTANCE.getTag(player.uniqueId) != null) tag.prefix else ""

        val rankPrefix = AquaCoreAPI.INSTANCE.getPlayerRank(player.uniqueId)
            .prefix

        val nameColored = AquaCoreAPI.INSTANCE.getPlayerNameColor(player.uniqueId)

        if (tagPrefix == "") {
            event.format = translate("&7[&5${profile.getLevel()}&7] $rankPrefix $nameColored${player.name}&7: &f%2\$s")
        } else {
            event.format = translate("&7[&5${profile.getLevel()}&7] $rankPrefix $tagPrefix $nameColored${player.name}&7: &f%2\$s")
        }
    }
}