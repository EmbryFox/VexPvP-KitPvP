package org.hyrical.kitpvp.coinflip.menu.button

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.buttons.TexturedHeadButton
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.coinflip.CoinflipService

class CoinflipButton(val coinflip: CoinflipService.Coinflip) : TexturedHeadButton() {

    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.SKULL_ITEM).build()
    }

}