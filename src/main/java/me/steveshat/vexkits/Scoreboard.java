package me.steveshat.vexkits;

import fr.mrmicky.fastboard.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Scoreboard implements Listener {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard board = new FastBoard(player);

        this.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    public static void updateBoard(FastBoard board) {

        String balance = PlaceholderAPI.setPlaceholders(board.getPlayer(), ".skript_balance.");
        String rank = PlaceholderAPI.setPlaceholders(board.getPlayer(), ".skript_rank.");
        String kills = PlaceholderAPI.setPlaceholders(board.getPlayer(), ".skript_kills.");
        String deaths = PlaceholderAPI.setPlaceholders(board.getPlayer(), ".skript_kills.");
        String kdr = PlaceholderAPI.setPlaceholders(board.getPlayer(), ".skript_kdr.");
        String streak = PlaceholderAPI.setPlaceholders(board.getPlayer(), ".skript_streak.");
        String color = Constants.sbColorMain;

        board.updateLines(
                ChatColor.GOLD + "",
                ChatColor.translateAlternateColorCodes('&', "&7 |" + color + " Balance: &a$" + balance),
                ChatColor.BOLD + "",
                ChatColor.translateAlternateColorCodes('&', "&7 |" + color + " Kills: &c" + kills),
                ChatColor.translateAlternateColorCodes('&', "&7 |" + color + " Deaths: &c" + deaths),
                ChatColor.translateAlternateColorCodes('&', "&7 |" + color + " KDR: &c" + kdr),
                ChatColor.translateAlternateColorCodes('&', "&7 |" + color + " Streak: &c" + streak),
                ChatColor.RED + "",
                Constants.sbColorSecondary + "vexmc.club"
        );
    }

    VexKits main;
    public Scoreboard() {
        this.main = main;
    }

}
