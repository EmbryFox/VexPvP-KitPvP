package org.hyrical.kitpvp.buycraft.menu.impl

import net.evilblock.cubed.menu.Button
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.hyrical.kitpvp.buycraft.menu.StoreMenu
import org.hyrical.kitpvp.buycraft.menu.confirm.ConfirmMenu

class CrateKeysMenu : StoreMenu("Store ➥ Crate Keys") {
    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons = HashMap<Int, Button>()

        buttons[10] = object : Button() {
            override fun getMaterial(player: Player): Material {
                return Material.GOLD_INGOT
            }

            override fun getName(player: Player): String {
                return "Crate Keys"
            }

            override fun getDescription(player: Player): List<String> {
                return listOf("Crate Keys")
            }

            override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
                ConfirmMenu(1) { b, i ->
                    if (b) {
                        player.sendMessage("You have bought $i Crate Keys")
                    } else {
                        player.sendMessage("You have cancelled your purchase")
                    }
                }.openMenu(player)
            }
        }

        return applyButtons(buttons)
    }
}