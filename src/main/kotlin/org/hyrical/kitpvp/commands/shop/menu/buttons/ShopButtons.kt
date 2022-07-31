package org.hyrical.kitpvp.commands.shop.menu.buttons

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.Potion
import org.bukkit.potion.PotionType
import org.hyrical.kitpvp.translate

class ShopButtons {
}

class ArmorButton : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.DIAMOND_CHESTPLATE).name("&dArmor").build()
    }

    override fun getDescription(player: Player): List<String> {
        return listOf(translate("&7Click this button to"), translate("&7open up the armorer!"))
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        // open inventory
    }

}

class PotionButton : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        val potion = Potion(PotionType.INSTANT_HEAL, 2)
        potion.isSplash = true

        return potion.toItemStack(1)
    }

    override fun getDescription(player: Player): List<String> {
        return listOf(translate("&7Click this button to"), translate("&7open up the potions!"))
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        // open inventory
    }

}

class MiscButton : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.FISHING_ROD).name("&dMisc").build()
    }

    override fun getDescription(player: Player): List<String> {
        return listOf(translate("&7Click this button to"), translate("&7open up the Misc!"))
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        // open inventory
    }

}

class WeaponsButton : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.DIAMOND_SWORD).name("&dWeapons").build()
    }

    override fun getDescription(player: Player): List<String> {
        return listOf(translate("&7Click this button to"), translate("&7open up the weapons!"))
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        // open inventory
    }

}

class EnchantmentButton : Button() {
    override fun getButtonItem(player: Player): ItemStack {
        return ItemBuilder.of(Material.ENCHANTMENT_TABLE).name("&dEnchantments").build()
    }

    override fun getDescription(player: Player): List<String> {
        return listOf(translate("&7Click this button to"), translate("&7open up the enchanter!"))
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        // open inventory
    }

}