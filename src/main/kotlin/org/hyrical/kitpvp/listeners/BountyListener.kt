package org.hyrical.kitpvp.listeners

import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate
import java.text.NumberFormat
import java.util.*

class BountyListener : Listener {

    @EventHandler
    fun death(event: PlayerDeathEvent){
        val attacker = event.entity.killer
        val victim = event.entity

        if (attacker == null || victim == null) return

        val attackerProfile = attacker.getProfile()
        val victimProfile = victim.getProfile()

        if (victimProfile.bounty != 0.0){
            Bukkit.broadcastMessage(translate("&7[&d${Constants.EXP_SYMBOL}&7] &5${attacker.name} &fhas claimed &5${victim.name}&f's bounty for " +
                    "&d${NumberFormat.getInstance(Locale.US).format(victimProfile.bounty)}&f."))

            attackerProfile.balance += victimProfile.bounty
            victimProfile.bounty = 0.0

            attackerProfile.save()
            victimProfile.save()

        }
    }

}