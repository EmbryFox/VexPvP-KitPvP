package org.hyrical.kitpvp.utils

import org.apache.commons.lang.StringEscapeUtils
import org.apache.commons.lang.StringUtils
import org.bukkit.ChatColor
import java.util.*


object ProgressBarBuilder {
    private const val blocksToDisplay = 10
    private val blockChar = StringEscapeUtils.unescapeJava("█")[0]
    private val completedColor = ChatColor.GREEN.toString()
    private val uncompletedColor = ChatColor.GRAY.toString()

    fun build(percentage: Double): String {
        var percentage = percentage
        val blocks = arrayOfNulls<String>(blocksToDisplay)
        Arrays.fill(blocks, uncompletedColor + blockChar)
        if (percentage > 100.0) percentage = 100.0
        var i = 0
        while (i < percentage / 10.0) {
            blocks[i] = completedColor + blockChar
            i++
        }
        return StringUtils.join(blocks)
    }

    fun percentage(value: Int, goal: Int): Double {
        return if (value > goal) 100.0 else value / goal * 100.0
    }
}
