package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
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
        CONTAINERS_LIST_NOT_PAGE = isInitialised ? format(config.getString("ContainersListNotPage")) : null;
        CONTAINERS_LIST_NO_CONTAINERS = isInitialised ? format(config.getString("ContainersListNoContainers")) : null;
        CONTAINERS_LIST_HEADER = isInitialised ? format(config.getString("ContainersListHeader")) : null;
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
        REGION_REMOVE_SPLASH_NOT_ENOUGH = isInitialised ? format(config.getString("RegionRemoveSplashNotEnough")) : null;
        REGION_REMOVE_SPLASH_SUCCESS = isInitialised ? format(config.getString("RegionRemoveSplashSuccess")) : null;
        REGION_LIST_SPLASH_HEADER = isInitialised ? format(config.getString("RegionListSplashHeader")) : null;
        REGION_CHECK_FAIL = isInitialised ? format(config.getString("RegionCheckFail")) : null;
        REGION_CHECK_SUCCESS = isInitialised ? format(config.getString("RegionCheckSuccess")) : null;
        REGIONS_LIST_NOT_PAGE = isInitialised ? format(config.getString("RegionsListNotPage")) : null;
        REGIONS_LIST_NO_REGIONS = isInitialised ? format(config.getString("RegionsListNoRegions")) : null;
        REGIONS_LIST_HEADER = isInitialised ? format(config.getString("RegionsListHeader")) : null;

    }

    public static void init() {
        new LangUtil();
    }

    public static String
            PREFIX;

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

    public static String CHECK_CONTAINER_LOOTABLE_FALSE;

    private static String CHECK_CONTAINER_LOOTABLE_TRUE;

    public static String CONTAINERS_LIST_NOT_PAGE;

    public static String CONTAINERS_LIST_NO_CONTAINERS;

    public static String CONTAINERS_LIST_HEADER;

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

    public static String SET_REGION_SOUND_NO_SOUND;

    public static String SET_REGION_SOUND_NOT_SOUND;

    public static String SET_REGION_NOT_REGION;

    public static String SET_REGION_SOUND_SUCCESS;

    public static String REGION_SPLASH_NOT_REGION;

    public static String REGION_ADD_SPLASH_NO_SPLASH;

    public static String REGION_ADD_SPLASH_EXISTS;

    public static String REGION_ADD_SPLASH_SUCCESS;

    public static String REGION_REMOVE_SPLASH_NO_SPLASH;

    public static String REGION_REMOVE_SPLASH_NOT_SPLASH;

    public static String REGION_REMOVE_SPLASH_NOT_ENOUGH;

    public static String REGION_REMOVE_SPLASH_SUCCESS;

    public static String REGION_LIST_SPLASH_HEADER;

    public static String REGION_CHECK_FAIL;

    private static String REGION_CHECK_SUCCESS;

    public static String REGIONS_LIST_NOT_PAGE;

    public static String REGIONS_LIST_NO_REGIONS;

    public static String REGIONS_LIST_HEADER;

    public static String CHECK_CONTAINER_LOOTABLE_TRUE(ContainerLootable container) {
        return CHECK_CONTAINER_LOOTABLE_TRUE
                .replaceAll("%REFILL%", String.valueOf(container.getRefillSeconds()));
    }

    public static String REGION_CHECK_SUCCESS(RegionExplorable region) {
        return REGION_CHECK_SUCCESS
                .replaceAll("%RNAME%", region.getName())
                .replaceAll("%ESOUND%", region.getEntrySound().name());
    }

}
