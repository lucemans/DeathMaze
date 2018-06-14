package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
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

    public List<Map.Entry<String, Integer>> getScoreBoardFormat(PlayerStats stats) {
        HashMap<String, Integer> scores = new HashMap<String, Integer>();
        String[] lines = this.config.getString("ScoreboardFormat").split("\n");
        int i = 0;
        for (String line : lines) {
            String formattedLine = ChatColor.translateAlternateColorCodes('&', line
                    .replace("%UUID%", stats.getUuid())
                    .replace("%NAME%", stats.getName())
                    .replace("%DISTANCE%", stats.getDistance() + "")
                    .replace("%KILLS%", stats.getKills() + "")
                    .replace("%DEATHS%", stats.getDeaths() + "")
                    .replace("%REGIONS%", stats.getRegionsExplored() + "")
                    .replace("%CONTAINERS%", stats.getContainersLooted() + ""));
            scores.put(formattedLine, lines.length - i);
            i++;
        }
        return null;
    }

}
