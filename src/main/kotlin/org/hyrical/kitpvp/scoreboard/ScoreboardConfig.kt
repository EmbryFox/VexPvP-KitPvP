package org.hyrical.kitpvp.scoreboard

import org.bukkit.ChatColor
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.scoreboard.animation.AnimationFrame

object ScoreboardConfig {

    var title: String = ""
    var titleAnimated: Boolean = false
    var titleFrames: List<AnimationFrame> = arrayListOf()

    var links: List<String> = arrayListOf()
    var header: List<String> = arrayListOf()
    var footer: List<String> = arrayListOf()

    var animationType: String = ""
    var typerInterval: Long = 0L
    var typerPause: Long = 0L

    fun load() {
        links = arrayListOf()
        header = arrayListOf()
        footer = arrayListOf()

        titleFrames = arrayListOf()

        val config = KitPvP.instance.config

        title = config.getString("scoreboard.title.static", "")
        titleAnimated = config.getBoolean("scoreboard.title.animated", true)

        (config.getList("scoreboard.title.animation") as List<Map<String, Any>>).let { frames ->
            titleFrames = frames.map { frame ->
                AnimationFrame(
                    text = ChatColor.translateAlternateColorCodes('&', frame["text"] as String),
                    delay = (frame["delay"] as Int).toLong()
                )
            }
        }

        links = config.getStringList("scoreboard.links").map {
            ChatColor.translateAlternateColorCodes('&', it)
        }

        header = config.getStringList("scoreboard.header").map {
            ChatColor.translateAlternateColorCodes('&', it)
        }

        footer = config.getStringList("scoreboard.footer").map {
            ChatColor.translateAlternateColorCodes('&', it)
        }

        animationType = config.getString("scoreboard.links-animation.type")
        typerInterval = config.getLong("scoreboard.links-animation.typer-interval")
        typerPause = config.getLong("scoreboard.links-animation.typer-pause")
    }

}