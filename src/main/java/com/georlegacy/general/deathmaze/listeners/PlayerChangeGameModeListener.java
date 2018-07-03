package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.objects.enumeration.MazeMode;
import com.georlegacy.general.deathmaze.util.ScoreboardUtil;
import com.georlegacy.general.deathmaze.util.StatsEncoder;
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

        PlayerStats stats;
        if (plugin.stats.containsKey(player)) {
            stats = plugin.stats.get(player);
        } else {
            if (StatsEncoder.decode(player.getUniqueId().toString()) != null) {
                plugin.stats.put(player, StatsEncoder.decode(player.getUniqueId().toString()));
                stats = plugin.stats.get(player);
            } else {
                stats = new PlayerStats();
                stats.setName(player.getName());
                stats.setUuid(player.getUniqueId().toString());
                plugin.stats.put(player, stats);
            }
        }
        ScoreboardUtil.send(player, stats);
    }

}
