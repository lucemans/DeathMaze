package com.georlegacy.general.deathmaze;

import com.georlegacy.general.deathmaze.commands.DeathMazeCommand;
import com.georlegacy.general.deathmaze.listeners.PlayerChangeWorldListener;
import com.georlegacy.general.deathmaze.listeners.PlayerDeathListener;
import com.georlegacy.general.deathmaze.listeners.PlayerKillEntityListener;
import com.georlegacy.general.deathmaze.listeners.PlayerMoveListener;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.objects.Maze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.util.ConfigUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import com.georlegacy.general.deathmaze.util.MazeEncoder;
import com.georlegacy.general.deathmaze.util.StatsEncoder;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class DeathMaze extends JavaPlugin {
    public HashMap<Player, PlayerStats> stats;
    @Getter private HashMap<Integer, ContainerLootable> refills;

    @Getter private Maze maze;
    private ConfigUtil config;

    public ConfigUtil getConfiguration() {
        return config;
    }

    public static DeathMaze getInstance() {
        return getPlugin(DeathMaze.class);
    }

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        new File(getDataFolder(), File.separator + "players").mkdirs();

        LangUtil.init();
        maze = MazeEncoder.decode();
        stats = new HashMap<Player, PlayerStats>();
        refills = new HashMap<Integer, ContainerLootable>();
        config = ConfigUtil.get();

        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChangeWorldListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerKillEntityListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);

        this.getCommand("deathmaze").setExecutor(new DeathMazeCommand());
    }

    @Override
    public void onDisable() {
        MazeEncoder.encode(maze);
        for (Map.Entry<Player, PlayerStats> entry : stats.entrySet()) {
            StatsEncoder.encode(entry.getValue());
        }
    }

    private void startRefills() {
        refills.clear();
        for (ContainerLootable c : maze.getContainers()) {
            refills.put(getServer().getScheduler().scheduleSyncRepeatingTask(this, null, 0L, c.getRefillMillis()*20), c);
        }
    }

}
