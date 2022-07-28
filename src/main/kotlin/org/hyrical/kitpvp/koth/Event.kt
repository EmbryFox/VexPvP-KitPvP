package org.hyrical.kitpvp.koth

import org.bukkit.Location

interface Event {

    var name: String

    var active: Boolean

    var type: EventType

    var location: String

    var radius: Int

    fun activate()

    fun tick()

    fun deactivate()


    @Suppress("SpellCheckingInspection")
    enum class EventType {
        KOTH
    }

}