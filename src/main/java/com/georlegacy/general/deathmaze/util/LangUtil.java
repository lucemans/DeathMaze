package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import org.bukkit.configuration.file.YamlConfiguration;
import static com.georlegacy.general.deathmaze.util.ColorUtil.format;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LangUtil {

    protected YamlConfiguration config;

    private boolean isInitialised = false;

    private LangUtil() {
        try {
            File f = new File(DeathMaze.getInstance().getDataFolder(), "messages.yml");
            if (!f.exists())
                Files.copy(DeathMaze.getInstance().getClass().getClassLoader().getResourceAsStream("messages.yml"), f.toPath());
            config = YamlConfiguration.loadConfiguration(f);
            isInitialised = true;
        } catch (IOException e) {
            e.printStackTrace();
        }


        PREFIX = isInitialised ? format(config.getString("Prefix")) : null;
        HELP_HEADER = isInitialised ? format(config.getString("HelpHeader")) : null;
        NO_PERMISSION_MESSAGE = isInitialised ? format(config.getString("NoPermissionMessage")) : null;
        REMOVE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER = isInitialised ? format(config.getString("RemoveContainerLootableCommandFailNotContainer")) : null;
        REMOVE_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("RemoveContainerLootableCommandSuccess")) : null;
        UPDATE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER = isInitialised ? format(config.getString("RemoveContainerLootableCommandFailNotContainer")) : null;
        UPDATE_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("RemoveContainerLootableCommandSuccess")) : null;
        ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER = isInitialised ? format(config.getString("AddContainerLootableCommandFailNoContainer")) : null;
        ADD_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("AddContainerLootableCommandSuccess")) : null;
        ADD_CONTAINER_COMMAND_EMPTY = isInitialised ? format(config.getString("AddContainerLootableCommandEmpty")) : null;
        INCORRECT_ARGS_MESSAGE = isInitialised ? format(config.getString("IncorrectArgsMessage")) : null;
        REMOVE_CONTAINER_ATTEMPT_MESSAGE = isInitialised ? format(config.getString("RemoveContainerAttemptMessage")) : null;
    }

    public static void init() {
        new LangUtil();
    }

    //TODO add placeholders
    public static String PREFIX;

    public static String HELP_HEADER;

    public static String NO_PERMISSION_MESSAGE;

    public static String INCORRECT_ARGS_MESSAGE;

    public static String REMOVE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER;

    public static String REMOVE_CONTAINER_COMMAND_SUCCESS;

    public static String UPDATE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER;

    public static String UPDATE_CONTAINER_COMMAND_SUCCESS;

    public static String ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER;

    public static String ADD_CONTAINER_COMMAND_SUCCESS;

    public static String ADD_CONTAINER_COMMAND_EMPTY;

    public static String REMOVE_CONTAINER_ATTEMPT_MESSAGE;

}
