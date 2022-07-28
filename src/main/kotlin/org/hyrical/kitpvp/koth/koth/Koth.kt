package org.hyrical.kitpvp.koth.koth

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.hyrical.kitpvp.koth.Event
import org.hyrical.kitpvp.koth.koth.serializer.LocationSerializer
import org.hyrical.kitpvp.koth.storage.KothHandler
import java.util.*
import kotlin.math.abs

class Koth(
           override var name: String,
           override var active: Boolean,
           override var type: Event.EventType,
           override var location: String,
           override var radius: Int
) : Event {

    var currentCapper: UUID? = null

    var duration: Int = 60 * 3

    override fun activate() {
        this.duration = 60 * 3
        this.currentCapper = null
    }

    override fun tick() {
        if (currentCapper != null) {
            val capper: Player = Bukkit.getPlayer(currentCapper)!!
            if (!onCap(capper.location) || capper.isDead || capper.gameMode != GameMode.SURVIVAL || capper.hasMetadata(
                    "invisible"
                )
            ) {
                resetCapTime()
            } else {
                if (duration % 10 == 0 && duration > 1) {
                    capper.sendMessage(ChatColor.GOLD.toString() + "[KingOfTheHill]" + ChatColor.YELLOW + " Attempting to control " + ChatColor.BLUE + name + ChatColor.YELLOW + ".")
                }
                if (duration <= 0) {
                    finishCapping()
                }
                this.duration--
            }
        } else {
            val onCap: MutableList<Player?> = ArrayList()
            for (player in Bukkit.getOnlinePlayers()) {
                if (onCap(player.location) && !player.isDead && player.gameMode == GameMode.SURVIVAL && !player.hasMetadata(
                        "invisible"
                    )
                ) {
                    onCap.add(player)
                }
            }
            onCap.shuffle()
            if (onCap.size != 0) {
                startCapping(onCap[0]!!)
            }
        }
    }

    override fun deactivate() {
        if (!active) return


    }

    fun save() {
        KothHandler.handler.storeAsync(name, this)
        KothHandler.koths[name] = this
    }


    fun onCap(location: Location): Boolean {
        if (!location.world.name.equals(location.world)) return false

        val loc = LocationSerializer.itemFrom64(this.location)!!

        val xDistance: Int = abs(location.blockX - loc.blockX)
        val yDistance: Int = abs(location.blockY - loc.blockY)
        val zDistance: Int = abs(location.blockZ - loc.blockZ)
        return xDistance <= this.radius && yDistance <= 5 && zDistance <= this.radius
    }

    private fun resetCapTime() {
        currentCapper = null
        this.duration = 60 * 3
    }

    private fun finishCapping() {
        deactivate()
    }

    fun startCapping(player: Player) {
        if (currentCapper != null) {
            resetCapTime()
        }

        currentCapper = player.uniqueId
        this.duration = 60 * 3
    }

}