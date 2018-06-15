package com.georlegacy.general.deathmaze.objects;

import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedList;

public class Maze implements Serializable {

    @Getter public LinkedList<ContainerLootable> containers;

}
