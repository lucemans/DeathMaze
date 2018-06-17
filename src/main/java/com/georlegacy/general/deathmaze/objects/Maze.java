package com.georlegacy.general.deathmaze.objects;

import lombok.Getter;

import javax.xml.bind.Marshaller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Serializable {

    @Getter private List<ContainerLootable> containers;

    @Getter private List<RegionExplorable> regions;

    public Maze() {
        containers = new ArrayList<ContainerLootable>();
    }

}
