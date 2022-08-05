package org.hyrical.kitpvp.abilities.impl

import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import org.hyrical.kitpvp.abilities.AbstractAbility
import org.hyrical.kitpvp.sendMessage

class HookAbility : AbstractAbility() {
    override fun getIdentifier(): String {
        return "hook"
    }

    override fun getItem(): ItemStack {
        return ItemBuilder.of(Material.FISHING_ROD).name("&eHook").build()
    }

    override fun getCooldown(): Long {
        return 1000L * 30
    }

    override fun onRightClick(player: Player) {
        return
    }

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        if (event.damager !is Player) return
        if (event.entity !is Player) return

        val damager = event.damager as Player
        val entity = event.entity as Player

        if (damager.itemInHand == getItem()) {
            event.isCancelled = true
            if (isOnCooldown(damager)) {
                event.damager as Player sendMessage "&cYou cannot use this for another &l${TimeUtil.formatIntoDetailedString((((cooldowns[damager.uniqueId]!! + getCooldown()) - System.currentTimeMillis()) / 1000).toInt())}&c."
                return
            }

            val nearby: List<Entity> = damager.getNearbyEntities(20.0, 20.0, 20.0)
            for (near in nearby) {
                if (near is Player) {
                    val direction: Vector = near.getLocation().toVector().subtract(damager.location.toVector()).normalize()
                    near.setVelocity(direction)
                }
            }

            cooldowns[damager.uniqueId] = System.currentTimeMillis()
        }
    }
}