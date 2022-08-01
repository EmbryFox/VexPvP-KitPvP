package org.hyrical.kitpvp.skydrops

import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Chest
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.potion.Potion
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionType
import org.hyrical.kitpvp.KitPvP

object SkyDropHandler {

    private val world = Bukkit.getWorld("world")

    val skyDropLocations: ArrayList<Location> = arrayListOf(
        Location(world, -28.0, 87.0, 29.0),
        Location(world, 35.0, 87.0, 45.0),
        Location(world, 71.0, 89.0, -41.0),
        Location(world, -7.0, 87.0, -50.0),
        Location(world, -28.0, 88.0, -53.0),
        Location(world, 73.0, 103.0, 28.0),
        Location(world, 65.0, 88.0, -17.0),
    )

    val skyDropItems: ArrayList<ItemStack> = arrayListOf(
        item(Material.FISHING_ROD, 1),
        item(Material.GOLDEN_APPLE, 64),
        item(Material.DIAMOND_SWORD, 1),
        item(Material.GOLDEN_APPLE, 1, 1),
        item(Material.DIAMOND_CHESTPLATE, 1),
        item(Material.BOW, 1, Enchantment.ARROW_KNOCKBACK, 2),
        item(Material.DIAMOND_SWORD, 1, Enchantment.DAMAGE_ALL, 1),
        potion(PotionType.INSTANT_HEAL, 0, true, 1),
        potion(PotionType.INSTANT_HEAL, 0, true, 2),
        potion(PotionType.REGEN, 90, true, 2),
        potion(PotionType.REGEN, 90, true, 2),
        potion(PotionType.SPEED, 60 * 8, true, 1),
        potion(PotionType.SPEED, 90, true, 2),
    )

    var currentSkyDropLocation: Location? = null

    fun spawnRandom(){
        for (loc in skyDropLocations){
            if (loc.block.type == Material.CHEST){
                val chest = loc.block.state as Chest

                chest.inventory.clear()
                chest.type = Material.AIR
            }
        }

        val location = skyDropLocations[KitPvP.random.nextInt(0, skyDropLocations.size)]

        location.block.type = Material.CHEST

        currentSkyDropLocation = location

        val chest = location.block.state as Chest
        val chestInventory = chest.inventory

        val item1 = skyDropItems[KitPvP.random.nextInt(0, skyDropItems.size)]
        val item2 = skyDropItems[KitPvP.random.nextInt(0, skyDropItems.size)]
        val item3 = skyDropItems[KitPvP.random.nextInt(0, skyDropItems.size)]
        val item4 = skyDropItems[KitPvP.random.nextInt(0, skyDropItems.size)]
        val item5 = skyDropItems[KitPvP.random.nextInt(0, skyDropItems.size)]

        chestInventory.setItem(KitPvP.random.nextInt(0, chestInventory.size), item1)
        chestInventory.setItem(KitPvP.random.nextInt(0, chestInventory.size), item2)
        chestInventory.setItem(KitPvP.random.nextInt(0, chestInventory.size), item3)
        chestInventory.setItem(KitPvP.random.nextInt(0, chestInventory.size), item4)
        chestInventory.setItem(KitPvP.random.nextInt(0, chestInventory.size), item5)

        chest.setMetadata("locked", FixedMetadataValue(KitPvP.instance, true))
    }

    private fun item(material: Material, amount: Int): ItemStack{
        return ItemBuilder.of(material).amount(amount).build()
    }

    private fun item(material: Material, amount: Int, data: Short): ItemStack{
        return ItemBuilder.of(material).amount(amount).data(data).build()
    }

    private fun item(material: Material, amount: Int, enchant: Enchantment, level: Int): ItemStack {
        return ItemBuilder.of(material).amount(amount).enchant(enchant, level).build()
    }

    private fun potion(type: PotionType, seconds: Int, splash: Boolean, level: Int): ItemStack {
        val potion = Potion(type, level)

        potion.isSplash = splash
        if (seconds != 0){
            potion.type.effectType.createEffect(20 * seconds, level)
        }

        return potion.toItemStack(1)
    }

}