package org.hyrical.kitpvp.buycraft.menu.impl.ranks.button

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ColorUtil
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.hyrical.kitpvp.buycraft.menu.confirm.ConfirmMenu
import org.hyrical.kitpvp.buycraft.menu.confirm.ConfirmMenuWithoutQuantity
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

class RankButton(private val name: String, private val description: List<String>, val command: String, val price: Int, val color: ChatColor) : Button() {
    override fun getName(player: Player): String {
        return translate(name)
    }

    override fun getDescription(player: Player): List<String> {
        val desc = description.map { translate(it) }.toMutableList()
        desc.add(translate("&e"))
        desc.add(translate("&7Price &f➥ &a$price"))

        return desc
    }

    override fun getMaterial(player: Player): Material {
        return Material.INK_SACK
    }

    override fun getDamageValue(player: Player): Byte {
        return ColorUtil.CHAT_COLOR_TO_DYE_DATA[color]!!.toByte()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        ConfirmMenuWithoutQuantity(price, player) { _, _ ->
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.name))
        }.openMenu(player)
    }
}