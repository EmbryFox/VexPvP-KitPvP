package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.hyrical.kitpvp.leaderboard.KillLeaderboard
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate
import java.util.*

object LeaderboardCommand {

    @Command(["leaderboard", "leaderboards", "statstop"])
    @JvmStatic
    fun leaderboard(player: Player, @Param("type", "kills") type: LeaderboardType){
        player.sendMessage(translate("&7&m--------------------------------------"))
        player.sendMessage(translate("&fYou are viewing the top &5${type.nameFirst}&f."))
        player.sendMessage(translate("&7&m--------------------------------------"))

        var i = 1
        if (type.nameFirst == "Kills"){
            for (c in 0..10){
                for (map in KillLeaderboard.getSortedMap()){

                    player sendMessage if (i == 1) "&51. &d${Bukkit.getOfflinePlayer(map.key).name} &7- &f${map.value}" else "&7$i. &d${Bukkit.getOfflinePlayer(map.key).name} &7- &f${map.value}"
                    i++
                }
            }
        }
        player.sendMessage(translate("&7&m--------------------------------------"))

    }
}

enum class LeaderboardType(val nameFirst: String, val aliases: Array<String>) {
    KILLS("Kills", arrayOf("k")),
    DEATHS("Deaths", arrayOf("d")),
    KDR("KD", arrayOf("kdr"))

}