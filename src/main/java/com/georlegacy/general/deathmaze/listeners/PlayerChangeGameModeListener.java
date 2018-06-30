package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.enumeration.MazeMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerChangeGameModeListener implements Listener {
    private final DeathMaze plugin;
    public PlayerChangeGameModeListener(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getConfiguration().getEnabledWorlds().contains(player.getWorld())) {
            return;
        }

        plugin.getModes().put(player, MazeMode.getByGameMode(event.getNewGameMode()));
    }

}
