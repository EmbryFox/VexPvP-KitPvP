package org.hyrical.kitpvp.listeners

import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.profiles.Profile
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

class KillstreakListener : Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    fun onDeath(event: PlayerDeathEvent) {
        if (event.entity.killer == null) return
        if (event.entity.killer == event.entity) return

        val profile = event.entity.killer.getProfile()

        val newBal = KitPvP.random.nextInt(5, 10)
        val level = profile.getLevel()

        event.entity.killer sendMessage "You received &d$$newBal &ffor killing &5${event.entity.name}&f."

        profile.kills++
        profile.killstreak++
        profile.experience += KitPvP.random.nextInt(5, 10)
        profile.balance += newBal
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
                generateMessage(player, 15)
            }
            20 -> {
                generateMessage(player, 20)
            }
            25 -> {
                generateMessage(player, 25)
            }
            30 -> {
                generateMessage(player, 30)
            }
            35 -> {
                generateMessage(player, 35)
            }
            40 -> {
                generateMessage(player, 40)
            }
            45 -> {
                generateMessage(player, 45)
            }
            50 -> {
                generateMessage(player, 50)
            }
        }
    }

    private fun generateMessage(player: Player, killstreak: Int){
        val profile = player.getProfile()

        profile.balance += killstreak * 5.0
        profile.bounty += 20
        profile.save()

        Bukkit.broadcastMessage(translate("&7[&d" + Constants.EXP_SYMBOL + "&7] &5${player.name} " +
                "&fis now on a &d$killstreak &fkillstreak!"))
    }
}