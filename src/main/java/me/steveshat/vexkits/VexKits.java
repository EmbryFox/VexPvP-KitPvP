package me.steveshat.vexkits;

import me.steveshat.vexkits.chat.ChatListener;
import me.steveshat.vexkits.luanrclientapi.ClientNametagProvider;
import me.steveshat.vexkits.scoreboard.ScoreboardScoresGetter;
import me.steveshat.vexkits.scoreboard.ScoreboardTitleGetter;
import net.evilblock.cubed.scoreboard.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class VexKits extends JavaPlugin {

    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("VexKits");

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.print("VexKits Loaded");
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        ScoreboardHandler.configure(new ScoreboardTitleGetter(), new ScoreboardScoresGetter());
        Bukkit.getScheduler().runTaskTimer(this, new ClientNametagProvider(), 0L, 20L);


    }
}
