package org.hyrical.kitpvp.koth.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.entity.Player
import org.hyrical.kitpvp.koth.Event
import org.hyrical.kitpvp.koth.koth.Koth
import org.hyrical.kitpvp.koth.storage.KothHandler
import org.hyrical.kitpvp.sendMessage

object KothCommands {

    @Command(["koth create"], permission = "kitpvp.koth.create")
    @JvmStatic
    fun create(player: Player, @Param("name") name: String) {
        if (KothHandler.koths.containsKey(name)) {
            player sendMessage "&cKoth already exists."
            return
        }

        val koth = Koth(name, false, Event.EventType.KOTH, player.location, 3)
        koth.save()

        player sendMessage "&aKoth created."
    }

    @Command(["koth activate"], permission = "kitpvp.koth.activate")
    @JvmStatic
    fun activate(player: Player, @Param("name") name: String) {
        if (KothHandler.activeKoth != null) {
            player sendMessage "&cThere is already an active Koth."
            return
        }

        val koth = KothHandler.koths[name] ?: run {
            player sendMessage "&cKoth does not exist."
            return
        }

        koth.activate()
    }
}