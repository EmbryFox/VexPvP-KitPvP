package org.hyrical.kitpvp.skydrops.listener

import com.lunarclient.bukkitapi.LunarClientAPI
import com.lunarclient.bukkitapi.`object`.LCWaypoint
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Chest
import org.bukkit.block.DoubleChest
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.kitpvp.KitPvP
import org.hyrical.kitpvp.translate
import org.hyrical.kitpvp.utils.ProgressBarBuilder
import util.nms.ActionBarUtil
import java.awt.Color

class ChestListener : Listener {

    var unlocking: Player? = null

    @EventHandler
    fun openChest(event: InventoryOpenEvent){
        if (event.inventory.holder is Chest || event.inventory.holder is DoubleChest){
            val player = event.player as Player
            val chest = event.inventory.holder as Chest

            if (chest.hasMetadata("locked")){
                event.isCancelled = true

                if (unlocking != null && unlocking == player){
                    player.sendMessage(translate("&cYou are already unlocking this chest!"))
                    return
                } else if (unlocking != null && unlocking != player){
                    player.sendMessage(translate("&cSomeone is already trying to unlock this chest!"))
                    return
                }

                unlocking = player

                object : BukkitRunnable(){
                    var i = 10
                    override fun run() {
                        if (player.isDead || !player.isValid){
                            unlocking = null
                            cancel()

                            return
                        }

                        if (i == 10){
                            player.sendMessage(translate("&fThis chest will unlock in &d10 &fseconds."))
                        } else if (i == 5){
                            player.sendMessage(translate("&fThis chest will unlock in &d5 &fseconds."))
                        } else if (i == 3){
                            player.sendMessage(translate("&fThis chest will unlock in &d3 &fseconds."))
                        } else if (i == 2){
                            player.sendMessage(translate("&fThis chest will unlock in &d2 &fseconds."))
                        } else if (i == 1){
                            player.sendMessage(translate("&fThis chest will unlock in &d1 &fsecond."))
                        } else if (i == 0){
                            cancel()

                            chest.removeMetadata("locked", KitPvP.instance)
                            player.openInventory(chest.inventory)

                            player.playSound(player.location, Sound.ORB_PICKUP, 1.0f, 1.0f)

                            unlocking = null

                            player.sendMessage(translate("&fThis chest has been unlocked."))
                        }

                        i--

                    }
                }.runTaskTimer(KitPvP.instance, 0L, 20L)
            }
        }
    }

    @EventHandler
    fun closeInventory(event: InventoryCloseEvent){
        if (event.inventory.holder is Chest){
            val chest = event.inventory.holder as Chest

            var empty = true

            for (item in chest.inventory){
                if (item != null){
                    empty = false
                }
            }

            if (empty) {
                chest.block.type = Material.AIR

                Bukkit.broadcastMessage(translate("&fThe &5Sky Drop &fhas been looted!"))

                for (player in Bukkit.getOnlinePlayers()){
                    LunarClientAPI.getInstance().removeWaypoint(player,
                        LCWaypoint(
                            "Sky Drop",
                            chest.location,
                            Color.RED.rgb,
                            true)
                    )
                }
            }
        }
    }
}