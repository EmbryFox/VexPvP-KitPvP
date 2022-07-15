package me.steveshat.vexkits;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class VexKits extends JavaPlugin {

    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("VexKits");


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

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.printf("VexKits Loaded");
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new Scoreboard(), this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : Scoreboard.boards.values()) {
                Scoreboard.updateBoard(board);
            }
        }, 0, 20);
    }
}
