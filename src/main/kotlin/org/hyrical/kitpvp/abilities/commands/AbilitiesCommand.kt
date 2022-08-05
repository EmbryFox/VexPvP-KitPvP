package org.hyrical.kitpvp.abilities.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.entity.Player
import org.hyrical.kitpvp.abilities.AbilityHandler
import org.hyrical.kitpvp.sendMessage

object AbilitiesCommand {

    @Command(["abilities give"], permission = "kitpvp.abilities.give")
    @JvmStatic
    fun abilitiesGive(player: Player, @Param(name = "ability") ability: String) {
        if (!AbilityHandler.abilities.containsKey(ability)) {
            player sendMessage "&cAbility not found."
            return
        }

        player.inventory.addItem(AbilityHandler.abilities[ability]!!.getItem())
    }
}