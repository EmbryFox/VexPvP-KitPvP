package org.hyrical.kitpvp.kits.command

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.kits.Kit
import org.hyrical.kitpvp.kits.KitsService
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

class KitsCommand {

    @Command(["kits"], description = "View all available kits")
    fun kits(player: Player) {
        player.sendMessage("§aAvailable kits:")
        player.sendMessage("§a- §fKit 1")
        player.sendMessage("§a- §fKit 2")
        player.sendMessage("§a- §fKit 3")
    }

    @Command(["kit"], description = "Apply a kit")
    fun kit(player: Player, @Param("kit", "xzadsafaefreasrfaedfaerdfaedaedsadasdassdasdasd") kitName: String) {
        if (kitName == "xzadsafaefreasrfaedfaerdfaedaedsadasdassdasdasd") {
            kits(player)
            return
        }

        val kit = KitsService.kits[kitName]

        if (kit == null) {
            player sendMessage "&cKit not found"
            return
        }

        if (!player.hasPermission(kit.permission)) {
            player sendMessage "&cYou do not have permission to use this kit"
            return
        }

        val playerProfile = player.getProfile()

        if (kit.cooldown != "") {
            if (playerProfile.kitCooldowns[kit.name] != null && playerProfile.kitCooldowns[kit.name]!! <= System.currentTimeMillis()) {
                player sendMessage "&cYou are on cooldown for this kit"
                return
            }
        }

        if (player.inventory.size + kit.items.size > 36) {
            player sendMessage "&cYou do not have enough space in your inventory"
            return
        }

        player.inventory.addItem(*kit.items.toTypedArray())

        player sendMessage "&aYou have applied the kit &f${kit.name}"

        if (kit.cooldown != "") {
            playerProfile.kitCooldowns[kit.name] = System.currentTimeMillis() + TimeUtil.parseTime(kit.cooldown)
            playerProfile.save()
        }
    }

    @Command(["kit admin create", "kits admin create"], permission = "kitpvp.admin.create")
    fun kitsAdmin(player: Player, @Param("kit") kitName: String) {
        if (KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cKit already exists"
            return
        }

        val items = arrayListOf<ItemStack>()

        player.inventory.contents.forEach { item ->
            if (item != null) {
                items.add(item)
            }
        }

        player sendMessage "&aKit created"

        val kit = Kit(kitName, items = items)

        KitsService.kits[kitName] = kit
        KitsService.handler.storeAsync(kitName, kit)
    }

    @Command(["kit admin delete", "kits admin delete"], permission = "kitpvp.admin.delete")
    fun kitsAdminDelete(player: Player, @Param("kit") kitName: String) {
        if (!KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cKit not found"
            return
        }

        KitsService.kits.remove(kitName)
        KitsService.handler.deleteAsync(kitName)

        player sendMessage "&aKit deleted"
    }

    @Command(["kit admin set", "kits admin set"], permission = "kitpvp.admin.set")
    fun kitsAdminSet(player: Player, @Param("kit") kitName: String) {
        if (!KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cKit not found"
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
        kit.items.addAll(items)

        KitsService.kits[kitName] = kit
        KitsService.handler.storeAsync(kitName, kit)

        player.sendMessage("&aKit updated")
    }

    @Command(["kit admin cooldown", "kits admin cooldown"], permission = "kitpvp.admin.cooldown")
    fun kitsAdminCooldown(player: Player, @Param("kit") kitName: String, @Param("cooldown") cooldown: String) {
        if (!KitsService.kits.containsKey(kitName)) {
            player sendMessage "&cKit not found"
            return
        }

        TimeUtil.parseTime(cooldown)

        val kit = KitsService.kits[kitName]!!

        kit.cooldown = cooldown

        KitsService.kits[kitName] = kit
        KitsService.handler.storeAsync(kitName, kit)

        player sendMessage "&aUpdated kit cooldown"
    }
}