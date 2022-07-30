package org.hyrical.kitpvp.nametag

import com.lunarclient.bukkitapi.LunarClientAPI
import me.activated.core.plugin.AquaCoreAPI
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.Team
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

object NameTagProvider : BukkitRunnable() {

    override fun run() {
        for (player in Bukkit.getOnlinePlayers()) {
            Bukkit.getOnlinePlayers().forEach { viewer: Player ->
                LunarClientAPI.getInstance().overrideNametag(player, getNametag(player), viewer)
            }
        }
    }

    private fun getNametag(player: Player): List<String> {
        val lines: MutableList<String> = ArrayList()

        val profile = player.getProfile()
        val aProfile = AquaCoreAPI.INSTANCE.getPlayerData(player.uniqueId)

        if (aProfile.isInStaffMode) {
            lines.add(translate("&7[ModMode]"))
        }

        if (profile.bounty != 0.0){
            lines.add(translate("&fBounty: &d${NumberFormat.getInstance(Locale.US)
                .format(profile.bounty)}"))
        }

        lines.add(translate(if (aProfile.isVanished) "&7&o* " + aProfile.highestRank.color + player.name else aProfile.highestRank.color.toString() + player.name))

        return lines
    }

}