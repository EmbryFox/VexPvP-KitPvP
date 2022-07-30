package org.hyrical.kitpvp.kits.command

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.kits.Kit
import org.hyrical.kitpvp.kits.KitsService
import org.hyrical.kitpvp.kits.serializer.ItemStackSerializer
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate
import java.io.File
import java.util.*
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

object KitsCommand {

    @Command(["kits"], description = "View all available kits")
    @JvmStatic
    fun kits(player: Player, @Param("kit", "fid2ieu923t0wi0adjifwjnbdjivfedjnerwjwje") kitName: String) {
        if (kitName != "fid2ieu923t0wi0adjifwjnbdjivfedjnerwjwje"){
            val kit = KitsService.kits[kitName]

            if (kit == null){
                player sendMessage "&cThat kit doesn't exist."
                return
            }

            kitCommand(player, kitName)

            return
        }

        val profile = player.getProfile()
        var i = 0

        for (kit in KitsService.kits){
            if (player.hasPermission(kit.value.permission)){
                i++
            }
        }

        player sendMessage("&7&m---------------------------------")
        player sendMessage(translate("&5Available Kits " + "&7(${i})" + "&f:"))
        player sendMessage("&7&m---------------------------------")

        for (kit in KitsService.kits) {
            if (player.hasPermission(kit.value.permission)) {
                player sendMessage "&d${kit.value.name.capitalize()} &7- " +
                        if (isOnCooldown(player, kit.value)) "&fCooldown: &d${
                            TimeUtil.formatIntoMMSS(((profile.kitCooldowns[kit.value.name]!! - System.currentTimeMillis()) / 1000).toInt())}" else "&fNot on cooldown"
            }
        }
        player sendMessage("&7&m---------------------------------")
    }

    @OptIn(ExperimentalTime::class)
    @Command(["kit"], description = "Apply a kit")
    @JvmStatic
    fun kitCommand(player: Player, @Param("kit", "xzadsafaefreasrfaedfaerdfaedaedsadasdassdasdasd") kitName: String) {
        if (kitName == "xzadsafaefreasrfaedfaerdfaedaedsadasdassdasdasd") {
            kits(player, "fid2ieu923t0wi0adjifwjnbdjivfedjnerwjwje")
            return
        }

        val kit = KitsService.kits[kitName]

        if (kit == null) {
            player sendMessage "&cNo kit with the name ${kitName.capitalize()} found."
            return
        }

        if (!player.hasPermission(kit.permission)) {
            player sendMessage "&cYou do not have permission to use this kit."
            return
        }

        val playerProfile = player.getProfile()

        if (kit.cooldown != "") {
            if (playerProfile.kitCooldowns[kit.name] != null) {
                if (isOnCooldown(player, kit)) {
                    player sendMessage "&cYou are on cooldown for this kit for another &l${TimeUtil.formatIntoDetailedString((((player.getProfile().kitCooldowns[kit.name]!! - System.currentTimeMillis()) / 1000).toInt()))}&c."
                    return
                } else {
                    playerProfile.kitCooldowns.remove(kit.name.lowercase())
                }
            }
        }

        val kitItems = kit.items.map { ItemStackSerializer.itemFrom64(it)!! }

        if (player.inventory.contents.filterNotNull().size + kitItems.size > 36) {
            player sendMessage "&cYou do not have enough space in your inventory."
            return
        }

        player.inventory.addItem(*kit.items.map { ItemStackSerializer.itemFrom64(it)!! }.toTypedArray())

        player sendMessage "&aYou have applied the kit &f${kit.name.capitalize()}&a."

        if (kit.cooldown != "") {
            playerProfile.kitCooldowns[kit.name] = System.currentTimeMillis() + TimeUtil.parseTime(kit.cooldown) * 1000
            playerProfile.save()
        }
    }

    @Command(["kit admin create", "kits admin create"], permission = "kitpvp.admin.create")
    @JvmStatic
    fun kitsAdmin(player: Player, @Param("kit") kitName: String) {
        kitName.lowercase()
        if (KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cThe kit $kitName already exists."
            return
        }

        val items = arrayListOf<String>()

        player.inventory.contents.forEach { item ->
            if (item != null) {
                items.add(ItemStackSerializer.itemTo64(item)!!)
            }
        }

        player.inventory.armorContents.forEach { item ->
            if (item != null){
                items.add(ItemStackSerializer.itemTo64(item)!!)
            }
        }

        player sendMessage "&aThat kit has been created."

        val kit = Kit(kitName, items = items)

        KitsService.kits[kitName] = kit
        KitsService.handler.storeAsync(kitName, kit)
    }

    @Command(["kit admin delete", "kits admin delete"], permission = "kitpvp.admin.delete")
    @JvmStatic
    fun kitsAdminDelete(player: Player, @Param("kit") kitName: String) {
        kitName.lowercase()
        if (!KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cNo kit with the name ${kitName.uppercase()} found."
            return
        }

        val file = File(KitPvP.instance.dataFolder.absolutePath + "/kit/${kitName}")
        file.delete()

        KitsService.kits.remove(kitName)

        player sendMessage "&cThat kit has been deleted."
    }

    @Command(["kit admin set", "kits admin set"], permission = "kitpvp.admin.set")
    @JvmStatic
    fun kitsAdminSet(player: Player, @Param("kit") kitName: String) {
        kitName.lowercase()
        if (!KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cNo kit with the name ${kitName.uppercase()} found."
            return
        }

        val items = arrayListOf<ItemStack>()

        player.inventory.contents.forEach { item ->
            if (item != null) {
                items.add(item)
            }
        }

        val kit = KitsService.kits[kitName]!!

        kit.items.clear()
        kit.items.addAll(items.map { ItemStackSerializer.itemTo64(it)!! })

        KitsService.kits[kitName] = kit
        KitsService.handler.storeAsync(kitName, kit)

        player sendMessage "&aThe kit's contents have been updated."
    }

    @Command(["kit admin cooldown", "kits admin cooldown"], permission = "kitpvp.admin.cooldown")
    @JvmStatic
    fun kitsAdminCooldown(player: Player, @Param("kit") kitName: String, @Param("cooldown") cooldown: String) {
        kitName.lowercase()
        if (!KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cNo kit with the name ${kitName.capitalize()} found."
            return
        }

        val kit = KitsService.kits[kitName]!!

        kit.cooldown = cooldown

        KitsService.kits[kitName] = kit
        KitsService.handler.storeAsync(kitName, kit)

        player sendMessage "&aThe kit cooldown has been updated to &l${TimeUtil.formatIntoDetailedString(TimeUtil.parseTime(cooldown))}&a."
    }

    @Command(["kit admin cooldown reset", "kits admin cooldown reset"], permission = "kitpvp.admin.cooldown.reset")
    @JvmStatic
    fun kitsAdminCooldownReset(player: Player, @Param("kit") kitName: String) {
        kitName.lowercase()
        if (!KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cNo kit with the name ${kitName.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }} found."
            return
        }

        val kit = KitsService.kits[kitName]!!

        kit.cooldown = ""

        KitsService.kits[kitName] = kit
        KitsService.handler.storeAsync(kitName, kit)

        player sendMessage "&aThe kit cooldown has been reset."
    }

    fun isOnCooldown(player: Player, kit: Kit): Boolean {
        val playerProfile = player.getProfile()

        if (playerProfile.kitCooldowns[kit.name.lowercase()] == null) return false

        return (playerProfile.kitCooldowns[kit.name.lowercase()]!! > System.currentTimeMillis())
    }
}