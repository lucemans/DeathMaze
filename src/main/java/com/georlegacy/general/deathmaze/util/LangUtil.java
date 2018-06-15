package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import org.bukkit.configuration.file.YamlConfiguration;
import static com.georlegacy.general.deathmaze.util.ColorUtil.format;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LangUtil {

    protected static YamlConfiguration config;

    private static boolean isInitialised = false;

    private LangUtil() {
        try {
            File f = new File(DeathMaze.getInstance().getDataFolder(), "messages.yml");
            if (!f.exists())
                Files.copy(DeathMaze.getInstance().getClass().getClassLoader().getResourceAsStream("messages.yml"), f.toPath());
            this.config = YamlConfiguration.loadConfiguration(f);
            isInitialised = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        new LangUtil();
    }

    //TODO add placeholders
    public static String PREFIX = isInitialised ? format(config.getString("Prefix")) : null;

    public static String ADD_CONTAINER_LOOATABLE_COMMAND_FAIL_NO_CONTAINER = isInitialised ? format(config.getString("AddContainerLootableCommandFailNoContainer")) : null;

    public static String ADD_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("AddContainerLootableCommandSuccess")) : null;

}
