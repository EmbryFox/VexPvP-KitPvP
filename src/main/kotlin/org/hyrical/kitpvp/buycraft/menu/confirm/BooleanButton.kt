/*
 * Copyright (c) 2020. Joel Evans
 *
 * Use and or redistribution of compiled JAR file and or source code is permitted only if given
 * explicit permission from original author: Joel Evans
 */

package org.hyrical.kitpvp.buycraft.menu.confirm

import net.evilblock.cubed.menu.Button
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.ArrayList
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

class BooleanButton(
    private val parent: ConfirmMenu,
    private val value: Boolean,
    private val confirm: Boolean = false
) : Button() {

    override fun getName(player: Player): String {
        return if (value) {
            if (confirm) {
                "${ChatColor.GREEN}${ChatColor.BOLD}Confirm"
            } else {
                "${ChatColor.GREEN}${ChatColor.BOLD}Yes"
            }
        } else {
            if (confirm) {
                "${ChatColor.RED}${ChatColor.BOLD}Cancel"
            } else {
                "${ChatColor.RED}${ChatColor.BOLD}No"
            }
        }
    }

    override fun getDescription(player: Player): List<String> {
        return ArrayList()
    }

    override fun getDamageValue(player: Player): Byte {
        return (if (this.value) 5 else 14).toByte()
    }

    override fun getMaterial(player: Player): Material {
        return Material.WOOL
    }

    override fun clicked(player: Player, i: Int, clickType: ClickType, view: InventoryView) {
        if (value) {
            playSuccess(player)
        } else {
            playFail(player)
            parent.called = true
            player sendMessage "&cYou have cancelled the transaction."
            player.closeInventory()
            return
        }

        parent.invokeCallback(true)
    }

}