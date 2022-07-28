package org.hyrical.kitpvp.kits

import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack

data class Kit(var name: String, var permission: String = "kits.$name.use", var items: ArrayList<String> = arrayListOf(), var cooldown: String = "")
