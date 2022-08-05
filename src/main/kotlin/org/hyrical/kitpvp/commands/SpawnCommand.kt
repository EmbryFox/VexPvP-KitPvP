package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.combat.isCombatTagged
import org.hyrical.kitpvp.sendMessage

object SpawnCommand {

    @Command(["spawn"], description = "Teleport to the spawn")
    @JvmStatic
    fun spawn(player: Player) {
        if (player.gameMode == GameMode.CREATIVE){
            player.teleport(Location(Bukkit.getWorld("world"), 0.5, 111.0, -7.5))

            return
        }

        if (player.isCombatTagged()) {
            player sendMessage "&cYou cannot use this command while in combat."
            return
        }

        player sendMessage "&fYou will be teleported to spawn in &d5 &fseconds.."

        object : BukkitRunnable() {
            var i = 5

            val x = player.location.x
            val z = player.location.z

            override fun run() {
                if (player.location.x != x || player.location.z != z){
                    player sendMessage "&cYou have moved therefore your spawn teleportation has been cancelled."
                    cancel()
                    return
                }

                if (i == 3){
                    player sendMessage "&fYou will be teleported to spawn in &d3 &fseconds.."
                } else if (i == 2){
                    player sendMessage "&fYou will be teleported to spawn in &d2 &fseconds.."
                } else if (i == 1){
                    player sendMessage "&fYou will be teleported to spawn in &d1 &fseconds.."
                }

                if (i <= 0){
                    player sendMessage "&aYou have been successfully teleported to spawn."

                    player.teleport(Location(Bukkit.getWorld("world"), 0.5, 111.0, -7.5))

                    cancel()
                    return
                }

                i--
            }
        }.runTaskTimer(KitPvP.instance, 0L, 20L)
    }
}