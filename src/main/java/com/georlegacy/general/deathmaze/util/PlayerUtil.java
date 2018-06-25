package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PlayerUtil {

    public static void addDistance(Player p, double dist) {
        if (dist > 15)
            return;
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            stats = DeathMaze.getInstance().stats.get(p);
            stats.setDistance(DeathMaze.getInstance().stats.get(p).getDistance() + dist);
        } else {
            if (StatsEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, StatsEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
                stats.setDistance(DeathMaze.getInstance().stats.get(p).getDistance() + dist);
            } else {
                stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setUuid(p.getUniqueId().toString());
                stats.setDistance(dist);
                DeathMaze.getInstance().stats.put(p, stats);
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
            if (StatsEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, StatsEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
                stats.setKills(DeathMaze.getInstance().stats.get(p).getKills() + 1);
            } else {
                stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setUuid(p.getUniqueId().toString());
                stats.setKills(1);
            }
        }
        ScoreBoardUtil.send(p, stats);
    }

    public static void addDeath(Player p) {
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            stats = DeathMaze.getInstance().stats.get(p);
            stats.setDeaths(DeathMaze.getInstance().stats.get(p).getDeaths() + 1);
        } else {
            if (StatsEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, StatsEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
                stats.setDeaths(DeathMaze.getInstance().stats.get(p).getDeaths() + 1);
            } else {
                stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setUuid(p.getUniqueId().toString());
                stats.setDeaths(1);
            }
        }
        ScoreBoardUtil.send(p, stats);
    }

    public static void setRegion(Player p, RegionExplorable r) {
        Random rand = new Random();
        p.sendTitle(
                DeathMaze.getInstance().getConfiguration().getRegionEntryHeader(r),
                ColorUtil.format(r.getEntrySplashes().get(rand.nextInt(r.getEntrySplashes().size()))),
                DeathMaze.getInstance().getConfiguration().getRegionEntryFadeIn(),
                DeathMaze.getInstance().getConfiguration().getRegionEntryStay(),
                DeathMaze.getInstance().getConfiguration().getRegionEntryFadeOut()
        );
        p.playSound(p.getLocation(), r.getEntrySound(), 1, 1);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, DeathMaze.getInstance().getConfiguration().getRegionEntryBlindness() * 20, 255, true, false));
        DeathMaze.getInstance().getRegions().put(p, r);
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            stats = DeathMaze.getInstance().stats.get(p);
            for (RegionExplorable region : stats.getRegionsExplored()) {
                if (r.getName().equals(region.getName())) {
                    return;
                }
            }
            stats.getRegionsExplored().add(r);
        } else {
            if (StatsEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, StatsEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
                for (RegionExplorable region : stats.getRegionsExplored()) {
                    if (r.getName().equals(region.getName())) {
                        return;
                    }
                }
                stats.getRegionsExplored().add(r);
            } else {
                stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setUuid(p.getUniqueId().toString());
                stats.getRegionsExplored().add(r);
            }
        }
        ScoreBoardUtil.send(p, stats);
    }

    public static void addContainer(Player p, ContainerLootable c) {
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            stats = DeathMaze.getInstance().stats.get(p);
        } else {
            if (StatsEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, StatsEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
            } else {
                stats = new PlayerStats();
                stats.setName(p.getName());
                stats.setUuid(p.getUniqueId().toString());
            }
        }
        if (!stats.getContainersLooted().contains(c))
            stats.getContainersLooted().add(c);
        ScoreBoardUtil.send(p, stats);
    }

}
