package me.steveshat.vexkits;

import me.steveshat.vexkits.chat.ChatListener;
import me.steveshat.vexkits.listeners.AntiBuild;
import me.steveshat.vexkits.listeners.DeathListener;
import me.steveshat.vexkits.luanrclientapi.ClientNametagProvider;
import me.steveshat.vexkits.scoreboard.ScoreboardScoresGetter;
import me.steveshat.vexkits.scoreboard.ScoreboardTitleGetter;
import net.evilblock.cubed.scoreboard.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class VexKits extends JavaPlugin {

    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("VexKits");

    @Override
    public void onEnable() {
        //Listeners
        getServer().getPluginManager().registerEvents(new AntiBuild(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);

        //Commands

        //Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Other
        Bukkit.getScheduler().runTaskTimer(this, new ClientNametagProvider(), 0L, 10L);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        ScoreboardHandler.configure(new ScoreboardTitleGetter(), new ScoreboardScoresGetter());


    }
}
