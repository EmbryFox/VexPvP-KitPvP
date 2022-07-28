package org.hyrical.kitpvp.scoreboard

import net.evilblock.cubed.scoreboard.ScoreGetter
import net.evilblock.cubed.scoreboard.ScoreboardHandler
import net.evilblock.cubed.scoreboard.TitleGetter
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.scoreboard.animation.type.LinkAnimation
import org.hyrical.kitpvp.scoreboard.animation.type.TitleAnimation
import org.hyrical.kitpvp.translate
import java.time.Instant
import java.util.*

object ScoreboardProvider {

    object Title : TitleGetter, Runnable {
        private var stage: Int = 0
        private var lastStage: Long = System.currentTimeMillis()

        override fun getTitle(player: Player): String {
            return if (ScoreboardConfig.titleAnimated) {
                ScoreboardConfig.titleFrames[stage].text
            } else {
                ScoreboardConfig.title
            }
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
            if (stage >= ScoreboardConfig.titleFrames.size) {
                stage = 0
            }

            val stageAt = ScoreboardConfig.titleFrames[stage]

            if (System.currentTimeMillis() - lastStage >= stageAt.delay) {
                lastStage = System.currentTimeMillis()

                if (stage + 1 >= ScoreboardConfig.titleFrames.size) {
                    stage = 0
                } else {
                    stage++
                }
            }
        }

    }

    object Scores : ScoreGetter {
        override fun getScores(scores: LinkedList<String>, player: Player) {
            val profile = player.getProfile()

            renderHeaderFooter(scores)
            scores.add(translate("&5&lPLAYER"))
            scores.add(translate(" &7" + Constants.DOT_SYMBOL + " &fKills: &d${profile.kills}"))
            scores.add(translate(" &7" + Constants.DOT_SYMBOL + " &fDeaths: &d${profile.deaths}"))
            scores.add(translate(" &7" + Constants.DOT_SYMBOL + " &fBalance: &d${profile.balance}"))
            scores.add(translate(" &7" + Constants.DOT_SYMBOL + " &fKillstreak: &d${profile.killstreak}"))
            scores.add(translate("&e"))
            scores.add(translate("&5&lSERVER"))
            scores.add(translate(" &7" + Constants.DOT_SYMBOL + " &fPlayers: &d${Bukkit.getOnlinePlayers().size}"))
            scores.add(translate(" &7" + Constants.DOT_SYMBOL + " &fJoins: &d${KitPvP.instance.config.getInt("joins")}"))
            scores.add(translate("&c"))

            renderHeaderFooter(scores)



        }

        private fun renderHeaderFooter(scores: MutableList<String>) {
            for ((index, line) in ScoreboardConfig.header.withIndex()) {
                scores.add(index, processPlaceholders(line))
            }

            for (line in ScoreboardConfig.footer) {
                scores.add(processPlaceholders(line))
            }
        }

        private fun processPlaceholders(line: String): String {
            var processed = line

            if (processed.contains("{currentLink}")) {
                val link = when (ScoreboardConfig.animationType) {
                    "SWITCH" -> {
                        TitleAnimation.render()
                    }
                    "TYPER" -> {
                        LinkAnimation.render()
                    }
                    else -> {
                        ""
                    }
                }

                processed = processed.replace("{currentLink}", link)
            }

            if (processed.contains("{currentDate}")) {
                processed = processed.replace("{currentDate}", TimeUtil.formatIntoFullCalendarString(Date.from(Instant.now())))
            }

            return processed
        }

    }
}