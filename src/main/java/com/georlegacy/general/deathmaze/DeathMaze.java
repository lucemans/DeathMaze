package com.georlegacy.general.deathmaze;

import com.georlegacy.general.deathmaze.util.ConfigUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathMaze extends JavaPlugin {
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
    }

    @Override
    public void onDisable() {

    }

}
