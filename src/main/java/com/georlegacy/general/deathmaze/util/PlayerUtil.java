package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import org.bukkit.entity.Player;

public class PlayerUtil {

    public static void addDistance(Player p, double dist) {
        if (dist > 15)
            return;
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

    public static void addKill(Player p) {
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            DeathMaze.getInstance().stats.get(p).setKills(DeathMaze.getInstance().stats.get(p).getKills() + 1);
        } else {
            if (DataEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, DataEncoder.decode(p.getUniqueId().toString()));
                DeathMaze.getInstance().stats.get(p).setKills(DeathMaze.getInstance().stats.get(p).getKills() + 1);
            } else {
                PlayerStats stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setKills(1);
            }
        }
    }

    public static void addDeath(Player p) {
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            DeathMaze.getInstance().stats.get(p).setDeaths(DeathMaze.getInstance().stats.get(p).getDeaths() + 1);
        } else {
            if (DataEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, DataEncoder.decode(p.getUniqueId().toString()));
                DeathMaze.getInstance().stats.get(p).setDeaths(DeathMaze.getInstance().stats.get(p).getDeaths() + 1);
            } else {
                PlayerStats stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setDeaths(1);
            }
        }
    }

}
