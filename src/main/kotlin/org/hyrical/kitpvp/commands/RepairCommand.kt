package org.hyrical.kitpvp.commands

import net.evilblock.cubed.command.Command
import org.bukkit.entity.Damageable
import org.bukkit.entity.Player
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage

object RepairCommand {

    @Command(["repair"])
    @JvmStatic
    fun repair(player: Player) {
        val item = player.inventory.getItem(player.inventory.heldItemSlot)

        if (item == null) {
            player sendMessage "&cYou must be holding an item to repair it."
            return
        }

        val profile = player.getProfile()

        if (profile.balance < 250) {
            player sendMessage "&cYou do not have enough money to repair this item."
            return
        }

        profile.balance -= 250
        profile.save()
        player sendMessage "&aYou have repaired your item for &6\$250&a."

        item.durability = 0

        // TOOD: Check if that actually works
    }
}