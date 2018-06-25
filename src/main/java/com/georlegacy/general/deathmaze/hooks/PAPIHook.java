package com.georlegacy.general.deathmaze.hooks;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.util.DistanceFormatter;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PAPIHook extends PlaceholderExpansion {
    private final DeathMaze plugin;
    public PAPIHook(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "deathmaze";
    }

    @Override
    public String getAuthor() {
        return "615283";
    }

    @Override
    public String getVersion() {
        return DeathMaze.getInstance().getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return null;
        }
        if (identifier.equalsIgnoreCase("kills")) {
            return plugin.stats.containsKey(player) ? String.valueOf(plugin.stats.get(player).getKills()) : String.valueOf(0);
        }
        if (identifier.equalsIgnoreCase("deaths")) {
            return plugin.stats.containsKey(player) ? String.valueOf(plugin.stats.get(player).getDeaths()) : String.valueOf(0);
        }
        if (identifier.equalsIgnoreCase("distance")) {
            return plugin.stats.containsKey(player) ? DistanceFormatter.format(plugin.stats.get(player).getDistance()) : "0m";
        }
        if (identifier.equalsIgnoreCase("lootables")) {
            return plugin.stats.containsKey(player) ? String.valueOf(plugin.stats.get(player).getContainersLooted().size()) : String.valueOf(0);
        }
        if (identifier.equalsIgnoreCase("regions")) {
            return plugin.stats.containsKey(player) ? String.valueOf(plugin.stats.get(player).getRegionsExplored().size()) : String.valueOf(0);
        }
        return null;
    }
}
