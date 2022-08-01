package org.hyrical.kitpvp.commands.shop.menu.menus

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.ItemUtils
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

class PotionMenu : Menu() {

    override fun getTitle(player: Player): String {
        return translate("Potions")
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons: HashMap<Int, Button> = hashMapOf()

        buttons[2] = createButton(PotionType.REGEN, true, 1, 90,500)
        buttons[3] = createButton(PotionType.SPEED, true, 1, 60 * 8, 750)
        buttons[4] = createButton(PotionType.SPEED, true, 2, 90, 1500)
        buttons[5] = createButton(PotionType.INSTANT_HEAL, true, 1, 0, 1500)
        buttons[6] = createButton(PotionType.INSTANT_HEAL, true, 2, 0, 1500)

        buttons[22] = BackButton()

        return buttons
    }

    private fun createButton(type: PotionType, splash: Boolean, tier: Int,
                             seconds: Int, price: Int) : Button {
        val potion = Potion(type, tier)

        potion.isSplash = splash
        if (seconds != 0){
            potion.type.effectType.createEffect(20 * seconds, tier)
        }

        return object : Button(){

            override fun getButtonItem(player: Player): ItemStack {
                val itemStackPotion = ItemBuilder.copyOf(potion.toItemStack(1))
                    .setLore(listOf(translate("&fPrice: &d$$price"), "", translate("&a&lLEFT-CLICK &ato purchase."))).build()

                return itemStackPotion
            }

            override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
                val profile = player.getProfile()

                if (profile.balance < price){
                    player.sendMessage(translate("&cYou don't have enough money to purchase this item."))
                    return
                }

                profile.balance -= price
                profile.save()

                player.inventory.addItem(potion.toItemStack(1))

                player sendMessage "&aYou have successfully purchased the " +
                        "${ItemUtils.getName(potion.toItemStack(1))} &afor $${price}"
            }
        }
    }
}