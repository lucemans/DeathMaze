package com.georlegacy.general.deathmaze.objects;

import com.georlegacy.general.deathmaze.util.ItemStackSerializerUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContainerLootable implements Serializable {

    public ContainerLootable(final long refillSeconds, final InventoryHolder container, final Location location) {
        this.refillMillis = refillSeconds;
        items = ItemStackSerializerUtil.itemStackArrayToBase64(container.getInventory().getContents());
    }

    @Getter @Setter public long refillMillis;

    @Getter public Location location;

    @Getter public String items;

}
