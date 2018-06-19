package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.util.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class PlayerOpenContainerListener implements Listener {
    private final DeathMaze plugin;
    public PlayerOpenContainerListener(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();

        if (!plugin.getConfiguration().getEnabledWorlds().contains(p.getWorld())) {
            return;
        }
        for (ContainerLootable c : plugin.getMaze().getContainers()) {
            if (plugin.getLoots().getOrDefault(c, false)) {
                return;
            }
            if (c.getLocation().equals(e.getInventory().getLocation())) {
                plugin.getLoots().put(c, true);
                PlayerUtil.addContainer(p, c);
            }
        }
    }

}
