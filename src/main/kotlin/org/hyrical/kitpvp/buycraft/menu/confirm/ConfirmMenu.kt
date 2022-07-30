package org.hyrical.kitpvp.buycraft.menu.confirm

import net.evilblock.cubed.Cubed
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.NumberButton
import org.bukkit.Material
import org.bukkit.entity.Player
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.profiles.getProfile
import org.hyrical.kitpvp.translate
import java.util.HashMap

class ConfirmMenu(
    val pricePerItem: Int,
    val player: Player,
    val callback: (Boolean, Int) -> Unit
) : Menu() {

    init {
        updateAfterClick = true
    }

    var called: Boolean = false
    var amount: Int = 1

    override fun getTitle(player: Player): String {
        return translate("Store ➥ Checkout")
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons = HashMap<Int, Button>()

        buttons[2] = BooleanButton(this, true, confirm = true)
        buttons[6] = BooleanButton(this, false, confirm = true)

        buttons[4] = object : NumberButton(amount, IntRange(1, 500)) {
            override fun getName(player: Player): String {
                return translate("&fCurrent amount &7➥ &a$amount")
            }

            override fun onChange(number: Int) {
                amount = number
            }
        }

        for (i in 0..8) {
            if (!buttons.containsKey(i)) {
                buttons[i] = Button.placeholder(Material.STAINED_GLASS_PANE, 14.toByte())
            }
        }

        return buttons
    }

    override fun onClose(player: Player, manualClose: Boolean) {
        KitPvP.instance.server.scheduler.runTaskLater(KitPvP.instance, {
            if (!called) {
                invokeCallback(false)
            }
        }, 1L)
    }

    fun invokeCallback(confirm: Boolean) {
        val profile = player.getProfile()

        if (profile.gems < pricePerItem * amount) {
            player.sendMessage(translate("&cYou don't have enough gems to buy this!"))
            return
        }

        called = true
        callback.invoke(confirm, amount)
    }
}