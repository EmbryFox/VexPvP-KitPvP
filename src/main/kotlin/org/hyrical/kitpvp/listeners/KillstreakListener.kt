package org.hyrical.kitpvp.listeners

import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.hyrical.kitpvp.profiles.Profile
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

class KillstreakListener : Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    fun onDeath(event: PlayerDeathEvent) {
        if (event.entity.killer == null) return

        val profile = event.entity.killer.getProfile()

        profile.kills++
        profile.killstreak++
        val level = profile.getLevel()
        profile.experience += 30
        profile.save()

        if (level != profile.getLevel()) {
            event.entity.killer sendMessage "&fYou have leveled up to &d${profile.getLevel()}&f."
        }

        event.entity.killer sendMessage "&fYou need &d${profile.getNextLevelExperience()} &fXP to level up to level &5${profile.getLevel() + 1}&f."

        applyKillstreakRewards(profile, event.entity.killer)
    }

    private fun applyKillstreakRewards(profile: Profile, player: Player) {
        when (profile.killstreak) {
            5 -> {
                generateMessage(player, 5)
            }
            10 -> {
                generateMessage(player, 10)
            }
            15 -> {
                generateMessage(player, 20)
            }
            20 -> {
                generateMessage(player, 20)
            }
            25 -> {
                generateMessage(player, 20)
            }
            30 -> {
                generateMessage(player, 20)
            }
            35 -> {
                generateMessage(player, 20)
            }
            40 -> {
                generateMessage(player, 20)
            }
            45 -> {
                generateMessage(player, 20)
            }
            50 -> {
                generateMessage(player, 20)
            }
        }
    }

    private fun generateMessage(player: Player, killstreak: Int){
        Bukkit.broadcastMessage(translate("&7[&d" + Constants.EXP_SYMBOL + "&7] &5${player.name} " +
                "&fis now on a &d$killstreak &fkillstreak!"))
    }
}