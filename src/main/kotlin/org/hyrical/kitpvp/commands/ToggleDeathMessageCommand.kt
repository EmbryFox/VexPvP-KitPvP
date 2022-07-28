package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Player
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate

object ToggleDeathMessageCommand {

    @Command(["toggledeathmessage", "tdm"], description = "Toggle death message")
    @JvmStatic
    fun toggleDeathMessage(player: Player) {
        val profile = player.getProfile()

        profile.canSeeKillMessages = !profile.canSeeKillMessages
        player.sendMessage(translate("&eYou have ${if (profile.canSeeKillMessages) "&aenabled" else "&cdisabled"} &edeath messages."))
        profile.save()


    }
}