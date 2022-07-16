package me.steveshat.vexkits.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.EventListener;

public class AntiBuild implements Listener {

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;

        e.setCancelled(true);
    }

    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;

        e.setCancelled(true);
    }
}
