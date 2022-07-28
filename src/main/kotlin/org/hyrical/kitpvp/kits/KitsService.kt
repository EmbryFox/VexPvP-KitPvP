package org.hyrical.kitpvp.kits

import io.github.nosequel.data.DataStoreType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.hyrical.kitpvp.KitPvP

object KitsService {

    val handler = KitPvP.instance.dataHandler.createStoreType<String, Kit>(DataStoreType.FLATFILE)
    val kits = hashMapOf<String, Kit>()
}