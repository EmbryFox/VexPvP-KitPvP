package me.steveshat.vexkits.luanrclientapi;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.activated.core.plugin.AquaCoreAPI;
import me.steveshat.vexkits.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClientNametagProvider implements Runnable{

    @Override
    public void run() {
        List<String> lines = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()){
            Bukkit.getOnlinePlayers().forEach(viewer -> LunarClientAPI.getInstance().overrideNametag(player, getNametag(player, viewer), viewer));
        }
    }

    public List<String> getNametag(Player player, Player viewer){
        List<String> lines = new ArrayList<>();
        Boolean mm = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId()).isInStaffMode();
        Boolean vanished = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId()).isVanished();
        String color = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId()).getNameColor();

        if (mm) {
            lines.add(Chat.format("&7[ModMode]"));
        }
        lines.add(Chat.format((vanished ? "&7&o*" : color) + player.getDisplayName()));
        return lines;
    }
}
