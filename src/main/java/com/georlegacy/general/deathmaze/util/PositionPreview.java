package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PositionPreview {

    private RegionExplorable region;

    private Player player;

    private Set<Block> blocks;

    public PositionPreview(RegionExplorable region, Player player) {
        this.region = region;
        this.player = player;
        this.blocks = new HashSet<Block>();
    }

    public void show() {
        Location pos1 = region.getPos1().getLocation();
        Location pos2 = region.getPos2().getLocation();

        for (double x = pos1.getX(); x <= pos2.getX(); x++) {
            for (double y = pos1.getY(); y <= pos2.getY(); y++) {
                for (double z = pos1.getZ(); z <= pos2.getZ(); z++) {
                    Location loc = new Location(pos1.getWorld(), x, y, z);
                    player.sendBlockChange(loc, DeathMaze.getInstance().getConfiguration().getPreviewMaterial(), (byte) 0);
                    blocks.add(loc.getBlock());
                }
            }
        }
    }

    public void hide() {
        for (Block b : blocks) {
            player.sendBlockChange(b.getLocation(), b.getType(), b.getData());
        }
    }

}
