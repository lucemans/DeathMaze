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
        INCORRECT_ARGS_MESSAGE = isInitialised ? format(config.getString("IncorrectArgsMessage")) : null;
        COMMAND_WRONG_WORLD_MESSAGE = isInitialised ? format(config.getString("CommandWrongWorldMessage")) : null;
        REMOVE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER = isInitialised ? format(config.getString("RemoveContainerLootableCommandFailNotContainer")) : null;
        REMOVE_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("RemoveContainerLootableCommandSuccess")) : null;
        REMOVE_CONTAINER_ATTEMPT_MESSAGE = isInitialised ? format(config.getString("RemoveContainerAttemptMessage")) : null;
        UPDATE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER = isInitialised ? format(config.getString("UpdateContainerLootableCommandFailNotContainer")) : null;
        UPDATE_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("UpdateContainerLootableCommandSuccess")) : null;
        ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER = isInitialised ? format(config.getString("AddContainerLootableCommandFailNoContainer")) : null;
        ADD_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("AddContainerLootableCommandSuccess")) : null;
        ADD_CONTAINER_COMMAND_EMPTY = isInitialised ? format(config.getString("AddContainerLootableCommandEmpty")) : null;
        ADD_CONTAINER_COMMAND_ALREADY_REGISTERED = isInitialised ? format(config.getString("AddContainerLootableCommandAlreadyRegistered")) : null;
        SET_REFILL_TIME_CONTAINER_NOT_NUMBER = isInitialised ? format(config.getString("SetRefillTimeContainerNotNumber")) : null;
        SET_REFILL_TIME_CONTAINER_NO_NUMBER = isInitialised ? format(config.getString("SetRefillTimeContainerNoNumber")) : null;
        SET_REFILL_TIME_CONTAINER_SUCCESS = isInitialised ? format(config.getString("SetRefillTimeContainerSuccess")) : null;
        SET_REFILL_TIME_CONTAINER_COMMAND_FAIL_NOT_CONTAINER = isInitialised ? format(config.getString("SetRefillTimeContainerCommandFailNotContainer")) : null;
        ADD_REGION_ALREADY_EXISTS = isInitialised ? format(config.getString("AddRegionAlreadyExists")) : null;
        ADD_REGION_NO_SELECTION = isInitialised ? format(config.getString("AddRegionNoSelection")) : null;
        ADD_REGION_SELECTION_TOO_SMALL = isInitialised ? format(config.getString("AddRegionSelectionTooSmall")) : null;
        ADD_REGION_NON_CUBOID_SELECTION = isInitialised ? format(config.getString("AddRegionNonCuboidSelection")) : null;
        ADD_REGION_EXISTING_OVERLAP = isInitialised ? format(config.getString("AddRegionExistingOverlap")) : null;
        ADD_REGION_SUCCESS = isInitialised ? format(config.getString("AddRegionSuccess")) : null;
        PREVIEW_REGION_NO_REGION = isInitialised ? format(config.getString("PreviewRegionNoRegion")) : null;
        PREVIEW_REGION_NOT_REGION = isInitialised ? format(config.getString("PreviewRegionNotRegion")) : null;
        PREVIEW_REGION_SUCCESS = isInitialised ? format(config.getString("PreviewRegionSuccess")) : null;
        PREVIEW_REGION_END = isInitialised ? format(config.getString("PreviewRegionEnd")) : null;
        REMOVE_REGION_NO_REGION = isInitialised ? format(config.getString("RemoveRegionNoRegion")) : null;
        REMOVE_REGION_NOT_REGION = isInitialised ? format(config.getString("RemoveRegionNotRegion")) : null;
        REMOVE_REGION_SUCCESS = isInitialised ? format(config.getString("RemoveRegionSuccess")) : null;

    }

    public static void init() {
        new LangUtil();
    }

    public static String PREFIX;

    public static String HELP_HEADER;

    public static String NO_PERMISSION_MESSAGE;

    public static String INCORRECT_ARGS_MESSAGE;

    public static String COMMAND_WRONG_WORLD_MESSAGE;

    public static String REMOVE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER;

    public static String REMOVE_CONTAINER_COMMAND_SUCCESS;

    public static String REMOVE_CONTAINER_ATTEMPT_MESSAGE;

    public static String UPDATE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER;

    public static String UPDATE_CONTAINER_COMMAND_SUCCESS;

    public static String ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER;

    public static String ADD_CONTAINER_COMMAND_SUCCESS;

    public static String ADD_CONTAINER_COMMAND_EMPTY;

    public static String ADD_CONTAINER_COMMAND_ALREADY_REGISTERED;

    public static String SET_REFILL_TIME_CONTAINER_NOT_NUMBER;

    public static String SET_REFILL_TIME_CONTAINER_NO_NUMBER;

    public static String SET_REFILL_TIME_CONTAINER_SUCCESS;

    public static String SET_REFILL_TIME_CONTAINER_COMMAND_FAIL_NOT_CONTAINER;

    public static String ADD_REGION_ALREADY_EXISTS;

    public static String ADD_REGION_NO_SELECTION;

    public static String ADD_REGION_SELECTION_TOO_SMALL;

    public static String ADD_REGION_NON_CUBOID_SELECTION;

    public static String ADD_REGION_EXISTING_OVERLAP;

    public static String ADD_REGION_SUCCESS;

    public static String PREVIEW_REGION_NO_REGION;

    public static String PREVIEW_REGION_NOT_REGION;

    public static String PREVIEW_REGION_SUCCESS;

    public static String PREVIEW_REGION_END;

    public static String REMOVE_REGION_NO_REGION;

    public static String REMOVE_REGION_NOT_REGION;

    public static String REMOVE_REGION_SUCCESS;

}
