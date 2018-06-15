package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.util.PlayerUtil;
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
    public void onMove(PlayerMoveEvent e) {
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
        PlayerUtil.addDistance(p, Math.hypot(p.getLocation().getX() - locs.get(p).getX(), p.getLocation().getZ() - locs.get(p).getZ()));
        locs.put(p, p.getLocation());
    }

}