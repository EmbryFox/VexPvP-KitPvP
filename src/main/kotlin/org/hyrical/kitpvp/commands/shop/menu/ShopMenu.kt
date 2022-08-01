package org.hyrical.kitpvp.commands.shop.menu

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import org.bukkit.entity.Player
import org.hyrical.kitpvp.commands.shop.menu.buttons.*
import org.hyrical.kitpvp.translate

class ShopMenu : Menu() {
    override fun getTitle(player: Player): String {
        return translate("&5Shop")
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: HashMap<Int, Button> = hashMapOf()

        buttons[2] = ArmorButton()
        buttons[3] = PotionButton()
        buttons[4] = MiscButton()
        buttons[5] = WeaponsButton()
        buttons[6] = EnchantmentButton()

        return buttons
    }
}

