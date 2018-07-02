package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.objects.NoRegion;
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
        ScoreboardUtil.send(p, stats);
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
        ScoreboardUtil.send(p, stats);
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
        ScoreboardUtil.send(p, stats);
    }

    public static void setRegion(Player player, RegionExplorable region) { DeathMaze.getInstance().getRegions().put(player, region);
        PlayerStats stats = findStats(player);
        if (region instanceof NoRegion) {
            stats.setCurrentRegion(region);
            ScoreboardUtil.send(player, stats);
            return;
        }

        Random rand = new Random();
        player.sendTitle(
                DeathMaze.getInstance().getConfiguration().getRegionEntryHeader(region),
                ColorUtil.format(region.getEntrySplashes().get(rand.nextInt(region.getEntrySplashes().size()))),
                DeathMaze.getInstance().getConfiguration().getRegionEntryFadeIn(),
                DeathMaze.getInstance().getConfiguration().getRegionEntryStay(),
                DeathMaze.getInstance().getConfiguration().getRegionEntryFadeOut()
        );
        player.playSound(player.getLocation(), region.getEntrySound(), 1, 1);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, DeathMaze.getInstance().getConfiguration().getRegionEntryBlindness() * 20, 255, true, false));

        stats.setCurrentRegion(region);
        ScoreboardUtil.send(player, stats);
        
        for (RegionExplorable rgn : stats.getRegionsExplored()) {
            if (rgn.getName().equals(region.getName())) {
                return;
            }
        }
        stats.getRegionsExplored().add(region);
    }

    public static void addContainer(Player p, ContainerLootable c) {
        PlayerStats stats = findStats(p);
        if (!stats.getContainersLooted().contains(c))
            stats.getContainersLooted().add(c);
        ScoreboardUtil.send(p, stats);
    }

    private static PlayerStats findStats(Player player) {
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(player)) {
            stats = DeathMaze.getInstance().stats.get(player);
        } else {
            if (StatsEncoder.decode(player.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(player, StatsEncoder.decode(player.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(player);
            } else {
                stats = new PlayerStats();
                stats.setName(player.getName());
                stats.setUuid(player.getUniqueId().toString());
            }
        }
        return stats;
    }

}
