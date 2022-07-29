package org.hyrical.kitpvp.commands.leaderboard.type

import net.evilblock.cubed.command.data.parameter.ParameterType
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.hyrical.kitpvp.commands.LeaderboardType

class LeaderboardTypeParam : ParameterType<LeaderboardType> {
    override fun tabComplete(player: Player, flags: Set<String>, source: String): List<String> {
        return listOf("Kills", "Deaths", "KD")
    }

    override fun transform(sender: CommandSender, source: String): LeaderboardType? {
        if (source.lowercase() == LeaderboardType.KILLS.nameFirst.lowercase()) {
            return LeaderboardType.KILLS
        } else if (source.lowercase() == LeaderboardType.DEATHS.nameFirst.lowercase()) {
            return LeaderboardType.DEATHS
        } else if (source.lowercase() == LeaderboardType.KDR.nameFirst.lowercase()) {
            return LeaderboardType.KDR
        }
        return LeaderboardType.KILLS
    }
}