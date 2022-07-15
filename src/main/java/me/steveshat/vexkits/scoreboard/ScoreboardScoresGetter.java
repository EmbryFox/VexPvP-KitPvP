package me.steveshat.vexkits.scoreboard;

import me.clip.placeholderapi.PlaceholderAPI;
import me.steveshat.vexkits.Constants;
import me.steveshat.vexkits.chat.Chat;
import net.evilblock.cubed.scoreboard.ScoreGetter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class ScoreboardScoresGetter implements ScoreGetter {
    @Override
    public void getScores(@NotNull LinkedList<String> linkedList, @NotNull Player player) {
        String rank = PlaceholderAPI.setPlaceholders(player, "%skript_rank%");
        String balance = PlaceholderAPI.setPlaceholders(player, "%skript_balance%");
        String kills = PlaceholderAPI.setPlaceholders(player, "%skript_kills%");
        String deaths = PlaceholderAPI.setPlaceholders(player, "%skript_kills%");
        String kdr = PlaceholderAPI.setPlaceholders(player, "%skript_kdr%");
        String streak = PlaceholderAPI.setPlaceholders(player, "%skript_streak%");

        linkedList.add(Chat.format("&e"));
        linkedList.add(Chat.format("&7 | &dRank: " + rank));
        linkedList.add(Chat.format("&7 | &dBalance: &a$" + balance));
        linkedList.add(Chat.format("&e"));
        linkedList.add(Chat.format("&7 | &dKills: &f" + kills));
        linkedList.add(Chat.format("&7 | &dDeaths: &f" + deaths));
        linkedList.add(Chat.format("&7 | &dKDR: &f" + kdr));
        linkedList.add(Chat.format("&7 | &dStreak: &f" + streak));
        linkedList.add(Chat.format("&e"));
        linkedList.add(Chat.format("&5vexmc.club"));

    }
}
