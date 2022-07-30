package org.hyrical.kitpvp.buycraft.menu.impl.crates

import net.evilblock.cubed.menu.Button
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.hyrical.kitpvp.buycraft.MainBuycraftMenu
import org.hyrical.kitpvp.buycraft.menu.StoreMenu
import org.hyrical.kitpvp.buycraft.menu.confirm.ConfirmMenu
import org.hyrical.kitpvp.buycraft.menu.impl.crates.button.KeyButton

class CrateKeysMenu : StoreMenu("Store ➥ Crate Keys") {
    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons = HashMap<Int, Button>()

        buttons[10] = KeyButton(
            "&fBasic Crate Key",
            listOf("&7You will receive &fbronze crate keys", "&7with this purchase."),
            "broadcast ${player.name} has purchased basic crate keys",
            30
        )

        buttons[12] = KeyButton(
            "&7Slver Crate Key",
            listOf("&7You will receive silver crate keys", "&7with this purchase."),
            "broadcast ${player.name} has purchased silver crate keys",
            60
        )

        buttons[14] = KeyButton(
            "&6Gold Crate Key",
            listOf("&7You will receive &6gold crate keys", "&7with this purchase."),
            "broadcast ${player.name} has purchased gold crate keys",
            150
        )

        buttons[16] = KeyButton(
            "&7Platinum Crate Key",
            listOf("&7You will receive platinum crate keys", "&7with this purchase."),
            "broadcast ${player.name} has purchased platinum crate keys",
            300
        )

        return applyButtons(buttons)
    }
}