package org.hyrical.kitpvp.profiles.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.hyrical.kitpvp.profiles.Profile
import org.hyrical.kitpvp.profiles.ProfileService

class ProfileListener : Listener {

    @EventHandler
    fun onAsyncJoin(player: AsyncPlayerPreLoginEvent) {
        if (ProfileService.service.retrieve(player.uniqueId) == null) {
            val profile = Profile(player.uniqueId)
            ProfileService.service.storeAsync(player.uniqueId, profile)
            ProfileService.cache[player.uniqueId] = profile
        }
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        if (ProfileService.cache.containsKey(event.player.uniqueId)) return

        ProfileService.service.retrieveAsync(event.player.uniqueId).thenAccept {
            ProfileService.cache[event.player.uniqueId] = it!!
        }
    }
}