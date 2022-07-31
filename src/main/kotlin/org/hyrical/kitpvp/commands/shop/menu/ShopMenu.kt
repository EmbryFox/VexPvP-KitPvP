package org.hyrical.kitpvp.commands.shop.menu

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ColorUtil
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.Potion
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionType
import org.hyrical.kitpvp.translate

class ShopMenu : Menu() {
    override fun getTitle(player: Player): String {
        return translate("&5Shop")
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: HashMap<Int, Button> = hashMapOf()

        buttons[1] =
    }
}

