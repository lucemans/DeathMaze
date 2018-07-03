package com.georlegacy.general.deathmaze;

import com.georlegacy.general.deathmaze.commands.DeathMazeCommand;
import com.georlegacy.general.deathmaze.hooks.PAPIHook;
import com.georlegacy.general.deathmaze.listeners.*;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.objects.Maze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
import com.georlegacy.general.deathmaze.objects.enumeration.MazeMode;
import com.georlegacy.general.deathmaze.objects.pagination.PaginationSet;
import com.georlegacy.general.deathmaze.tasks.Refill;
import com.georlegacy.general.deathmaze.util.ConfigUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import com.georlegacy.general.deathmaze.util.MazeEncoder;
import com.georlegacy.general.deathmaze.util.StatsEncoder;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;
import java.util.logging.Level;

public final class DeathMaze extends JavaPlugin {
    public HashMap<Player, PlayerStats> stats;
    @Getter public HashMap<Player, MazeMode> modes;
    @Getter private HashMap<Integer, ContainerLootable> refills;
    @Getter private HashMap<Player, RegionExplorable> regions;
    @Getter private HashMap<ContainerLootable, Boolean> loots;
    @Getter private HashMap<String, PaginationSet> playerRegionLists;
    @Getter private HashMap<String, PaginationSet> playerLootableLists;

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

        // Initialisation
        LangUtil.init();
        maze = MazeEncoder.decode();
        stats = new HashMap<Player, PlayerStats>();
        modes = new HashMap<Player, MazeMode>();
        refills = new HashMap<Integer, ContainerLootable>();
        regions = new HashMap<Player, RegionExplorable>();
        loots = new HashMap<ContainerLootable, Boolean>();
        playerRegionLists = new HashMap<String, PaginationSet>();
        playerLootableLists = new HashMap<String, PaginationSet>();
        configuration = ConfigUtil.get();
        worldedit = (WorldEditPlugin) this.getServer().getPluginManager().getPlugin("WorldEdit");

        startRefills();

        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PAPIHook(this).register();
        }

        // Listeners
        this.getServer().getPluginManager().registerEvents(new PlayerChangeGameModeListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChangeWorldListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerKillEntityListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerOpenContainerListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerTeleportListener(this), this);

        this.getCommand("deathmaze").setExecutor(new DeathMazeCommand());

        //Initialisation complete
        this.getLogger().log(Level.FINE, "Initialisation complete.");
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
        LangUtil.init();
        startRefills();
        checkPlayers();
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

    private void checkPlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!stats.containsKey(p)) continue;
            ArrayList<RegionExplorable> l = new ArrayList<RegionExplorable>(stats.get(p).getRegionsExplored());
            for (RegionExplorable region : (ArrayList<RegionExplorable>) l.clone()){
                if (!containsMaze(maze.getRegions(), region)) {
                    stats.get(p).getRegionsExplored().remove(region);
                }
            }
            for (ContainerLootable container : stats.get(p).getContainersLooted()) {
                if (!maze.getContainers().contains(container))
                    stats.get(p).getContainersLooted().remove(container);
            }
        }
    }

    private boolean containsMaze(List<RegionExplorable> list, RegionExplorable l) {
        for (RegionExplorable exp : list)
            if (exp.getName().equalsIgnoreCase(l.getName()))
                return true;
        return false;
    }

}
