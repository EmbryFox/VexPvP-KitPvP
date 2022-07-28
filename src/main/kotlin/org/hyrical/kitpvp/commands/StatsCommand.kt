package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.entity.Player
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate

object StatsCommand {

    @Command(["stats"])
    fun stats(player: Player, @Param("target", "self") target: Player){
        val profile = target.getProfile()

        player.sendMessage(translate("&7&m------------------------------"))
        player.sendMessage(translate("&5${target.name} &fStats"))
        player.sendMessage(translate("&7&m------------------------------"))
        player.sendMessage(translate("&fKills: &d${profile.kills}"))
        player.sendMessage(translate("&fDeaths: &d${profile.deaths}"))
        player.sendMessage(translate("&fBalance: &d${profile.balance}"))
        player.sendMessage(translate("&fKillStreak: &d${profile.killstreak}"))
        player.sendMessage(translate("&fKDR: &d${profile.getKDR()}"))
        player.sendMessage(translate("&7&m------------------------------"))
    }
}