/*
 * Copyright (c) 2020. Joel Evans
 *
 * Use and or redistribution of compiled JAR file and or source code is permitted only if given
 * explicit permission from original author: Joel Evans
 */

package org.hyrical.kitpvp.scoreboard.animation.type

import org.hyrical.kitpvp.scoreboard.ScoreboardConfig

object TitleAnimation : Runnable {

    private var index: Int = 0
    private var lastChange: Long = System.currentTimeMillis()

    override fun run() {
        if (System.currentTimeMillis() - lastChange >= 3_000L) {
            lastChange = System.currentTimeMillis()

            if (index + 1 >= ScoreboardConfig.links.size) {
                index = 0
            } else {
                index++
            }
        }
    }

    @JvmStatic
    fun render(): String {
        return ScoreboardConfig.links[index]
    }

}