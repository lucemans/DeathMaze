package com.georlegacy.general.deathmaze.objects;

import com.georlegacy.general.deathmaze.util.SerializableLocation;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Sound;

import java.io.Serializable;
import java.util.LinkedList;

public class RegionExplorable implements Serializable {

    public RegionExplorable(String name, Location pos1, Location pos2, Sound entrySound) {
        this.name = name;
        this.pos1 = new SerializableLocation(pos1);
        this.pos2 = new SerializableLocation(pos2);
        this.entrySound = entrySound;
        this.entrySplashes = new LinkedList<String>();
    }

    @Getter @Setter private String name;

    @Getter @Setter private SerializableLocation pos1, pos2;

    @Getter @Setter private Sound entrySound;

    @Getter private LinkedList<String> entrySplashes;

}
