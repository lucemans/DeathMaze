package com.georlegacy.general.deathmaze.hooks;

import com.georlegacy.general.deathmaze.DeathMaze;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PAPIHook extends PlaceholderExpansion {

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


        return null;
    }
}
