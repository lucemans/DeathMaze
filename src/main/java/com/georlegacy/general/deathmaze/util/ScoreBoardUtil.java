package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.*;

public class ScoreBoardUtil {

    public static void send(Player p, PlayerStats stats) {
        ScoreboardManager m = Bukkit.getScoreboardManager();
        Scoreboard b = m.getNewScoreboard();

        Objective o = b.registerNewObjective("Data", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(DeathMaze.getInstance().getConfiguration().getScoreboardHeader());

        Collection<String> news = new ArrayList();

        Collection<String> olds = new ArrayList(p.getScoreboard().getEntries());

        for (Map.Entry<String, Integer> entry : DeathMaze.getInstance().getConfiguration().getScoreBoardFormat(stats)) {
            Score s = o.getScore(entry.getKey());
            s.setScore(entry.getValue());
            news.add(entry.getKey());
        }

        List<String> oldsList = new ArrayList<String>(olds);
        List<String> newsList = new ArrayList<String>(news);

        oldsList.removeAll(news);
        newsList.removeAll(olds);

        if (oldsList.size() != 0 || newsList.size() != 0) {
            p.setScoreboard(b);
        }

    }

}
