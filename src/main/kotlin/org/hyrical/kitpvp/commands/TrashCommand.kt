package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.hyrical.kitpvp.combat.isCombatTagged
import org.hyrical.kitpvp.sendMessage

object TrashCommand {

    @Command(["trash"], description = "Trash your inventory")
    @JvmStatic
    fun trash(player: Player) {
        if (player.isCombatTagged()) {
            player sendMessage "&cYou can't do that while in combat"
            return
        }

        val inv = Bukkit.createInventory(null, 27, "Trash")
        player.openInventory(inv)
    }
}