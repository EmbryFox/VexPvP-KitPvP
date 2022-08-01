package org.hyrical.kitpvp.commands.shop.menu.menus

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.ItemUtils
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.Potion
import org.bukkit.potion.PotionType
import org.hyrical.kitpvp.commands.shop.menu.buttons.BackButton
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.sendMessage
import org.hyrical.kitpvp.translate
import java.lang.IllegalArgumentException

class EnchantmentMenu : Menu() {

    override fun getTitle(player: Player): String {
        return "Enchantments"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: HashMap<Int, Button> = hashMapOf()

        // first row
        buttons[2] = createButton(Enchantment.DAMAGE_ALL, 1, 400)
        buttons[3] = createButton(Enchantment.DAMAGE_ALL, 2, 800)
        buttons[4] = createButton(Enchantment.DAMAGE_ALL, 3, 1200)
        buttons[5] = createButton(Enchantment.KNOCKBACK, 2, 900)
        buttons[6] = createButton(Enchantment.ARROW_INFINITE, 1, 1400)

        //second row
        buttons[11] = createButton(Enchantment.ARROW_DAMAGE, 1, 300)
        buttons[12] = createButton(Enchantment.ARROW_DAMAGE, 2, 600)
        buttons[13] = createButton(Enchantment.ARROW_DAMAGE, 3, 1000)
        buttons[14] = createButton(Enchantment.ARROW_KNOCKBACK, 2, 2000)
        buttons[15] = createButton(Enchantment.DURABILITY, 1, 650)

        // third row

        buttons[20] = createButton(Enchantment.DURABILITY, 2, 1300)
        buttons[21] = createButton(Enchantment.DURABILITY, 3, 3000)
        buttons[22] = createButton(Enchantment.PROTECTION_ENVIRONMENTAL, 1, 250)
        buttons[23] = createButton(Enchantment.PROTECTION_ENVIRONMENTAL, 2, 500)
        buttons[24] = createButton(Enchantment.PROTECTION_ENVIRONMENTAL, 3, 1000)

        // back

        buttons[40] = BackButton()

        return buttons
    }

    private fun createButton(type: Enchantment, tier: Int,
                             price: Int) : Button {

        return object : Button(){

            override fun getButtonItem(player: Player): ItemStack {
                return ItemBuilder.of(Material.ENCHANTED_BOOK).enchant(type, tier)
                    .setLore(listOf(translate("&fPrice: &d$$price"), "", translate("&7You must hold the item to enchant it."), "", translate("&a&lLEFT-CLICK &ato purchase."))).build()
            }

            override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
                val profile = player.getProfile()
                val itemInHand = player.itemInHand

                if (itemInHand == null){
                    player sendMessage "&cYou must be holding a piece of armor in your hand."
                    return
                }

                if (profile.balance < price){
                    player.sendMessage(translate("&cYou don't have enough money to purchase this item."))
                    return
                }

                if (type.canEnchantItem(itemInHand)){
                    itemInHand.addEnchantment(type, tier)

                    player.updateInventory()
                } else {
                    player sendMessage "&cYou can't enchant that item with this enchantment."
                    return
                }

                profile.balance -= price
                profile.save()

                player sendMessage "&aYou have successfully purchased " +
                        "an enchantment &afor $${price}"
            }
        }
    }

}