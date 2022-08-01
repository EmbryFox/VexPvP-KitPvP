package org.hyrical.kitpvp.skydrops.command

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Player
import org.hyrical.kitpvp.skydrops.SkyDropHandler
import org.hyrical.kitpvp.skydrops.task.SkyDropTask

object SkyDropCommands {

    @Command(["skydrop forcestart"], "kitpvp.admin")
    @JvmStatic
    fun skydrop(player: Player){

        SkyDropTask().run()
    }
}