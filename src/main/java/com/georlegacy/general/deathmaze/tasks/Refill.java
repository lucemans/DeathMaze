package com.georlegacy.general.deathmaze.tasks;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import org.bukkit.inventory.InventoryHolder;

public class Refill implements Runnable {

    private int taskID;
    private final DeathMaze plugin;

    public Refill(int taskID, DeathMaze plugin) {
        this.taskID = taskID;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ContainerLootable c = plugin.getRefills().get(taskID);
        InventoryHolder inv = (InventoryHolder) c.getLocation().getBlock().getState();
        inv.getInventory().setContents(c.getItems());
    }

}
