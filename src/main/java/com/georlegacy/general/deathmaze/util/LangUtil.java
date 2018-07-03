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

    private static String noMsg = format("&4Error getting message.");

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


        PREFIX = isInitialised ? format(config.getString("Prefix")) : noMsg;
        HELP_HEADER = isInitialised ? format(config.getString("HelpHeader")) : noMsg;
        NO_PERMISSION_MESSAGE = isInitialised ? format(config.getString("NoPermissionMessage")) : noMsg;
        INCORRECT_ARGS_MESSAGE = isInitialised ? format(config.getString("IncorrectArgsMessage")) : noMsg;
        COMMAND_WRONG_WORLD_MESSAGE = isInitialised ? format(config.getString("CommandWrongWorldMessage")) : noMsg;
        REMOVE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER = isInitialised ? format(config.getString("RemoveContainerLootableCommandFailNotContainer")) : noMsg;
        REMOVE_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("RemoveContainerLootableCommandSuccess")) : noMsg;
        REMOVE_CONTAINER_ATTEMPT_MESSAGE = isInitialised ? format(config.getString("RemoveContainerAttemptMessage")) : noMsg;
        UPDATE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER = isInitialised ? format(config.getString("UpdateContainerLootableCommandFailNotContainer")) : noMsg;
        UPDATE_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("UpdateContainerLootableCommandSuccess")) : noMsg;
        ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER = isInitialised ? format(config.getString("AddContainerLootableCommandFailNoContainer")) : noMsg;
        ADD_CONTAINER_COMMAND_SUCCESS = isInitialised ? format(config.getString("AddContainerLootableCommandSuccess")) : noMsg;
        ADD_CONTAINER_COMMAND_EMPTY = isInitialised ? format(config.getString("AddContainerLootableCommandEmpty")) : noMsg;
        ADD_CONTAINER_COMMAND_ALREADY_REGISTERED = isInitialised ? format(config.getString("AddContainerLootableCommandAlreadyRegistered")) : noMsg;
        SET_REFILL_TIME_CONTAINER_NOT_NUMBER = isInitialised ? format(config.getString("SetRefillTimeContainerNotNumber")) : noMsg;
        SET_REFILL_TIME_CONTAINER_NO_NUMBER = isInitialised ? format(config.getString("SetRefillTimeContainerNoNumber")) : noMsg;
        SET_REFILL_TIME_CONTAINER_SUCCESS = isInitialised ? format(config.getString("SetRefillTimeContainerSuccess")) : noMsg;
        SET_REFILL_TIME_CONTAINER_COMMAND_FAIL_NOT_CONTAINER = isInitialised ? format(config.getString("SetRefillTimeContainerCommandFailNotContainer")) : noMsg;
        CHECK_CONTAINER_LOOTABLE_FALSE = isInitialised ? format(config.getString("CheckContainerLootableFalse")) : noMsg;
        CHECK_CONTAINER_LOOTABLE_TRUE = isInitialised ? format(config.getString("CheckContainerLootableTrue")) : noMsg;
        CONTAINERS_LIST_NOT_PAGE = isInitialised ? format(config.getString("ContainersListNotPage")) : noMsg;
        CONTAINERS_LIST_NO_CONTAINERS = isInitialised ? format(config.getString("ContainersListNoContainers")) : noMsg;
        CONTAINERS_LIST_HEADER = isInitialised ? format(config.getString("ContainersListHeader")) : noMsg;
        ADD_REGION_ALREADY_EXISTS = isInitialised ? format(config.getString("AddRegionAlreadyExists")) : noMsg;
        ADD_REGION_NO_SELECTION = isInitialised ? format(config.getString("AddRegionNoSelection")) : noMsg;
        ADD_REGION_SELECTION_TOO_SMALL = isInitialised ? format(config.getString("AddRegionSelectionTooSmall")) : noMsg;
        ADD_REGION_NON_CUBOID_SELECTION = isInitialised ? format(config.getString("AddRegionNonCuboidSelection")) : noMsg;
        ADD_REGION_EXISTING_OVERLAP = isInitialised ? format(config.getString("AddRegionExistingOverlap")) : noMsg;
        ADD_REGION_SUCCESS = isInitialised ? format(config.getString("AddRegionSuccess")) : noMsg;
        PREVIEW_REGION_NO_REGION = isInitialised ? format(config.getString("PreviewRegionNoRegion")) : noMsg;
        PREVIEW_REGION_NOT_REGION = isInitialised ? format(config.getString("PreviewRegionNotRegion")) : noMsg;
        PREVIEW_REGION_SUCCESS = isInitialised ? format(config.getString("PreviewRegionSuccess")) : noMsg;
        PREVIEW_REGION_END = isInitialised ? format(config.getString("PreviewRegionEnd")) : noMsg;
        REMOVE_REGION_NO_REGION = isInitialised ? format(config.getString("RemoveRegionNoRegion")) : noMsg;
        REMOVE_REGION_NOT_REGION = isInitialised ? format(config.getString("RemoveRegionNotRegion")) : noMsg;
        REMOVE_REGION_SUCCESS = isInitialised ? format(config.getString("RemoveRegionSuccess")) : noMsg;
        SET_REGION_SOUND_NO_SOUND = isInitialised ? format(config.getString("SetRegionSoundNoSound")) : noMsg;
        SET_REGION_SOUND_NOT_SOUND = isInitialised ? format(config.getString("SetRegionSoundNotSound")) : noMsg;
        SET_REGION_NOT_REGION = isInitialised ? format(config.getString("SetRegionNotRegion")) : noMsg;
        SET_REGION_SOUND_SUCCESS = isInitialised ? format(config.getString("SetRegionSoundSuccess")) : noMsg;
        REGION_SPLASH_NOT_REGION = isInitialised ? format(config.getString("RegionSplashNotRegion")) : noMsg;
        REGION_ADD_SPLASH_NO_SPLASH = isInitialised ? format(config.getString("RegionAddSplashNoSplash")) : noMsg;
        REGION_ADD_SPLASH_EXISTS = isInitialised ? format(config.getString("RegionAddSplashExists")) : noMsg;
        REGION_ADD_SPLASH_SUCCESS = isInitialised ? format(config.getString("RegionAddSplashSuccess")) : noMsg;
        REGION_REMOVE_SPLASH_NO_SPLASH = isInitialised ? format(config.getString("RegionRemoveSplashNoSplash")) : noMsg;
        REGION_REMOVE_SPLASH_NOT_SPLASH = isInitialised ? format(config.getString("RegionRemoveSplashNotSplash")) : noMsg;
        REGION_REMOVE_SPLASH_NOT_ENOUGH = isInitialised ? format(config.getString("RegionRemoveSplashNotEnough")) : noMsg;
        REGION_REMOVE_SPLASH_SUCCESS = isInitialised ? format(config.getString("RegionRemoveSplashSuccess")) : noMsg;
        REGION_LIST_SPLASH_HEADER = isInitialised ? format(config.getString("RegionListSplashHeader")) : noMsg;
        REGION_CHECK_FAIL = isInitialised ? format(config.getString("RegionCheckFail")) : noMsg;
        REGION_CHECK_SUCCESS = isInitialised ? format(config.getString("RegionCheckSuccess")) : noMsg;
        REGIONS_LIST_NOT_PAGE = isInitialised ? format(config.getString("RegionsListNotPage")) : noMsg;
        REGIONS_LIST_NO_REGIONS = isInitialised ? format(config.getString("RegionsListNoRegions")) : noMsg;
        REGIONS_LIST_HEADER = isInitialised ? format(config.getString("RegionsListHeader")) : noMsg;
        REGION_TELEPORT_NO_REGION = isInitialised ? format(config.getString("RegionTeleportNoRegion")) : noMsg;
        REGION_TELEPORT_NOT_REGION = isInitialised ? format(config.getString("RegionTeleportNotRegion")) : noMsg;
        REGION_TELEPORT_NO_SPACE = isInitialised ? format(config.getString("RegionTeleportNoSpace")) : noMsg;
        REGION_TELEPORT_SUCCESS = isInitialised ? format(config.getString("RegionTeleportSuccess")) : noMsg;
        RELOAD_COMMAND_SUCCESS = isInitialised ? format(config.getString("ReloadCommandSuccess")) : noMsg;
        MAZE_SET_SPAWN_SUCCESS = isInitialised ? format(config.getString("MazeSetSpawnSuccess")) : noMsg;
        CONTAINER_TP_NO_LOOTABLE = isInitialised ? format(config.getString("ContainerTPNoLootable")) : noMsg;
        CONTAINER_TP_NOT_LOOTABLE = isInitialised ? format(config.getString("ContainerTPNotLootable")) : noMsg;
        CONTAINER_TP_SUCCESS = isInitialised ? format(config.getString("ContainerTPSuccess")) : noMsg;

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

    public static String REGION_TELEPORT_NO_REGION;

    public static String REGION_TELEPORT_NOT_REGION;

    public static String REGION_TELEPORT_NO_SPACE;

    public static String REGION_TELEPORT_SUCCESS;

    public static String RELOAD_COMMAND_SUCCESS;

    public static String MAZE_SET_SPAWN_SUCCESS;

    public static String CONTAINER_TP_NO_LOOTABLE;

    public static String CONTAINER_TP_NOT_LOOTABLE;

    public static String CONTAINER_TP_SUCCESS;

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
