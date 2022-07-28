package org.hyrical.kitpvp.koth.storage

import io.github.nosequel.data.DataStoreType
import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.koth.koth.Koth

object KothHandler {

    val handler = KitPvP.instance.dataHandler.createStoreType<String, Koth>(DataStoreType.FLATFILE)
    val koths = hashMapOf<String, Koth>()

    var activeKoth: Koth? = null

    fun load() {
        koths.clear()

        handler.retrieveAll().forEach {
            koths[it.name] = it
        }

        object : BukkitRunnable() {
            override fun run() {
                activeKoth?.tick()
            }
        }.runTaskTimer(KitPvP.instance, 5L, 20L)
    }
}
