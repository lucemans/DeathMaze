package com.georlegacy.general.deathmaze.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public class ContainerLootable implements Serializable {

    public ContainerLootable(final long refillSeconds, final InventoryHolder container, final Location location) {
        this.refillMillis = refillSeconds;
        this.items = container.getInventory().getContents();
    }

    @Getter @Setter public long refillMillis;

    @Getter public Location location;

    @Getter public ItemStack[] items;

}
