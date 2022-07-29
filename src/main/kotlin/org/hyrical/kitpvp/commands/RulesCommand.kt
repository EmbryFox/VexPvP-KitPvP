package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Player
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.sendMessage

object RulesCommand {

    @Command(["rules"], description = "View the rules")
    @JvmStatic
    fun rules(player: Player) {
        for (msg in KitPvP.instance.config.getStringList("rules")) {
            player sendMessage msg
        }
    }
}