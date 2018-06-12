package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.util.PlayerUtil;
import com.georlegacy.general.deathmaze.util.ScoreBoardUtil;
import org.bukkit.Bukkit;
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
            e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            return;
        }
        ScoreBoardUtil.send(p);
        if (!locs.containsKey(p)) {
            locs.put(p, p.getLocation());
            return;
        }
        if (p.getLocation().distance(locs.get(p)) < 1) {
            return;
        }
        PlayerUtil.addDistance(p, p.getLocation().distance(locs.get(p)));
    }

}