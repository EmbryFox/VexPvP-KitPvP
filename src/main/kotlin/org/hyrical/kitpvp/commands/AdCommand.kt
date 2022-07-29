package org.hyrical.kitpvp.commands

import mkremins.fanciful.FancyMessage
import net.evilblock.cubed.command.Command
import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer
import net.kyori.adventure.platform.bukkit.MinecraftComponentSerializer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.sendMessage

object AdCommand {

    @Command(["ad", "advertise"], description = "Display an advertisement")
    @JvmStatic
    fun ad(player: Player) {
        val member: FancyMessage = FancyMessage()
        val vip: FancyMessage = FancyMessage()
        val patron: FancyMessage = FancyMessage()
        val legend: FancyMessage = FancyMessage()

        player sendMessage "&7&m-----------------------------"

        //message.text("")

    }
}