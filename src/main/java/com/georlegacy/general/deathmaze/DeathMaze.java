package com.georlegacy.general.deathmaze;

import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.util.ConfigUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class DeathMaze extends JavaPlugin {
    public HashMap<Player, PlayerStats> stats;

    protected static DeathMaze instance;
    private ConfigUtil config = ConfigUtil.get();

    public ConfigUtil getConfiguration() {
        return config;
    }

    public static DeathMaze getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        stats = new HashMap<Player, PlayerStats>();
    }

    @Override
    public void onDisable() {

    }

}
