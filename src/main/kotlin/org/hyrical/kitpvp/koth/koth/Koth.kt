package org.hyrical.kitpvp.koth.koth

import com.lunarclient.bukkitapi.LunarClientAPI
import com.lunarclient.bukkitapi.`object`.LCWaypoint
import org.bukkit.*
import org.bukkit.entity.Player
import org.hyrical.kitpvp.koth.Event
import org.hyrical.kitpvp.koth.koth.serializer.LocationSerializer
import org.hyrical.kitpvp.koth.storage.KothHandler
import org.hyrical.kitpvp.sendMessage
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
    var remainingTime: Int = duration

    override fun activate() {
        this.currentCapper = null

        KothHandler.activeKoth = this

        for (player in Bukkit.getOnlinePlayers()){
            LunarClientAPI.getInstance().sendWaypoint(player, LCWaypoint(name, LocationSerializer.itemFrom64(location), Color.PURPLE.asRGB(), true))

            player.sendTitle("&5&lKOTH", "$name &fhas been started!")
            player.playSound(player.location, Sound.ENDERDRAGON_HIT, 2.0f, 2.0f)
        }
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
                if (remainingTime % 10 == 0 && remainingTime > 1) {
                    capper sendMessage "&7[&5&lKOTH&7] &fYou are attempting to control &d${name}&f."
                }
                if (remainingTime <= 0) {
                    finishCapping(capper)
                }
                this.remainingTime--
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
        this.remainingTime = 0
        this.currentCapper = null
        KothHandler.activeKoth = null
    }

    fun save() {
        KothHandler.handler.storeAsync(name, this)
        KothHandler.koths[name] = this
    }


    fun onCap(location: Location): Boolean {
        val loc = LocationSerializer.itemFrom64(this.location)!!

        val xDistance: Int = abs(location.blockX - loc.blockX)
        val yDistance: Int = abs(location.blockY - loc.blockY)
        val zDistance: Int = abs(location.blockZ - loc.blockZ)
        return xDistance <= this.radius && yDistance <= 5 && zDistance <= this.radius
    }

    private fun resetCapTime() {
        currentCapper = null
        this.remainingTime = duration
    }

    private fun finishCapping(capper: Player) {

        Bukkit.getOnlinePlayers().forEach {
            it sendMessage "&7&m----------------------------------"
            it sendMessage "&5${capper.name} &fhas captured the &d${name} &fKOTH!"
            it sendMessage "&7&m----------------------------------"

            it.playSound(it.location, Sound.WITHER_SPAWN, 1.5f, 1.5f)
        }

        deactivate()
    }

    fun startCapping(player: Player) {
        if (currentCapper != null) {
            resetCapTime()
        }

        currentCapper = player.uniqueId
        this.remainingTime = duration
    }

}