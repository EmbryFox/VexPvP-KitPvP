package org.hyrical.kitpvp.coinflip.menu.button

import net.evilblock.cubed.menu.buttons.TexturedHeadButton
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.coinflip.CoinflipService

class CoinflipButton(val coinflip: CoinflipService.Coinflip) : TexturedHeadButton() {


    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.SKULL_ITEM).name("&").build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        CoinflipService.activeCoinFlips.add(coinflip)
        CoinflipService.coinflips.remove(coinflip.player)


    }

}