package com.georlegacy.general.deathmaze.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.Serializable;

public class SerializableLocation implements Serializable {

    private double x,y,z;

    private float yaw,pitch;

    private String world;

    public SerializableLocation(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
        this.yaw = loc.getYaw();
        this.pitch = loc.getPitch();
        this.world = loc.getWorld().getName();
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

}
