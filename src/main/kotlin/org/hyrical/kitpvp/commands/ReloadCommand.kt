package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Player
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.scoreboard.ScoreboardConfig
import org.hyrical.kitpvp.sendMessage

object ReloadCommand {

    @Command(["kitpvp-reload"], description = "Reloads the plugin", permission = "kitpvp.reload")
    @JvmStatic
    fun reload(player: Player) {
        KitPvP.instance.reloadConfig()
        ScoreboardConfig.load()

        player sendMessage "&aKitPvP has been reloaded!"
    }

}