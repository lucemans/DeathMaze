package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.google.gson.annotations.Since;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scoreboard.Score;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class ConfigUtil {
    protected YamlConfiguration config;

    private ConfigUtil() {
        try {
            File f = new File(DeathMaze.getInstance().getDataFolder(), "config.yml");
            if (!f.exists())
                Files.copy(DeathMaze.getInstance().getClass().getClassLoader().getResourceAsStream("config.yml"), f.toPath());
            this.config = YamlConfiguration.loadConfiguration(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigUtil get() {
        return new ConfigUtil();
    }

    public List<World> getEnabledWorlds() {
        List<World> worlds = new ArrayList<World>();
        this.config.getStringList("EnabledWorlds").forEach(string -> worlds.add(Bukkit.getWorld(string)));
        return !worlds.isEmpty() ? worlds : null;
    }

    public String getScoreboardHeader() {
        return ChatColor.translateAlternateColorCodes('&', this.config.getString("ScoreboardHeader"));
    }

    public List<Score> getScoreBoardFormat(PlayerStats stats) {
        //TODO Create getter and parser for placeholders
        return null;
    }

}
