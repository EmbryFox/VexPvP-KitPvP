package org.hyrical.kitpvp.buycraft.menu.confirm

import net.evilblock.cubed.menu.buttons.TexturedHeadButton
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryView

abstract class NumberButton(
    protected var number: Int,
    protected var range: IntRange? = null,
    protected var amountPer: Int,
    headTexture: String = NUMBER_HEAD_TEXTURE
) : TexturedHeadButton(headTexture) {

    val price: Int = number * amountPer

    override fun getDescription(player: Player): List<String> {
        return arrayListOf<String>().also { desc ->
            desc.add("")
            desc.add("${ChatColor.GREEN}${ChatColor.BOLD}LEFT-CLICK ${ChatColor.GREEN}to increase by +1")
            desc.add("${ChatColor.RED}${ChatColor.BOLD}RIGHT-CLICK ${ChatColor.RED}to decrease by -1")
            desc.add("")
            desc.add("${ChatColor.GREEN}${ChatColor.BOLD}SHIFT LEFT-CLICK ${ChatColor.GREEN}to increase by +10")
            desc.add("${ChatColor.RED}${ChatColor.BOLD}SHIFT RIGHT-CLICK ${ChatColor.RED}to decrease by -10")
            desc.add("")
            desc.add("${ChatColor.WHITE}&fPrice ${ChatColor.GRAY}➥ ${ChatColor.GREEN}$price")
        }
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        try {
            val mod = if (clickType.isShiftClick) {
                10
            } else {
                1
            }

            when {
                clickType.isLeftClick -> {
                    val result = number + mod

                    if (range != null && result !in range!!) {
                        player.sendMessage("${ChatColor.RED}Value must be within range ${range!!.first} - ${range!!.last}!")
                        return
                    }

                    onChange(result)
                    number = result
                }
                clickType.isRightClick -> {
                    val result = number - mod

                    if (range != null && result !in range!!) {
                        player.sendMessage("${ChatColor.RED}Value must be within range ${range!!.first} - ${range!!.last}!")
                        return
                    }

                    onChange(result)
                    number = result
                }
            }
        } catch (e: IllegalStateException) {
            player.sendMessage("${ChatColor.RED}${e.message}!")
        }
    }

    abstract fun onChange(number: Int)

    companion object {
        private const val NUMBER_HEAD_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFhOTQ2M2ZkM2M0MzNkNWUxZDlmZWM2ZDVkNGIwOWE4M2E5NzBiMGI3NGRkNTQ2Y2U2N2E3MzM0OGNhYWIifX19"
    }

}