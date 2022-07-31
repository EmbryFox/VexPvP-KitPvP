package org.hyrical.kitpvp.leaderboard

import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.profiles.ProfileService
import java.util.*
import kotlin.collections.ArrayList


object DeathLeaderboard : BukkitRunnable() {

    val cache: LinkedHashMap<UUID, Int> = linkedMapOf()

    fun load() {
        runTaskTimerAsynchronously(KitPvP.instance, 20L, 20L * 15L)
    }

    override fun run() {
        cache.clear()

        ProfileService.service.retrieveAll().forEach {
            cache[it.uuid] = it.deaths
        }
    }

    fun getLeaderboards(): ArrayList<Map.Entry<UUID, Int>> {
        val map = LinkedHashMap(cache)
        val list: ArrayList<Map.Entry<UUID, Int>> = arrayListOf()

        for (entry in map){
            list.add(entry)
        }

        list.sortWith { (_, value), (_, value1) ->
            // Comparing two entries by value
            // Substracting the entries
            (value
                    - value1)
        }

        list.reverse()

        return list
    }

}