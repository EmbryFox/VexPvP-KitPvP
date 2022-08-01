package org.hyrical.kitpvp.koth.commands

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.entity.Player
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.koth.Event
import org.hyrical.kitpvp.koth.koth.Koth
import org.hyrical.kitpvp.koth.koth.serializer.LocationSerializer
import org.hyrical.kitpvp.koth.storage.KothHandler
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate
import java.io.File

object KothCommands {

    @Command(["koth create"], permission = "kitpvp.koth.create")
    @JvmStatic
    fun create(player: Player, @Param("name") name: String) {
        if (KothHandler.koths.containsKey(name)) {
            player sendMessage "&cThat KoTH already exists."
            return
        }

        val koth = Koth(name, false, Event.EventType.KOTH, LocationSerializer.itemTo64(player.location)!!, 3)
        koth.save()

        player sendMessage "&aThat KoTH has been created."
    }

    @Command(["koth delete"], permission = "kitpvp.koth.delete")
    @JvmStatic
    fun delete(player: Player, @Param("name") name: String) {
        name.lowercase()
        if (!KothHandler.koths.containsKey(name.lowercase())) {
            player sendMessage "&cThat KoTH doesn't exist."
            return
        }

        val file = File(KitPvP.instance.dataFolder.absolutePath + "/koth/$name")
        file.delete()

        KothHandler.koths.remove(name)

        player sendMessage "&aYou have deleted the KoTH ${name.capitalize()}."
    }

    @Command(["koth activate"], permission = "kitpvp.koth.activate")
    @JvmStatic
    fun activate(player: Player, @Param("name") name: String) {
        name.lowercase()
        if (KothHandler.activeKoth != null) {
            player sendMessage "&cThere is already an active KoTH."
            return
        }

        val koth = KothHandler.koths[name.lowercase()] ?: run {
            player sendMessage "&cThat KoTH doesn't exist."
            return
        }

        koth.activate()

        player sendMessage "&aYou have activated the ${koth.name}&a."
    }

    @Command(["koth deactivate"], permission = "kitpvp.koth.deactivate")
    @JvmStatic
    fun deactivate(player: Player, @Param("name") name: String) {
        name.lowercase()
        if (KothHandler.activeKoth == null) {
            player sendMessage "&cThere isn't already an active KoTH."
            return
        }

        val koth = KothHandler.koths[name] ?: run {
            player sendMessage "&cThat KoTH doesn't exist."
            return
        }

        koth.deactivate()
        player sendMessage "&aYou have deactivated the ${koth.name}&a."
    }

    @Command(["koth dist", "koth distance"], permission = "kitpvp.koth.distance")
    @JvmStatic
    fun distance(player: Player, @Param("name") name: String, @Param("distance") distance: Int) {
        name.lowercase()
        val koth = KothHandler.koths[name] ?: run {
            player sendMessage "&cThat KoTH doesn't exist."
            return
        }

        koth.radius = distance
        koth.save()

        player sendMessage "&aYou have set the distance for ${koth.name} to $distance&a."
    }

    @Command(["koth time"], permission = "kitpvp.koth.time")
    @JvmStatic
    fun time(player: Player, @Param("name") name: String, @Param("time") time: String) {

        name.lowercase()

        val parsedTime: Int

        try {
            parsedTime = TimeUtil.parseTime(time)
        } catch (e: Exception){
            player sendMessage "&cThe time provided is invalid."
            return
        }

        val koth = KothHandler.koths[name] ?: run {
            player sendMessage "&cThat KoTH doesn't exist."
            return
        }

        koth.duration = parsedTime

        if (koth.active){
            koth.remainingTime = parsedTime
        }

        koth.save()

        player sendMessage "&aYou have set the time for ${koth.name} to ${TimeUtil.formatIntoMMSS(parsedTime)}&a."
    }

    @Command(["koth loc", "koth location"], permission = "kitpvp.koth.loc")
    @JvmStatic
    fun loc(player: Player, @Param("name") name: String) {
        name.lowercase()
        val koth = KothHandler.koths[name] ?: run {
            player sendMessage "&cThat KoTH doesn't exist."
            return
        }

        koth.location = LocationSerializer.itemTo64(player.location)!!
        koth.save()

        player sendMessage "&aYou have set the location for ${koth.name} to your location."
    }

    @Command(["koth tp", "koth teleport"], permission = "kitpvp.koth.teleport")
    @JvmStatic
    fun teleport(player: Player, @Param("name") name: String) {
        name.lowercase()
        val koth = KothHandler.koths[name] ?: run {
            player sendMessage "&cThat KoTH doesn't exist."
            return
        }

        player.teleport(LocationSerializer.itemFrom64(koth.location))
    }

    @Command(["koth list"], permission = "kitpvp.koth.teleport")
    @JvmStatic
    fun list(player: Player) {
        for (koth in KothHandler.koths){
            player.sendMessage(translate("&d${koth.value.name} &7- &f${TimeUtil.formatIntoMMSS(koth.value.duration)}"))
        }
    }
}