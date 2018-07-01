package com.georlegacy.general.deathmaze.objects;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public @Data class PlayerStats implements Serializable {

    public PlayerStats() {
        regionsExplored = new ArrayList<RegionExplorable>();
        containersLooted = new ArrayList<ContainerLootable>();
    }

    @Getter @Setter private String name;

    @Getter @Setter private String uuid;

    @Getter @Setter private int totalXp;

    @Getter @Setter private int level;

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