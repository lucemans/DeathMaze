package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Map;

public class ScoreBoardUtil {

    public static void send(Player p, PlayerStats stats) {
        ScoreboardManager m = Bukkit.getScoreboardManager();
        Scoreboard b = m.getNewScoreboard();

        Objective o = b.registerNewObjective("Data", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(DeathMaze.getInstance().getConfiguration().getScoreboardHeader());

        for (Map.Entry<String, Integer> entry : DeathMaze.getInstance().getConfiguration().getScoreBoardFormat(stats)) {
            Score s = o.getScore(entry.getKey());
            s.setScore(entry.getValue());
        }

        p.setScoreboard(b);
    }

}
