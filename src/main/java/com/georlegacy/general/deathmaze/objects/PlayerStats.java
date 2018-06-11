package com.georlegacy.general.deathmaze.objects;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

public @Data class PlayerStats implements Serializable {

    public PlayerStats() {

    }

    @Getter public String name;

    @Getter public int totalXp;

    @Getter public int level;

    @Getter public int levelXp;

    @Getter public int totalLevelXp;

    @Getter public double distance;

    @Getter public int kills;

    @Getter public int deaths;

    @Getter public int regionsExplored;

    @Getter public int containersLooted;

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalXp(int xp) {
        this.totalXp = xp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLevelXp(int xp) {
        this.levelXp = xp;
    }

    public void setTotalLevelXp(int xp) {
        this.totalLevelXp = xp;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setRegionsExplored(int regions) {
        this.regionsExplored = regions;
    }

    public void setContainersLooted(int containers) {
        this.containersLooted = containers;
    }

}
