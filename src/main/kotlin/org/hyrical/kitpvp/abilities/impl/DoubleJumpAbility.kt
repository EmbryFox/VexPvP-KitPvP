package org.hyrical.kitpvp.abilities.impl

import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.Sound
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
            .name(translate("&dDouble Jump"))
            .build()
    }

    override fun getCooldown(): Long {
        return 1000L * 20
    }

    override fun onRightClick(player: Player) {
        player.velocity = player.location.direction.multiply(1.5).setY(1)

        player.playSound(player.location, Sound.BAT_HURT, 2.2f, 5.6f);
        player.playSound(player.location, Sound.WITHER_SHOOT, 1.9f, 5.1f);
        player.playSound(player.location, Sound.GHAST_FIREBALL, 1.9f, 1.4f);
    }
}