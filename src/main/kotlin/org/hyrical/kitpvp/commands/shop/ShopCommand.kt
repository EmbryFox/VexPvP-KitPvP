package org.hyrical.kitpvp.commands.shop

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Player
import org.hyrical.kitpvp.commands.shop.menu.ShopMenu

object ShopCommand {

    @Command(["shop"])
    @JvmStatic
    fun shop(player: Player){
        ShopMenu().openMenu(player)
    }
}