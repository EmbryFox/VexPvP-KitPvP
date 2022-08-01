package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.entity.Player
import org.hyrical.kitpvp.getColoredName
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate
import java.text.NumberFormat
import java.util.*

object StatsCommand {

    @Command(["stats"])
    @JvmStatic
    fun stats(player: Player, @Param("target", "self") target: Player){
        val profile = target.getProfile()

        player.sendMessage(translate("&7&m------------------------------"))
        player.sendMessage(translate("&5${target.name}&f's Stats"))
        player.sendMessage(translate("&7&m------------------------------"))
        player.sendMessage(translate("&fKills: &d${profile.kills}"))
        player.sendMessage(translate("&fDeaths: &d${profile.deaths}"))
        player.sendMessage(translate("&fBalance: &a$${NumberFormat.getInstance(Locale.US).format(profile.balance)}"))
        player.sendMessage(translate("&fKillStreak: &d${profile.killstreak}"))
        player.sendMessage(translate("&fKDR: &d${profile.getKDR()}"))
        player.sendMessage(translate("&7&m------------------------------"))
    }

    @Command(["setkills"], permission = "kitpvp.admin")
    @JvmStatic
    fun setkills(player: Player, @Param("target") target: Player, @Param("amount") amount: Int){
        val profile = target.getProfile()

        profile.kills = amount
        profile.save()

        player sendMessage "&aYou have set ${target.getColoredName()}&a's kills to $amount&a."
    }

    @Command(["setdeaths"], permission = "kitpvp.admin")
    @JvmStatic
    fun setdeaths(player: Player, @Param("target") target: Player, @Param("amount") amount: Int){
        val profile = target.getProfile()

        profile.deaths = amount
        profile.save()

        player sendMessage "&aYou have set ${target.getColoredName()}&a's deaths to $amount&a."
    }
}