package com.georlegacy.general.deathmaze.tasks;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.util.ItemStackSerializerUtil;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Refill implements Runnable {

    private int taskID;
    private final DeathMaze plugin;

    public Refill(DeathMaze plugin) {
        this.plugin = plugin;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void run() {
        ContainerLootable c = plugin.getRefills().get(taskID);
        InventoryHolder inv = (InventoryHolder) c.getLocation().getLocation().getBlock().getState();
        List<ItemStack> it = new ArrayList<ItemStack>();
        try {
            inv.getInventory().setContents(ItemStackSerializerUtil.itemStackArrayFromBase64(c.getItems()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
