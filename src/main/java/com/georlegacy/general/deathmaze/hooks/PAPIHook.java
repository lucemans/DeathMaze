package com.georlegacy.general.deathmaze.hooks;

import com.georlegacy.general.deathmaze.DeathMaze;
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
        //TODO complete others
        return null;
    }
}
