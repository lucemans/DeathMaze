package com.georlegacy.general.deathmaze.objects.enumeration;

import org.bukkit.GameMode;

/**
 * @author 615283
 */
public enum MazeMode {

    /**
     * Playing in the maze, all scores will be counted and added.
     */
    PLAYING("PLAYING", GameMode.SURVIVAL, true),
    /**
     * Editing the maze, scores not counted.
     */
    EDITING("EDITING", GameMode.CREATIVE, false),
    /**
     * Spectating others, ie staff catching hackers, scores not counted.
     */
    SPECTATING("SPECTATING", GameMode.SPECTATOR, false);

    private String display;
    private GameMode gameMode;
    private boolean scorable;

    /**
     * Gets the display name of the mode.
     * @return {@link String} of name
     */
    public String getDisplay() {
        return display;
    }

    /**
     * Gets the gamemode associated with that mode.
     * @return {@link GameMode} of mode
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Gets whether players will get scores in this gamemode.
     * @return {@link boolean} representation
     */
    public boolean isScorable() {
        return scorable;
    }

    /**
     * Constructor for enum options.
     * @param display The display name for the mode
     * @param gm The gamemode associated with the mode
     * @param scorable Whether players score when in this gamemode
     */
    MazeMode(String display, GameMode gm, boolean scorable) {
        this.display = display;
        this.gameMode = gm;
        this.scorable = scorable;
    }

    /**
     * Finds a MazeMode constant from a GameMode.
     * @param gameMode The {@link GameMode} to search the values for
     * @return The found {@link MazeMode}
     * @throws IllegalArgumentException When there is no MazeMode associated with that {@link GameMode}
     */
    public static MazeMode getByGameMode(GameMode gameMode) throws IllegalArgumentException {
        for (MazeMode mazeMode : values()) {
            if (mazeMode.getGameMode().equals(gameMode))
                return mazeMode;
        }
        throw new IllegalArgumentException();
    }

}
