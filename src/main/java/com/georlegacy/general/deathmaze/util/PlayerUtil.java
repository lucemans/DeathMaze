package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import org.bukkit.entity.Player;

public class PlayerUtil {

    public static void addDistance(Player p, double dist) {
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            DeathMaze.getInstance().stats.get(p).setDistance(DeathMaze.getInstance().stats.get(p).getDistance() + dist);
        } else {
            if (DataEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, DataEncoder.decode(p.getUniqueId().toString()));
                DeathMaze.getInstance().stats.get(p).setDistance(DeathMaze.getInstance().stats.get(p).getDistance() + dist);
            } else {
                PlayerStats stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setDistance(dist);
            }
        }
    }

}
