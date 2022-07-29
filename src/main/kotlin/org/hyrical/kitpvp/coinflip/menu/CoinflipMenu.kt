package org.hyrical.kitpvp.coinflip.menu

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import org.bukkit.entity.Player
import org.hyrical.kitpvp.translate

class CoinflipMenu : PaginatedMenu() {
    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        return
    }

    override fun getPrePaginatedTitle(player: Player): String {
        return translate("Coinflips")
    }
}