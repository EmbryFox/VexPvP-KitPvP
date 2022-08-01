package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

object BountyCommand {

    @Command(["bounty"], description = "Put a bounty on a player!")
    @JvmStatic
    fun bounty(player: Player,
               @Param("target") target: Player,
               @Param("amount") amount: Double) {
        val profile =  player.getProfile()
        val targetProfile = target.getProfile()

        if (player == target){
            player sendMessage "&cYou cannot put a bounty on yourself!"
            return
        }

        if (targetProfile.bounty != 0.0){
            player sendMessage "&cThat player already has a bounty."
            return
        }

        if (amount.isNaN() || amount < 0){
            player sendMessage "&cThat number is invalid."
            return
        }

        if (amount > profile.balance){
            player sendMessage "&cYou don't have enough money to put a bounty on this player!"
            return
        }

        if (amount < KitPvP.instance.config.getInt("bounty-min")){
            player sendMessage "&cYou are required to put at least $${KitPvP.instance.config.getInt("bounty-min")}."
            return
        }

        targetProfile.bounty = amount
        targetProfile.save()

        Bukkit.broadcastMessage(translate("&7[&d" + Constants.EXP_SYMBOL + "&7] " +
                "&5${player.name} &fhas placed a bounty on &5${target.name} &ffor &d$${amount}&f!"))
     }
}