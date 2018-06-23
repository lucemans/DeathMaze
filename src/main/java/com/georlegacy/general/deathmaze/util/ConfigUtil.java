package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
import com.sun.prism.shader.Mask_TextureRGB_AlphaTest_Loader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scoreboard.Score;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class ConfigUtil {
    private YamlConfiguration config;

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
        String header = this.config.getString("ScoreboardHeader");
        return header.length() > 32 ? "HEADER_TOO_LONG" : ColorUtil.format(header);
    }

    public Set<Map.Entry<String, Integer>> getScoreBoardFormat(PlayerStats stats) {
        HashMap<String, Integer> scores = new HashMap<String, Integer>();
        List<String> lines = this.config.getStringList("ScoreboardFormat");
        int i = 0;
        for (String line : lines) {
            String formattedLine = ChatColor.translateAlternateColorCodes('&', line
                    .replace("%UUID%", stats.getUuid())
                    .replace("%NAME%", stats.getName())
                    .replace("%DISTANCE%", DistanceFormatter.format(stats.getDistance()))
                    .replace("%KILLS%", stats.getKills() + "")
                    .replace("%DEATHS%", stats.getDeaths() + "")
                    .replace("%REGIONS%", stats.getRegionsExplored().size() + "")
                    .replace("%CONTAINERS%", stats.getContainersLooted().size() + ""));
            if (line.length() >= 40)
                formattedLine = "LINE_TOO_LONG";
            scores.put(formattedLine, lines.size() - i);
            i++;
        }
        return scores.entrySet();
    }

    public Material getPreviewMaterial() {
        Material mat;
        try {
            mat = Material.getMaterial(this.config.getString("PreviewBlock"));
        } catch (IllegalArgumentException e) {
            mat = Material.GLASS;
        }
        return mat;
    }

    public String getRegionEntryHeader(RegionExplorable r) {
        return ColorUtil.format(this.config.getString("RegionEntryHeader").replace("%RNAME%", r.getName()));
    }

    public int getRegionEntryFadeIn() {
        return this.config.getInt("RegionEntryTitleFadeIn");
    }

    public int getRegionEntryFadeOut() {
        return this.config.getInt("RegionEntryTitleFadeOut");
    }

    public int getRegionEntryStay() {
        return this.config.getInt("RegionEntryTitleStay");
    }

    public long getDefaultRefillSeconds() {
        return this.config.getLong("DefaultRefillSeconds");
    }

}
