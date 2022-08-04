package org.hyrical.kitpvp.listeners

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.hyrical.kitpvp.profiles.canSeeKillMessages
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate

class DeathMessageListener : Listener {

    @EventHandler
    fun onDeathMessage(event: PlayerDeathEvent) {
        event.deathMessage = null

        val profile = event.entity.getProfile()

        // Testing Jetbrains Space :) again

        profile.deaths++
        profile.killstreak = 0
        profile.save()

        val deathMessage: String = if (event.entity.killer != null) {
            translate("&d${event.entity.name}&7[&5${event.entity.getProfile().kills}&7] &fhas been killed &d${event.entity.killer.name}&7[&5${event.entity.killer.getProfile().kills}&7]")
        } else {
            translate("&d${event.entity.name}&7[&5${event.entity.getProfile().kills}&7] &fhas died.")
        }

        Bukkit.getOnlinePlayers().stream().filter { it.canSeeKillMessages() }.forEach { it.sendMessage(deathMessage) }

    }
}