package org.hyrical.kitpvp.buycraft.command

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.entity.Player
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage

object GemCommands {

    @Command(["gem", "gems"], description = "Displays the amount of gems you have.")
    @JvmStatic
    fun gems(player: Player) {
        player sendMessage "&7Gems &f➥ &a${player.getProfile().gems}"
    }

    @Command(["gem give", "gems give"], description = "Gives you a certain amount of gems.", permission = "kitpvp.gem.give")
    @JvmStatic
    fun gemsGive(player: Player, @Param("target") target: Player, @Param("amount") amount: Int) {
        target.getProfile().gems += amount
        target.getProfile().save()

        target sendMessage "&7${target.name} &f➥ &aYou have been given &f${amount} &agems."
    }

    @Command(["gem take", "gems take"], description = "Takes away a certain amount of gems.", permission = "kitpvp.gem.take")
    @JvmStatic
    fun gemsTake(player: Player, @Param("target") target: Player, @Param("amount") amount: Int) {
        target.getProfile().gems -= amount
        target.getProfile().save()

        target sendMessage "&7${target.name} &f➥ &aYou have been taken away &f${amount} &agems."
    }

    @Command(["gem set", "gems set"], description = "Sets a certain amount of gems.", permission = "kitpvp.gem.set")
    @JvmStatic
    fun gemsSet(player: Player, @Param("target") target: Player, @Param("amount") amount: Int) {
        target.getProfile().gems = amount
        target.getProfile().save()

        target sendMessage "&7${target.name} &f➥ &aYou have been given &f${amount} &agems."
    }

    @Command(["gem check", "gems check"], description = "Checks the amount of gems a player has.", permission = "kitpvp.gem.check")
    @JvmStatic
    fun gemsCheck(player: Player, @Param("target") target: Player) {
        target sendMessage "&7${target.name} &f➥ &a${target.getProfile().gems}"
    }
}