package me.steveshat.vexkits.scoreboard;

import me.steveshat.vexkits.Constants;
import me.steveshat.vexkits.chat.Chat;
import net.evilblock.cubed.scoreboard.TitleGetter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ScoreboardTitleGetter implements TitleGetter {
    @NotNull
    @Override
    public String getTitle(@NotNull Player player) {
        return Chat.format(Constants.sbTitle);
    }
}
