package me.steveshat.vexkits.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DeathListener implements Listener {

    @EventHandler
    public static void onDeath(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() == null) return;

        Player killer = e.getEntity().getKiller();

        killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, (int) (20L * 4), 3));
    }
}
