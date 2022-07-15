package me.steveshat.vexkits.chat;

import org.bukkit.ChatColor;

public class Chat {

    public static String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
