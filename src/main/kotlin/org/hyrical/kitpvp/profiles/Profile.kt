package org.hyrical.kitpvp.profiles

import org.bukkit.entity.Player
import java.util.*
import kotlin.math.floor

data class Profile(
    val uuid: UUID,
    var kills: Int = 0,
    var deaths: Int = 0,
    var killstreak: Int = 0,
    var balance: Double = 0.0,
    var bounty: Double = 0.0,
    var canSeeKillMessages: Boolean = true,
    var premiumPass: Boolean = false,
    var kitCooldowns: MutableMap<String, Long> = mutableMapOf(),
    var experience: Long = 0,
    var gems: Int = 0
) {
    fun save() {
        ProfileService.service.storeAsync(uuid, this)
        ProfileService.cache[uuid] = this
    }

    fun getKDR(): String {
        if (deaths == 0) {
            return "0.0"
        }

        return (kills.toDouble() / deaths).toString()
    }

    fun getLevel(): Int {
        return floor((experience.toFloat() / 250.toFloat()).toDouble()).toInt()
    }

    fun getNextLevelExperience(): Int {
        return (-(experience - (this.getLevel()+1)*250)).toInt();
    }
}

fun Player.canSeeKillMessages(): Boolean {
    return ProfileService.cache[uniqueId]!!.canSeeKillMessages
}

fun Player.getProfile(): Profile {
    return ProfileService.cache[uniqueId] ?: ProfileService.service.retrieve(uniqueId) ?: Profile(uniqueId)
}