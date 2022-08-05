package org.hyrical.kitpvp.skydrops.task

import com.lunarclient.bukkitapi.LunarClientAPI
import com.lunarclient.bukkitapi.`object`.LCWaypoint
import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.skydrops.SkyDropHandler
import org.hyrical.kitpvp.translate
import java.awt.Color

class SkyDropTask : BukkitRunnable() {
    override fun run() {
        SkyDropHandler.spawnRandom()

        val location = SkyDropHandler.currentSkyDropLocation!!

        Bukkit.broadcastMessage(" ")
        Bukkit.broadcastMessage(
            translate("&7[&5${Constants.EXP_SYMBOL}&7] &fA &dSky Drop " +
                    "&fhas spawned at &d${location.x}&7, &d${location.z}&f!")
        )
        Bukkit.broadcastMessage(" ")

        Bukkit.getOnlinePlayers().forEach {
            LunarClientAPI.getInstance().sendWaypoint(it,
                LCWaypoint(
                    "Sky Drop",
                    location,
                    Color.RED.rgb,
                    true))
        }
    }
}