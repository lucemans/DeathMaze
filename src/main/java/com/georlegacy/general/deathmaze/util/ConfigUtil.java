package com.georlegacy.general.deathmaze.util;

import com.google.gson.annotations.Since;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigUtil {
    protected YamlConfiguration config;

    private ConfigUtil() {
        try {
            this.config.load(new File("plugins/DeathMaze/config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
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

}
