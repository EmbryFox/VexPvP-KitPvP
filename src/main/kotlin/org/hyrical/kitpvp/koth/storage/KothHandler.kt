package org.hyrical.kitpvp.koth.storage

import io.github.nosequel.data.DataStoreType
import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.koth.koth.Koth
import org.hyrical.kitpvp.koth.serializer.KothSerializer

object KothHandler {

    val handler = KitPvP.instance.dataHandler.createStoreType<String, String>(DataStoreType.FLATFILE)
    val koths = hashMapOf<String, Koth>()

    val activeKoth: Koth? = null

    fun load() {
        koths.clear()

        handler.retrieveAll().forEach {
            val koth = KothSerializer.kothFrom64(it)
            if (koth != null) {
                koths[koth.name] = koth
            }
        }

        object : BukkitRunnable() {
            override fun run() {
                activeKoth?.tick()
            }
        }.runTaskTimer(KitPvP.instance, 5L, 20L)
    }
}
