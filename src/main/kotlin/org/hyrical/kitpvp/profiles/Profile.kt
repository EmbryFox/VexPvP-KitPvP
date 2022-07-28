package org.hyrical.kitpvp.profiles

import org.bukkit.entity.Player
import org.hyrical.kitpvp.translate
import java.util.UUID

data class Profile(
    val uuid: UUID,
    var kills: Int = 0,
    var deaths: Int = 0,
    var killstreak: Int = 0,
    var balance: Double = 0.0,
    var canSeeKillMessages: Boolean = true,
    var premiumPass: Boolean = false,
    var kitCooldowns: MutableMap<String, Long> = mutableMapOf(),
) {
    fun save() {
        ProfileService.service.storeAsync(uuid, this)
        ProfileService.cache[uuid] = this
    }

    fun getKDR(): String {
        if (deaths == 0) {
            return "0"
        }

        return (kills.toDouble() / deaths).toString()
    }
}

fun Player.canSeeKillMessages(): Boolean {
    return ProfileService.cache[uniqueId]?.canSeeKillMessages ?: false
}

fun Player.getProfile(): Profile {
    return ProfileService.cache[uniqueId] ?: ProfileService.service.retrieve(uniqueId) ?: Profile(uniqueId)
}