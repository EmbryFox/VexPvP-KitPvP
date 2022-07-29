package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.entity.Player
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage

object BalanceCommand {

    @Command(["bal", "balance", "money", "cash"])
    @JvmStatic
    fun balance(player: Player, @Param("target", defaultValue = "self") target: Player){
        val profile = target.getProfile()

        player sendMessage "&5${target.name}&f's balance: &d${profile.balance}"
    }
}