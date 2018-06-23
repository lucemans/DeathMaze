package com.georlegacy.general.deathmaze;

import com.georlegacy.general.deathmaze.commands.DeathMazeCommand;
import com.georlegacy.general.deathmaze.listeners.*;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.objects.Maze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
import com.georlegacy.general.deathmaze.tasks.Refill;
import com.georlegacy.general.deathmaze.util.ConfigUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import com.georlegacy.general.deathmaze.util.MazeEncoder;
import com.georlegacy.general.deathmaze.util.StatsEncoder;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import javafx.collections.transformation.FilteredList;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

public final class DeathMaze extends JavaPlugin {
    public HashMap<Player, PlayerStats> stats;
    public Set<PlayerStats> offlineStats;
    @Getter private HashMap<Integer, ContainerLootable> refills;
    @Getter private HashMap<Player, RegionExplorable> regions;
    @Getter private HashMap<ContainerLootable, Boolean> loots;

    @Getter private Maze maze;
    @Getter private ConfigUtil configuration;
    @Getter private WorldEditPlugin worldedit;

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
        offlineStats = new HashSet<PlayerStats>();
        refills = new HashMap<Integer, ContainerLootable>();
        regions = new HashMap<Player, RegionExplorable>();
        loots = new HashMap<ContainerLootable, Boolean>();
        configuration = ConfigUtil.get();
        worldedit = (WorldEditPlugin) this.getServer().getPluginManager().getPlugin("WorldEdit");

        loadStats();
        startRefills();

        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChangeWorldListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerKillEntityListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerOpenContainerListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);

        this.getCommand("deathmaze").setExecutor(new DeathMazeCommand());
    }

    @Override
    public void onDisable() {
        MazeEncoder.encode(maze);
        for (Map.Entry<Player, PlayerStats> entry : stats.entrySet()) {
            StatsEncoder.encode(entry.getValue());
        }
        for (int id : refills.keySet()) {
            getServer().getScheduler().cancelTask(id);
        }
    }

    public void reloadAll() {
        configuration = ConfigUtil.get();
        startRefills();
    }

    public void loadStats() {
        System.out.println("loading");
        for (Map.Entry<Player, PlayerStats> entry : stats.entrySet()) {
            System.out.println(entry + "first for");
            StatsEncoder.encode(entry.getValue());
        }
        System.out.println(offlineStats);
        stats.clear();
        for (PlayerStats stats : offlineStats) {
            StatsEncoder.encode(stats);
        }
        offlineStats.clear();
        for (File stats : Objects.requireNonNull(new File(getDataFolder() + File.separator + "players").listFiles(pathname -> pathname.getName().endsWith(".dat")))) {
            System.out.println(stats);
            offlineStats.add(StatsEncoder.decode(stats));
        }
    }

    private void startRefills() {
        for (int id : refills.keySet()) {
            getServer().getScheduler().cancelTask(id);
        }
        refills.clear();
        for (ContainerLootable c : maze.getContainers()) {
            Refill refill = new Refill(this);
            final int id = getServer().getScheduler().scheduleSyncRepeatingTask(this, refill, 1L, (c.getRefillSeconds()*20));
            refill.setTaskID(id);
            refills.put(id, c);
        }
    }

}
