package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.util.PlayerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private final DeathMaze plugin;
    public PlayerDeathListener(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (plugin.getConfiguration().getEnabledWorlds().contains(e.getEntity().getWorld())) {
            PlayerUtil.addDeath(e.getEntity());
        }

    }
}
