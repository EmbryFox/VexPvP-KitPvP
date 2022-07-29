package org.hyrical.kitpvp.leaderboard

import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.profiles.ProfileService
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap


object KillLeaderboard : BukkitRunnable() {

    val cache: LinkedHashMap<UUID, Int> = linkedMapOf()

    fun load() {
        runTaskTimerAsynchronously(KitPvP.instance, 20L, 20L * 60L * 5L)
    }

    override fun run() {
        cache.clear()

        ProfileService.service.retrieveAll().forEach {
            cache[it.uuid] = it.kills
        }
    }

    fun getSortedMap(): Map<UUID, Int> {
        return cache.toList().sortedBy { (_, v) -> v }.toMap()
    }
}