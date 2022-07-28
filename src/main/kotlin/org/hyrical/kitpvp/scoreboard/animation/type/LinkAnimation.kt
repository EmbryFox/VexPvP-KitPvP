package org.hyrical.kitpvp.scoreboard.animation.type

import org.hyrical.kitpvp.scoreboard.ScoreboardConfig

object LinkAnimation : Runnable {

    private var textIndex: Int = 0
    private var letterIndex: Int = 0

    private var appendAt: Long? = null
    private var pausedAt: Long? = null

    private var rendered: String = ""

    override fun run() {
        if (ScoreboardConfig.animationType != "TYPER") {
            return
        }

        if (pausedAt == null) {
            val currentText = ScoreboardConfig.links[textIndex]

            if (appendAt == null || System.currentTimeMillis() >= appendAt!! + ScoreboardConfig.typerInterval) {
                rendered += currentText[++letterIndex]
                appendAt = System.currentTimeMillis()

                if (letterIndex >= currentText.length - 1) {
                    pausedAt = System.currentTimeMillis()
                }
            }
        } else {
            if (System.currentTimeMillis() >= pausedAt!! + ScoreboardConfig.typerPause) {
                letterIndex = 0
                pausedAt = null
                rendered = ""

                if (textIndex++ >= ScoreboardConfig.links.size - 1) {
                    textIndex = 0
                }
            }
        }
    }

    fun render(): String {
        return rendered
    }

}