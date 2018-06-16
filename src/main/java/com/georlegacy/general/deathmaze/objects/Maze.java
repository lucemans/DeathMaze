package com.georlegacy.general.deathmaze.objects;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Serializable {

    @Getter public List<ContainerLootable> containers;

    public Maze() {
        containers = new ArrayList<ContainerLootable>();
    }

}
