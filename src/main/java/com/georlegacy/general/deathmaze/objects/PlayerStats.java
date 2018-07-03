package com.georlegacy.general.deathmaze.objects;

import com.georlegacy.general.deathmaze.objects.enumeration.Level;
import com.georlegacy.general.deathmaze.util.ScoreboardUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public @Data class PlayerStats implements Serializable {

    public PlayerStats() {
        regionsExplored = new ArrayList<RegionExplorable>();
        containersLooted = new ArrayList<ContainerLootable>();
        currentLevel = Level.ZERO;
    }

    @Getter @Setter private String name;

    @Getter @Setter private String uuid;

    @Getter @Setter private Level currentLevel;

    @Getter @Setter private double excessXp;

    public void addXp(double xpToAdd) {
        excessXp += xpToAdd;
        if (excessXp >= Level.getNextLevel(currentLevel).getXp()) {
            excessXp = 0;
            currentLevel = Level.getNextLevel(currentLevel);
            ScoreboardUtil.send(Bukkit.getPlayerExact(name), this);
        }
    }

    @Getter @Setter private double distance;

    @Getter @Setter private int kills;

    @Getter @Setter private int deaths;

    @Setter private RegionExplorable currentRegion;

    public RegionExplorable getCurrentRegion() {
        if (currentRegion!=null)
            return currentRegion;
        else
            return new NoRegion();
    }

    @Getter @Setter private List<RegionExplorable> regionsExplored;

    @Getter @Setter private List<ContainerLootable> containersLooted;

}