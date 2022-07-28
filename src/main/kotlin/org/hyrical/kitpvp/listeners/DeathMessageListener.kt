package org.hyrical.kitpvp.listeners

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.hyrical.kitpvp.profiles.canSeeKillMessages
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate
import java.util.StringJoiner

class DeathMessageListener : Listener {

    @EventHandler
    fun onDeathMessage(event: PlayerDeathEvent) {
        event.deathMessage = null

        val deathMessage: String? = translate("&5${event.entity.killer.name}[&d${event.entity.killer.getProfile().kills}&5] &dhas killed &5${event.entity.name}[&d${event.entity.getProfile().kills}&5]")

        Bukkit.getOnlinePlayers().stream().filter { it.canSeeKillMessages() }.forEach { it.sendMessage(deathMessage) }

        val profile = event.entity.getProfile()

        profile.deaths++
        profile.save()
    }
}