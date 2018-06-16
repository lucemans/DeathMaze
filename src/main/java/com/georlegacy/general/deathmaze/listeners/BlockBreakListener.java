package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.util.LangUtil;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private final DeathMaze plugin;
    public BlockBreakListener(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        for (ContainerLootable c : plugin.getMaze().getContainers()) {
            if (e.getBlock().getLocation().equals(c.getLocation().getLocation())) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(LangUtil.PREFIX + LangUtil.REMOVE_CONTAINER_ATTEMPT_MESSAGE);
            }
        }
    }

}
