package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {
    private final DeathMaze plugin;
    public PlayerTeleportListener(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        DeathMaze.getInstance().moveListener.teleporters.put(e.getPlayer(), true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
                DeathMaze.getInstance().moveListener.teleporters.put(e.getPlayer(), false);
            }
        }, 300);
    }

}
