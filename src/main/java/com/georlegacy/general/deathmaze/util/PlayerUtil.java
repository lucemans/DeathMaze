package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import org.bukkit.entity.Player;

public class PlayerUtil {

    public static void addDistance(Player p, double dist) {
        if (dist > 15)
            return;
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            stats = DeathMaze.getInstance().stats.get(p);
            stats.setDistance(DeathMaze.getInstance().stats.get(p).getDistance() + dist);
        } else {
            if (DataEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, DataEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
                stats.setDistance(DeathMaze.getInstance().stats.get(p).getDistance() + dist);
            } else {
                stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setUuid(p.getUniqueId().toString());
                stats.setDistance(dist);
            }
        }
        ScoreBoardUtil.send(p, stats);
    }

    public static void addKill(Player p) {
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            stats = DeathMaze.getInstance().stats.get(p);
            stats.setKills(DeathMaze.getInstance().stats.get(p).getKills() + 1);
        } else {
            if (DataEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, DataEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
                stats.setKills(DeathMaze.getInstance().stats.get(p).getKills() + 1);
            } else {
                stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setUuid(p.getUniqueId().toString());
                stats.setKills(1);
            }
        }
    }

    public static void addDeath(Player p) {
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            DeathMaze.getInstance().stats.get(p).setDeaths(DeathMaze.getInstance().stats.get(p).getDeaths() + 1);
            stats = DeathMaze.getInstance().stats.get(p);
            stats.setDeaths(DeathMaze.getInstance().stats.get(p).getDeaths() + 1);
        } else {
            if (DataEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, DataEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
                stats.setDeaths(DeathMaze.getInstance().stats.get(p).getDeaths() + 1);
            } else {
                stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setUuid(p.getUniqueId().toString());
                stats.setDeaths(1);
            }
        }
    }

}
