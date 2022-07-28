package org.hyrical.kitpvp.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.hyrical.kitpvp.profiles.Profile
import org.hyrical.kitpvp.profiles.getProfile

class KillstreakListener : Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    fun onDeath(event: PlayerDeathEvent) {
        if (event.entity.killer == null) return

        val profile = event.entity.killer.getProfile()

        profile.kills++
        profile.killstreak++
        profile.save()

        applyKillstreakRewards(profile, event.entity.killer)

    }

    private fun applyKillstreakRewards(profile: Profile, player: Player) {
        TODO("Not yet implemented")
    }
}