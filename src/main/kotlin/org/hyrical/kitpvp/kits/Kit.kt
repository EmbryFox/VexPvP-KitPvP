package org.hyrical.kitpvp.kits

data class Kit(var name: String, var permission: String = "kits.$name.use", var items: ArrayList<String> = arrayListOf(), var cooldown: String = "")
