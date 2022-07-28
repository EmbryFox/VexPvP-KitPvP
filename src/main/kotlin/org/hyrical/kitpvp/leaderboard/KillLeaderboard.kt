package org.hyrical.kitpvp.leaderboard

import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.profiles.ProfileService
import java.util.*

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

        val list: LinkedList<Map.Entry<UUID, Int>> = LinkedList<Map.Entry<UUID, Int>>(cache.entries)

        list.sortWith { (_, value): Map.Entry<UUID?, Int>, (_, value1): Map.Entry<UUID?, Int> ->
            value1.compareTo(
                value
            )
        }

        val sortedHashMap: LinkedHashMap<UUID, Int> = LinkedHashMap<UUID, Int>()

        list.forEach {
            sortedHashMap[it.key] = it.value
        }

        cache.putAll(sortedHashMap)
    }
}