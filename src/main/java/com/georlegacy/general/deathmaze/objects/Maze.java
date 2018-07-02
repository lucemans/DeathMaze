package com.georlegacy.general.deathmaze.objects;

import com.georlegacy.general.deathmaze.util.SerializableLocation;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Maze implements Serializable {

    @Getter @Setter private SerializableLocation spawn;

    @Getter private List<ContainerLootable> containers;

    @Getter private List<RegionExplorable> regions;

    public Maze() {
        containers = new LinkedList<ContainerLootable>();
        regions = new LinkedList<RegionExplorable>();
        spawn = new SerializableLocation(new Location(
                Bukkit.getWorlds().get(0),
                0,
                80,
                0,
                0,
                0
        ));
    }

}
