package com.georlegacy.general.deathmaze.objects;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public @Data class PlayerStats implements Serializable {

    public PlayerStats() {

    }

    @Getter @Setter public String name;

    @Getter @Setter public String uuid;

    @Getter @Setter public int totalXp;

    @Getter @Setter public int level;

    @Getter @Setter public double distance;

    @Getter @Setter public int kills;

    @Getter @Setter public int deaths;

    @Getter @Setter public int regionsExplored;

    @Getter @Setter public int containersLooted;

}
