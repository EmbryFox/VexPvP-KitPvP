package org.hyrical.kitpvp.buycraft.buttons

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ColorUtil
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate

class InfoButton : Button() {

    override fun getName(player: Player): String {
        return translate("&a&lGem Shop")
    }

    override fun getDescription(player: Player): List<String> {
        val profile = player.getProfile()
        val desc = mutableListOf<String>()

        desc.add(translate("&e"))
        desc.add(translate("&7Gems: &a${profile.gems}"))
        desc.add(translate("&e"))
        desc.add(translate("&7Purchase gems at &astore.vexmc.club&7."))

        return desc
    }

    override fun shouldCancel(player: Player, slot: Int, clickType: ClickType): Boolean {
        return true
    }

    override fun getMaterial(player: Player): Material {
        return Material.WOOL
    }

    override fun getDamageValue(player: Player): Byte {
        return ColorUtil.CHAT_COLOR_TO_WOOL_DATA[ChatColor.GREEN]!!.toByte()
    }
}

class SecondaryInfoButton : Button() {

    override fun getName(player: Player): String {
        return translate("&2Gem Tips")
    }

    override fun getDescription(player: Player): List<String> {
        return listOf(translate("&e"), translate("&7Purchase gems at &astore.vexmc.club&7."))
    }

    override fun getMaterial(player: Player): Material {
        return Material.INK_SACK
    }

    override fun getDamageValue(player: Player): Byte {
        return ColorUtil.CHAT_COLOR_TO_DYE_DATA[ChatColor.DARK_GREEN]!!.toByte()
    }
}

class ThirdendaryInfoButton : Button() {

    override fun getName(player: Player): String {
        return translate("&2Gem Tips")
    }

    override fun getDescription(player: Player): List<String> {
        return listOf(translate("&e"), translate("&7Earn &agems &7through giveaways, games and events!"))
    }

    override fun getMaterial(player: Player): Material {
        return Material.INK_SACK
    }

    override fun getDamageValue(player: Player): Byte {
        return ColorUtil.CHAT_COLOR_TO_DYE_DATA[ChatColor.DARK_GREEN]!!.toByte()
    }
}