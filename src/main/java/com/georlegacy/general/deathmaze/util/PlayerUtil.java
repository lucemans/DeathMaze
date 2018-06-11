package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerUtil {

    public static void addDistance(Player p, double dist) {
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            DeathMaze.getInstance().stats.get(p).setDistance(DeathMaze.getInstance().stats.get(p).getDistance()+dist);
        } else {
            DeathMaze.getInstance().stats.put(p, new PlayerStats());
            DeathMaze.getInstance().stats.get(p).setDistance(DeathMaze.getInstance().stats.get(p).getDistance()+dist);
        }
    }

}
