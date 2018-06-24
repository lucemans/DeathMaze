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
        CHECK_CONTAINER_LOOTABLE_FALSE = isInitialised ? format(config.getString("CheckContainerLootableFalse")) : null;
        CHECK_CONTAINER_LOOTABLE_TRUE = isInitialised ? format(config.getString("CheckContainerLootableTrue")) : null;
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
        SET_REGION_SOUND_NO_SOUND = isInitialised ? format(config.getString("SetRegionSoundNoSound")) : null;
        SET_REGION_SOUND_NOT_SOUND = isInitialised ? format(config.getString("SetRegionSoundNotSound")) : null;
        SET_REGION_NOT_REGION = isInitialised ? format(config.getString("SetRegionNotRegion")) : null;
        SET_REGION_SOUND_SUCCESS = isInitialised ? format(config.getString("SetRegionSoundSuccess")) : null;
        REGION_SPLASH_NOT_REGION = isInitialised ? format(config.getString("RegionSplashNotRegion")) : null;
        REGION_ADD_SPLASH_NO_SPLASH = isInitialised ? format(config.getString("RegionAddSplashNoSplash")) : null;
        REGION_ADD_SPLASH_EXISTS = isInitialised ? format(config.getString("RegionAddSplashExists")) : null;
        REGION_ADD_SPLASH_SUCCESS = isInitialised ? format(config.getString("RegionAddSplashSuccess")) : null;
        REGION_REMOVE_SPLASH_NO_SPLASH = isInitialised ? format(config.getString("RegionRemoveSplashNoSplash")) : null;
        REGION_REMOVE_SPLASH_NOT_SPLASH = isInitialised ? format(config.getString("RegionRemoveSplashNotSplash")) : null;
        REGION_REMOVE_SPLASH_SUCCESS = isInitialised ? format(config.getString("RegionRemoveSplashSuccess")) : null;
        REGION_LIST_SPLASH_HEADER = isInitialised ? format(config.getString("RegionListSplashHeader")) : null;

    }

    public static void init() {
        new LangUtil();
    }

    public static String
            PREFIX,

    HELP_HEADER,

    NO_PERMISSION_MESSAGE,

    INCORRECT_ARGS_MESSAGE,

    COMMAND_WRONG_WORLD_MESSAGE,

    REMOVE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER,

    REMOVE_CONTAINER_COMMAND_SUCCESS,

    REMOVE_CONTAINER_ATTEMPT_MESSAGE,

    UPDATE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER,

    UPDATE_CONTAINER_COMMAND_SUCCESS,

    ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER,

    ADD_CONTAINER_COMMAND_SUCCESS,

    ADD_CONTAINER_COMMAND_EMPTY,

    ADD_CONTAINER_COMMAND_ALREADY_REGISTERED,

    SET_REFILL_TIME_CONTAINER_NOT_NUMBER,

    SET_REFILL_TIME_CONTAINER_NO_NUMBER,

    SET_REFILL_TIME_CONTAINER_SUCCESS,

    SET_REFILL_TIME_CONTAINER_COMMAND_FAIL_NOT_CONTAINER,

    CHECK_CONTAINER_LOOTABLE_FALSE,

    CHECK_CONTAINER_LOOTABLE_TRUE,

    ADD_REGION_ALREADY_EXISTS,

    ADD_REGION_NO_SELECTION,

    ADD_REGION_SELECTION_TOO_SMALL,

    ADD_REGION_NON_CUBOID_SELECTION,

    ADD_REGION_EXISTING_OVERLAP,

    ADD_REGION_SUCCESS,

    PREVIEW_REGION_NO_REGION,

    PREVIEW_REGION_NOT_REGION,

    PREVIEW_REGION_SUCCESS,

    PREVIEW_REGION_END,

    REMOVE_REGION_NO_REGION,

    REMOVE_REGION_NOT_REGION,

    REMOVE_REGION_SUCCESS,

    SET_REGION_SOUND_NO_SOUND,

    SET_REGION_SOUND_NOT_SOUND,

    SET_REGION_NOT_REGION,

    SET_REGION_SOUND_SUCCESS,

    REGION_SPLASH_NOT_REGION,

    REGION_ADD_SPLASH_NO_SPLASH,

    REGION_ADD_SPLASH_EXISTS,

    REGION_ADD_SPLASH_SUCCESS,

    REGION_REMOVE_SPLASH_NO_SPLASH,

    REGION_REMOVE_SPLASH_NOT_SPLASH,

    REGION_REMOVE_SPLASH_SUCCESS,

    REGION_LIST_SPLASH_HEADER;

}
