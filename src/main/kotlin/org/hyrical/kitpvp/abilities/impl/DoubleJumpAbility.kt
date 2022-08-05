package org.hyrical.kitpvp.abilities.impl

import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.abilities.AbstractAbility
import org.hyrical.kitpvp.translate

class DoubleJumpAbility : AbstractAbility() {
    override fun getIdentifier(): String {
        return "double-jump"
    }

    override fun getItem(): ItemStack {
        return ItemBuilder.of(Material.FEATHER)
            .name(translate("&eDouble Jump"))
            .build()
    }

    override fun getCooldown(): Long {
        return 1000L * 20
    }

    override fun onRightClick(player: Player) {
        player.velocity = player.location.direction.multiply(2).setY(1)
    }
}