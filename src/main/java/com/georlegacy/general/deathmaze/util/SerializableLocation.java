package com.georlegacy.general.deathmaze.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.Serializable;

public class SerializableLocation implements Serializable {

    public double x,y,z;

    public String world;

    public SerializableLocation(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
        this.world = loc.getWorld().getName();
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

}
