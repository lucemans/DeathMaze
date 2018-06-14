package com.georlegacy.general.deathmaze;

import com.georlegacy.general.deathmaze.listeners.PlayerChangeWorldListener;
import com.georlegacy.general.deathmaze.listeners.PlayerMoveListener;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.util.ConfigUtil;
import com.georlegacy.general.deathmaze.util.DataEncoder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class DeathMaze extends JavaPlugin {
    public HashMap<Player, PlayerStats> stats;

    protected static DeathMaze instance;
    private ConfigUtil config;

    public ConfigUtil getConfiguration() {
        return config;
    }

    public static DeathMaze getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        new File(getDataFolder(), File.separator + "players").mkdirs();

        instance = this;
        stats = new HashMap<Player, PlayerStats>();
        config = ConfigUtil.get();

        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChangeWorldListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
    }

    @Override
    public void onDisable() {
        for (Map.Entry<Player, PlayerStats> entry : stats.entrySet()) {
            DataEncoder.encode(entry.getValue());
        }
    }

}
