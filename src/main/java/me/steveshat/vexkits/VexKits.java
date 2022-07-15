package me.steveshat.vexkits;

import me.steveshat.vexkits.chat.ChatListener;
import me.steveshat.vexkits.scoreboard.ScoreboardScoresGetter;
import me.steveshat.vexkits.scoreboard.ScoreboardTitleGetter;
import net.evilblock.cubed.scoreboard.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        ScoreboardHandler.configure(new ScoreboardTitleGetter(), new ScoreboardScoresGetter())


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("vex")) {

            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

                if (sender.hasPermission("vex.reload")) {

                    Player p = (Player) sender;
                    plugin.reloadConfig();
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Reloaded vex config");

                }
            }
        }

        return true;
    }
}
