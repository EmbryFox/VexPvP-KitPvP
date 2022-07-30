package org.hyrical.kitpvp.buycraft

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.GlassButton
import net.evilblock.cubed.util.bukkit.ColorUtil
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.hyrical.kitpvp.buycraft.buttons.InfoButton
import org.hyrical.kitpvp.buycraft.buttons.SecondaryInfoButton
import org.hyrical.kitpvp.buycraft.buttons.ThirdendaryInfoButton
import org.hyrical.kitpvp.buycraft.menu.impl.crates.CrateKeysMenu
import org.hyrical.kitpvp.translate

class MainBuycraftMenu : Menu("Store ➥ Main") {

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons = HashMap<Int, Button>()

        for (i in 0 until (9 * 6)) {
            buttons[i] = GlassButton(ColorUtil.CHAT_COLOR_TO_DYE_DATA[ChatColor.DARK_GREEN]!!.toByte())
        }

        buttons[13] = InfoButton()
        buttons[19] = SecondaryInfoButton()
        buttons[25] = ThirdendaryInfoButton()

        buttons[31] = RankButton()
        buttons[38] = TagButton()
        buttons[42] = KeysButton()

        return buttons
    }

    class RankButton : Button() {
        override fun getName(player: Player): String {
            return translate("&a&lRanks")
        }

        override fun getDescription(player: Player): List<String> {
            return listOf(translate("&e"), translate("&2This category includes:"), translate(" &7➥ &aLifetime Ranks"), translate(" &7➥ &aRank Upgrades"), translate("&e"), translate("&2Click to view products!"))
        }

        override fun getMaterial(player: Player): Material {
            return Material.BOOK_AND_QUILL
        }
    }

    class TagButton : Button() {
        override fun getName(player: Player): String {
            return translate("&a&lTags")
        }

        override fun getDescription(player: Player): List<String> {
            return listOf(translate("&e"), translate("&2This category includes:"), translate(" &7➥ &aPre-made Tags"), translate(" &7➥ &aCustom Tags"), translate("&e"), translate("&2Click to view products!"))
        }

        override fun getMaterial(player: Player): Material {
            return Material.NAME_TAG
        }
    }

    class KeysButton : Button() {
        override fun getName(player: Player): String {
            return translate("&a&lCrate Keys")
        }

        override fun getDescription(player: Player): List<String> {
            return listOf(translate("&e"), translate("&2This category includes:"), translate(" &7➥ &aBasic Crates"), translate(" &7➥ &aPremium Crates"), translate(" &7➥ &aGamble Crates"), translate("&e"), translate("&2Click to view products!"))
        }

        override fun getMaterial(player: Player): Material {
            return Material.TRIPWIRE_HOOK
        }

        override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
            CrateKeysMenu().openMenu(player)
        }
    }
}