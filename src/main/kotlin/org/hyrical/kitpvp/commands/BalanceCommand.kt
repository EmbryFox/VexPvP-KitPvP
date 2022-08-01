package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.entity.Player
import org.hyrical.kitpvp.getColoredName
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import java.text.NumberFormat
import java.util.*

object BalanceCommand {

    @Command(["bal", "balance", "money", "cash"])
    @JvmStatic
    fun balance(player: Player, @Param("target", defaultValue = "self") target: Player){
        val profile = target.getProfile()

        player sendMessage "&d${target.name}&f's balance: &a$${NumberFormat.getInstance(Locale.US).format(profile.balance)}"
    }

    @Command(["setbal", "setbalance"], "kitpvp.setbal")
    @JvmStatic
    fun setbal(player: Player, @Param("target") target: Player, @Param("amount") amount: Double){
        val profile = target.getProfile()

        profile.balance = amount
        profile.save()

        player sendMessage "&aYou have set the balance of ${player.getColoredName()} &ato $amount"
    }

    @Command(["addbal", "addbalance"], "kitpvp.addbal")
    @JvmStatic
    fun addbal(player: Player, @Param("target") target: Player, @Param("amount") amount: Double){
        val profile = target.getProfile()

        profile.balance += amount
        profile.save()

        player sendMessage "&aYou have added $amount to the balance of ${player.getColoredName()}&a."
    }
}