package org.hyrical.kitpvp.buycraft.menu

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.BackButton
import net.evilblock.cubed.menu.buttons.GlassButton
import net.evilblock.cubed.util.bukkit.ColorUtil
import net.evilblock.cubed.util.bukkit.Tasks
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.hyrical.kitpvp.buycraft.MainBuycraftMenu
import org.hyrical.kitpvp.buycraft.buttons.InfoButton
import org.hyrical.kitpvp.buycraft.buttons.SecondaryInfoButton
import org.hyrical.kitpvp.buycraft.buttons.ThirdendaryInfoButton
import org.hyrical.kitpvp.translate

abstract class StoreMenu(val title: String) : Menu() {

    override fun getTitle(player: Player): String {
        return translate(title)
    }
    fun applyButtons(buttons: HashMap<Int, Button>): Map<Int, Button> {
        val b = GlassButton(ColorUtil.CHAT_COLOR_TO_DYE_DATA[ChatColor.DARK_GREEN]!!.toByte())

        buttons[0] = b
        buttons[1] = b
        buttons[2] = b

        buttons[3] = SecondaryInfoButton()
        buttons[4] = InfoButton()
        buttons[5] = ThirdendaryInfoButton()

        buttons[6] = b
        buttons[7] = b
        buttons[8] = b

        buttons[9] = b
        buttons[17] = b
        buttons[18] = b
        buttons[19] = b
        buttons[20] = b
        buttons[21] = b
        buttons[22] = BackButton {
            MainBuycraftMenu().openMenu(it)
        }

        buttons[23] = b
        buttons[24] = b
        buttons[25] = b
        buttons[26] = b

        return buttons
    }

    override fun onClose(player: Player, manualClose: Boolean) {
        if (manualClose) {
            Tasks.delayed(2L) {
                MainBuycraftMenu().openMenu(player)
            }
        }
    }
}