package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.objects.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;

public class ScoreBoardUtil {

    public static void send(Player p, PlayerStats stats) {
        ScoreboardManager m = Bukkit.getScoreboardManager();
        Scoreboard b = m.getNewScoreboard();

        Objective o = b.registerNewObjective("Data", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.RED + "DeathMaze");

        Score blankScore = o.getScore(ChatColor.WHITE + "");
        blankScore.setScore(10);

        Score nameScore = o.getScore("Name: " + p.getName());
        nameScore.setScore(9);

        Score distanceScore = o.getScore("Distance: " + DistanceFormatter.format(Double.parseDouble(new DecimalFormat("#.#").format(stats.getDistance()))));
        distanceScore.setScore(8);

        p.setScoreboard(b);
    }

}
