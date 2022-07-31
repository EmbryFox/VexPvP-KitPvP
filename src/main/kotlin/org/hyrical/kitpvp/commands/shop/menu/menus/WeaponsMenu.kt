package org.hyrical.kitpvp.commands.shop.menu.menus

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.ItemUtils
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.commands.shop.menu.buttons.BackButton
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate

class WeaponsMenu : Menu() {

    override fun getTitle(player: Player): String {
        return translate("Weapons")
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: HashMap<Int, Button> = hashMapOf()

        buttons[3] = createButton("Diamond Sword", Material.DIAMOND_SWORD, 1, 350)
        buttons[4] = createButton("Arrow", Material.ARROW, 64, 100)
        buttons[5] = createButton("Bow", Material.BOW, 1, 200)

        buttons[22] = BackButton()

        return buttons
    }

    private fun createButton(name: String, material: Material, amount: Int, price: Int) : Button {
        return object : Button(){
            override fun getButtonItem(player: Player): ItemStack {
                return ItemBuilder.of(material).name(translate(name))
                    .setLore(listOf(translate("&fPrice: &d$$price"), "", translate("&a&lLEFT-CLICK &ato purchase.")))
                    .build()
            }

            override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
                val profile = player.getProfile()

                if (profile.balance < price){
                    player.sendMessage(translate("&cYou don't have enough money to purchase this item."))
                    return
                }

                profile.balance -= price
                profile.save()

                player.inventory.addItem(ItemBuilder.of(material).amount(amount).build())

                player sendMessage "&aYou have successfully purchased the ${ItemUtils.getName(ItemStack(material))} &afor $${price}"
            }
        }
    }

    private fun createButton(item: ItemStack, amount: Int, price: Int) : Button {
        return object : Button(){
            override fun getButtonItem(player: Player): ItemStack {
                val newItemStack = ItemBuilder.copyOf(item).setLore(listOf(translate("&fPrice: &d$$price"), "", translate("&a&lLEFT-CLICK &ato purchase.")))
                    .build()

                if (item.type == Material.GOLDEN_APPLE && amount == 1){
                    newItemStack.durability = 1
                }

                return newItemStack
            }

            override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
                val profile = player.getProfile()

                if (profile.balance < price){
                    player.sendMessage(translate("&cYou don't have enough money to purchase this item."))
                    return
                }

                if (player.inventory.size + 1 > 36){
                    player sendMessage "&cYour inventory is currently full."
                    return
                }

                profile.balance -= price
                profile.save()

                player.inventory.addItem(item)

                player sendMessage "&aYou have successfully purchased the ${ItemUtils.getName(item)} &afor $${price}"
            }
        }
    }
}