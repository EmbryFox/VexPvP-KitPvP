package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Player
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

object ToggleDeathMessageCommand {

    @Command(["toggledeathmessage", "tdm"], description = "Toggle death message")
    @JvmStatic
    fun toggleDeathMessage(player: Player) {
        val profile = player.getProfile()

        profile.canSeeKillMessages = !profile.canSeeKillMessages
        profile.save()

        player sendMessage "&fYou are now ${if (profile.canSeeKillMessages) "&aable" else "&cnot able"} &fto see death messages!"


    }
}