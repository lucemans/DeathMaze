package com.georlegacy.general.deathmaze.util;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Location;
import org.bukkit.Material;

public class TeleportUtil {

    public static Location findSafe(CuboidSelection cs) {
        Location min = cs.getMinimumPoint();
        Location max = cs.getMaximumPoint();

        for (int x = min.getBlockX(); x < max.getBlockX(); x++) {
            for (int y = min.getBlockY(); y < (max.getBlockY() - 2); y++) {
                for (int z = min.getBlockZ(); z < max.getBlockZ(); z++) {
                    Location loc = new Location(cs.getWorld(), x, y, z);
                    if (loc.getBlock().getType().equals(Material.AIR)) {
                        Location above = loc.clone();
                        above.setY(loc.getY() + 1);
                        if (above.getBlock().getType().equals(Material.AIR)) {
                            loc.setX(loc.getX() + .5);
                            loc.setZ(loc.getZ() + .5);
                            return loc;
                        }
                    }
                }
            }
        }

        return null;
    }

}
