package org.hyrical.kitpvp.buycraft.command

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Player
import org.hyrical.kitpvp.buycraft.MainBuycraftMenu

object StoreCommand {

    @Command(["store"])
    @JvmStatic
    fun store(player: Player) {
        MainBuycraftMenu().openMenu(player)
    }
}