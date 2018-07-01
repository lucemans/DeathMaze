package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.NoRegion;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
import com.georlegacy.general.deathmaze.objects.enumeration.MazeMode;
import com.georlegacy.general.deathmaze.util.PlayerUtil;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class PlayerMoveListener implements Listener {
    private final DeathMaze plugin;
    public PlayerMoveListener(DeathMaze plugin) {
        this.plugin = plugin;
    }

    public HashMap<Player, Location> locs = new HashMap<Player, Location>();

    @EventHandler
    public void onMoveForDistance(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (!plugin.getConfiguration().getEnabledWorlds().contains(p.getWorld())) {
            return;
        }
        if (!locs.containsKey(p)) {
            locs.put(p, p.getLocation());
            return;
        }
        if (p.getLocation().getBlockX() == locs.get(p).getBlockX() && p.getLocation().getBlockZ() == locs.get(p).getBlockZ()) {
            return;
        }
        if (plugin.getModes().getOrDefault(p, MazeMode.PLAYING).equals(MazeMode.PLAYING)) {
            PlayerUtil.addDistance(p, Math.hypot(p.getLocation().getX() - locs.get(p).getX(), p.getLocation().getZ() - locs.get(p).getZ()));
        }
        locs.put(p, p.getLocation());
    }

    @EventHandler
    public void onMoveForRegion(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (!plugin.getConfiguration().getEnabledWorlds().contains(p.getWorld())) {
            return;
        }
        if (!plugin.getModes().getOrDefault(p, MazeMode.PLAYING).equals(MazeMode.PLAYING)) {
            return;
        }
        for (RegionExplorable r : plugin.getMaze().getRegions()) {
            CuboidSelection cs = new CuboidSelection(r.getPos1().getLocation().getWorld(), r.getPos1().getLocation(), r.getPos2().getLocation());
            if (cs.contains(p.getLocation())) {
                if (plugin.getRegions().containsKey(p)) {
                    if (plugin.getRegions().get(p).equals(r)) {
                        return;
                    }
                    PlayerUtil.setRegion(p, r);
                    return;
                }
                PlayerUtil.setRegion(p, r);
                return;
            }
        }
        plugin.getRegions().remove(p);
    }

}