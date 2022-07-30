package org.hyrical.kitpvp.commands.leaderboard

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.hyrical.kitpvp.leaderboard.KillLeaderboard
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

object LeaderboardCommand {

    @Command(["leaderboard", "leaderboards", "statstop"])
    @JvmStatic
    fun leaderboard(player: Player, @Param("type", "kills") type: LeaderboardType){
        player.sendMessage(translate("&7&m--------------------------------------"))
        player.sendMessage(translate("&fYou are viewing the top &5${type.nameFirst}&f."))
        player.sendMessage(translate("&7&m--------------------------------------"))

        var i = 1

        for (entry in KillLeaderboard.getLeaderboards()){
            if (Bukkit.getOfflinePlayer(entry.key) == null) continue
            if (i == 11) continue

            player sendMessage (if (i == 1) "&51." else "&7$i.") + " &d${Bukkit.getOfflinePlayer(entry.key)} &7- &f${entry.value}"

            i++
        }

        player.sendMessage(translate("&7&m--------------------------------------"))

    }
}

enum class LeaderboardType(val nameFirst: String, val aliases: Array<String>) {
    KILLS("Kills", arrayOf("k")),
    DEATHS("Deaths", arrayOf("d")),
    KDR("KD", arrayOf("kdr"))

}