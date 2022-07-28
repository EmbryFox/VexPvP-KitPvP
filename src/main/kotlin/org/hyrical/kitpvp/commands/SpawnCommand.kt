package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.combat.isCombatTagged
import org.hyrical.kitpvp.sendMessage

object SpawnCommand {

    @Command(["spawn"], description = "Teleport to the spawn")
    fun spawn(player: Player) {
        if (player.isCombatTagged()) {
            player sendMessage "&cYou cannot use this command while in combat."
            return
        }

        player sendMessage "&aYou will be teleported to spawn in 3 seconds... DO NOT MOVE"

        object : BukkitRunnable() {
            val i = 3

            override fun run() {
                //if ()
            }

        }
    }
}