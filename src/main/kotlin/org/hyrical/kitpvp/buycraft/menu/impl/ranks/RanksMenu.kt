package org.hyrical.kitpvp.buycraft.menu.impl.ranks

import net.evilblock.cubed.menu.Button
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.hyrical.kitpvp.buycraft.menu.StoreMenu
import org.hyrical.kitpvp.buycraft.menu.impl.ranks.button.RankButton

class RanksMenu() : StoreMenu("Store ➥ Ranks") {
    override fun getButtons(player: Player): Map<Int, Button> {
        val buttons = HashMap<Int, Button>()

        buttons[10] = RankButton(
            "&aMerchant Rank",
            listOf("&7You will receive a &amerchant &7rank", "&7with this purchase."),
            "broadcast ${ChatColor.translateAlternateColorCodes('&', "&7You have purchased the &amerchant &7rank.")}",
            500,
            ChatColor.GREEN
        )

        buttons[12] = RankButton(
            "&bShogun Rank",
            listOf("&7You will receive a &bshogun &7rank", "&7with this purchase."),
            "broadcast ${ChatColor.translateAlternateColorCodes('&', "&7You have purchased the &bshogun &7rank.")}",
            1000,
            ChatColor.AQUA
        )

        buttons[14] = RankButton(
            "&5Emperor Rank",
            listOf("&7You will receive a &5emperor &7rank", "&7with this purchase."),
            "broadcast ${ChatColor.translateAlternateColorCodes('&', "&7You have purchased the &5emperor &7rank.")}",
            1500,
            ChatColor.DARK_PURPLE
        )

        buttons[16] = RankButton(
            "&5Vex Rank",
            listOf("&7You will receive a &5vex &7rank", "&7with this purchase."),
            "broadcast ${ChatColor.translateAlternateColorCodes('&', "&7You have purchased the &5vex &7rank.")}",
            2500,
            ChatColor.DARK_PURPLE
        )

        return applyButtons(buttons)
    }
}