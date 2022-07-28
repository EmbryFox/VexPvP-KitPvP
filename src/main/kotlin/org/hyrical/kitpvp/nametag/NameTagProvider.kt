package org.hyrical.kitpvp.nametag

import com.lunarclient.bukkitapi.LunarClientAPI
import me.activated.core.plugin.AquaCoreAPI
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.Team
import org.hyrical.kitpvp.translate

object NameTagProvider : BukkitRunnable() {

    override fun run() {
        val lines: List<String> = ArrayList()
        for (player in Bukkit.getOnlinePlayers()) {
            Bukkit.getOnlinePlayers().forEach { viewer: Player ->
                LunarClientAPI.getInstance().overrideNametag(player, getNametag(player, viewer), viewer)
            }
        }
    }

    fun getNametag(player: Player, viewer: Player): List<String>? {
        val lines: MutableList<String> = ArrayList()
        val mm = AquaCoreAPI.INSTANCE.getPlayerData(player.uniqueId).isInStaffMode
        val color = AquaCoreAPI.INSTANCE.getPlayerData(player.uniqueId).nameColor
        val vanished = AquaCoreAPI.INSTANCE.getPlayerData(player.uniqueId).isVanished

        if (mm) {
            lines.add(translate("&7[ModMode]"))
        }
        
        lines.add(translate(if (vanished) "&7&o*" else color + player.name))

        return lines
    }

}