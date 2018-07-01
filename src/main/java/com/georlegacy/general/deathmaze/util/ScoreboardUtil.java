package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.objects.enumeration.MazeMode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.*;

public class ScoreboardUtil {

    @SuppressWarnings("unchecked")
    public static void send(Player p, PlayerStats stats) {

        ScoreboardManager m = Bukkit.getScoreboardManager();
        Scoreboard b = m.getNewScoreboard();

        Objective o = b.registerNewObjective("Data", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(DeathMaze.getInstance().getConfiguration().getScoreboardHeader());

        Collection<String> news = new ArrayList();

        Collection<String> olds = new ArrayList(p.getScoreboard().getEntries());

        if (!DeathMaze.getInstance().getModes().containsKey(p)) {
            DeathMaze.getInstance().getModes().put(p, MazeMode.getByGameMode(p.getGameMode()));
        }
        MazeMode mode = DeathMaze.getInstance().getModes().get(p);
        if (mode.equals(MazeMode.EDITING)) {
            for (Map.Entry<String, Integer> entry : DeathMaze.getInstance().getConfiguration().getEditingScoreboardFormat(p)) {
                Score s = o.getScore(entry.getKey());
                s.setScore(entry.getValue());
                news.add(entry.getKey());
            }
        } else if (mode.equals(MazeMode.SPECTATING)) {
            for (Map.Entry<String, Integer> entry : DeathMaze.getInstance().getConfiguration().getEditingScoreboardFormat(p)) {
                Score s = o.getScore(entry.getKey());
                s.setScore(entry.getValue());
                news.add(entry.getKey());
            }
        } else if (mode.equals(MazeMode.PLAYING)) {
            for (Map.Entry<String, Integer> entry : DeathMaze.getInstance().getConfiguration().getEditingScoreboardFormat(p)) {
                Score s = o.getScore(entry.getKey());
                s.setScore(entry.getValue());
                news.add(entry.getKey());
            }
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
