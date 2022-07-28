package org.hyrical.kitpvp.announcer

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.translate

object Announcer : BukkitRunnable() {

    private var i = 0

    private val announcements: MutableList<String> = mutableListOf()

    fun load(config: FileConfiguration) {
        announcements.clear()

        for (i in 0 until config.getStringList("announcements").size) {
            announcements.add(config.getStringList("announcements")[i].replace("%nl%", "\n"))
        }

        Announcer.runTaskTimer(KitPvP.instance, 20L, config.getInt("announcer-delay").toLong())
    }

    /**
     * When an object implementing interface `Runnable` is used
     * to create a thread, starting the thread causes the object's
     * `run` method to be called in that separately executing
     * thread.
     *
     *
     * The general contract of the method `run` is that it may
     * take any action whatsoever.
     *
     * @see java.lang.Thread.run
     */
    override fun run() {
        val message = announcements[i]

        Bukkit.broadcastMessage(translate(message))

        if (i == announcements.size - 1) {
            i = 0
        } else {
            i++
        }
    }
}