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
            "&7Basic Crate Key",
            listOf("&7You will receive basic crate keys", "&7when you purchase them."),
            "broadcast ${player.name} has purchased basic crate keys",
            30
        )

        return applyButtons(buttons)
    }
}