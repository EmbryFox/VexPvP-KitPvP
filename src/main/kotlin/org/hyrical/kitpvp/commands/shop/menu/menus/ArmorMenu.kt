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

class ArmorMenu : Menu() {

    override fun getTitle(player: Player): String {
        return translate("Armor")
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: HashMap<Int, Button> = hashMapOf()

        buttons[4] = createButton(Material.DIAMOND_HELMET, 350)
        buttons[13] = createButton(Material.DIAMOND_CHESTPLATE, 450)
        buttons[22] = createButton(Material.DIAMOND_LEGGINGS, 400)
        buttons[31] = createButton(Material.DIAMOND_BOOTS, 300)

        buttons[49] = BackButton()

        return buttons
    }

    private fun createButton(material: Material, price: Int) : Button {
        return object : Button(){
            override fun getButtonItem(player: Player): ItemStack {
                return ItemBuilder.of(material).name(translate("&bDiamond Armor"))
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

                player.inventory.addItem(ItemStack(material))

                player sendMessage "&aYou have successfully purchased the ${ItemUtils.getName(ItemStack(material))} &afor $${price}"
            }
        }
    }
}