package org.hyrical.kitpvp.koth.koth.listener

import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.hyrical.kitpvp.koth.storage.KothHandler
import org.hyrical.kitpvp.sendMessage

class BowPreventionListener : Listener {

    @EventHandler
    fun bow(event: EntityDamageByEntityEvent){
        if (event.damager is Arrow && event.entity is Player && (event.damager as Arrow).shooter is Player){
            val arrow = event.damager as Arrow
            val shooter = arrow.shooter as Player

            val victim = event.entity as Player

            if (KothHandler.activeKoth != null){
                val koth = KothHandler.activeKoth!!

                if (koth.onCap(victim.location)){
                    event.isCancelled = true

                    shooter sendMessage "&cYou cannot shoot other players on KoTH."
                }
            }
        }
    }

    @EventHandler
    fun bowOnCap(event: EntityDamageByEntityEvent){
        if (event.damager is Arrow && event.entity is Player && (event.damager as Arrow).shooter is Player){
            val arrow = event.damager as Arrow
            val shooter = arrow.shooter as Player

            val victim = event.entity as Player

            if (KothHandler.activeKoth != null){
                val koth = KothHandler.activeKoth!!

                if (koth.onCap(shooter.location)){
                    event.isCancelled = true

                    shooter sendMessage "&cYou cannot shoot other players while on KoTH."
                }
            }
        }
    }
}