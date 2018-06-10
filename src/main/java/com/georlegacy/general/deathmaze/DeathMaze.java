package com.georlegacy.general.deathmaze;

import org.bukkit.plugin.java.JavaPlugin;

public final class DeathMaze extends JavaPlugin {
    protected static DeathMaze instance;

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
