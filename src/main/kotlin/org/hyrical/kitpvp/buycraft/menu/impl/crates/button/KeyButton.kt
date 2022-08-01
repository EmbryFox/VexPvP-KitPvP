package org.hyrical.kitpvp.buycraft.menu.impl.crates.button

import net.evilblock.cubed.menu.Button
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.hyrical.kitpvp.buycraft.menu.confirm.ConfirmMenu
import org.hyrical.kitpvp.translate


class KeyButton(private val name: String, private val description: List<String>, val command: String, val amountPer: Int): Button() {

    override fun getName(player: Player): String {
        return translate(name)
    }

    override fun getDescription(player: Player): List<String> {
        val desc = description.map { translate(it) }.toMutableList()
        desc.add(translate("&e"))
        desc.add(translate("&7Price per item &f➥ &a$amountPer"))

        return desc
    }

    override fun getMaterial(player: Player): Material {
        return Material.TRIPWIRE_HOOK
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        ConfirmMenu(pricePerItem = amountPer, player = player) { b, i ->
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.name).replace("%amount%", i.toString()))
        }.openMenu(player)
    }
}