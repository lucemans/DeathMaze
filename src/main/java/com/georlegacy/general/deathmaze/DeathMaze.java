package com.georlegacy.general.deathmaze;

import com.georlegacy.general.deathmaze.listeners.PlayerChangeWorldListener;
import com.georlegacy.general.deathmaze.listeners.PlayerDeathListener;
import com.georlegacy.general.deathmaze.listeners.PlayerKillEntityListener;
import com.georlegacy.general.deathmaze.listeners.PlayerMoveListener;
import com.georlegacy.general.deathmaze.objects.Maze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.util.ConfigUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import com.georlegacy.general.deathmaze.util.MazeEncoder;
import com.georlegacy.general.deathmaze.util.StatsEncoder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class DeathMaze extends JavaPlugin {
    public HashMap<Player, PlayerStats> stats;

    private Maze maze;

    protected static DeathMaze instance;
    private ConfigUtil config;

    public ConfigUtil getConfiguration() {
        return config;
    }

    public Maze getMaze() {
        return maze;
    }

    public static DeathMaze getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        new File(getDataFolder(), File.separator + "players").mkdirs();

        LangUtil.init();

        instance = this;

        maze = MazeEncoder.decode();
        stats = new HashMap<Player, PlayerStats>();
        config = ConfigUtil.get();

        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChangeWorldListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerKillEntityListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

    @Override
    public void onDisable() {
        for (Map.Entry<Player, PlayerStats> entry : stats.entrySet()) {
            StatsEncoder.encode(entry.getValue());
        }
    }

}
