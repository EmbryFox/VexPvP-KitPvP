package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer
import net.kyori.adventure.platform.bukkit.MinecraftComponentSerializer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import org.hyrical.kitpvp.KitPvP

object AdCommand {

    @Command(["ad"], description = "Display an advertisement")
    @JvmStatic
    fun ad(player: Player) {
        //TODO: Implement advertisement
    }
}