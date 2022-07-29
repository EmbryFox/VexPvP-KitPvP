package org.hyrical.kitpvp.coinflip.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.entity.Player
import org.hyrical.kitpvp.coinflip.CoinflipService
import org.hyrical.kitpvp.sendMessage

object CoinflipCommand {

    @Command(["coinflips", "cf"])
    @JvmStatic
    fun coinflips(player: Player){

    }

    @Command(["coinflip create", "cf create"])
    @JvmStatic
    fun coinflip(player: Player, @Param("amount") amount: Double){
        CoinflipService.coinflips[player.uniqueId] = CoinflipService.Coinflip(player.uniqueId,null, amount)

        player sendMessage "&fYou have created a &dcoinflip &ffor &d${amount}&f."
    }
}