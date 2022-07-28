package org.hyrical.kitpvp.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.hyrical.kitpvp.profiles.getProfile

class BountyListener : Listener {

    @EventHandler
    fun death(event: PlayerDeathEvent){
        val attacker = event.entity.killer
        val victim = event.entity

        val attackerProfile = attacker.getProfile()
        val victimProfile = victim.getProfile()

        if (victimProfile.bounty != 0.0){
            attackerProfile.balance += victimProfile.bounty
            victimProfile.bounty = 0.0

            attackerProfile.save()
            victimProfile.save()

        }
    }

}