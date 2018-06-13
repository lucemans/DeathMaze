package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.util.DataEncoder;
import com.georlegacy.general.deathmaze.util.PlayerUtil;
import com.georlegacy.general.deathmaze.util.ScoreBoardUtil;
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

    private HashMap<Player, Location> locs = new HashMap<Player, Location>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!plugin.getConfiguration().getEnabledWorlds().contains(p.getWorld())) {
            return;
        }
        PlayerStats stats;
        if (DeathMaze.getInstance().stats.containsKey(p)) {
            stats = DeathMaze.getInstance().stats.get(p);
        } else {
            if (DataEncoder.decode(p.getUniqueId().toString()) != null) {
                DeathMaze.getInstance().stats.put(p, DataEncoder.decode(p.getUniqueId().toString()));
                stats = DeathMaze.getInstance().stats.get(p);
            } else {
                PlayerStats newStats = new PlayerStats();
                newStats.setName(p.getName());
                newStats.setUuid(p.getUniqueId().toString());
                DeathMaze.getInstance().stats.put(p, newStats);
                stats = DeathMaze.getInstance().stats.get(p);
            }
        }
        ScoreBoardUtil.send(p, stats);
        if (!locs.containsKey(p)) {
            locs.put(p, p.getLocation());
            return;
        }
        if (p.getLocation().getX() == locs.get(p).getX() && p.getLocation().getZ() == locs.get(p).getZ()) {
            return;
        }
        PlayerUtil.addDistance(p, Math.hypot(p.getLocation().getX() - locs.get(p).getX(), p.getLocation().getZ() - locs.get(p).getZ()));
        locs.put(p, p.getLocation());
    }

}