package org.hyrical.kitpvp.commands.shop.menu.buttons

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.commands.shop.menu.ShopMenu

class BackButton : Button() {

    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.NETHER_STAR).name("&cGo back").build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        player.closeInventory()

        ShopMenu().openMenu(player)
    }

}