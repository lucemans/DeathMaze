package com.georlegacy.general.deathmaze.objects;

import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Maze implements Serializable {

    @Getter private List<ContainerLootable> containers;

    @Getter private List<RegionExplorable> regions;

    public Maze() {
        containers = new LinkedList<ContainerLootable>();
        regions = new LinkedList<RegionExplorable>();
    }

}
