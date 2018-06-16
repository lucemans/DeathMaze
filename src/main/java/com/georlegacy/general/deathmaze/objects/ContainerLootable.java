package com.georlegacy.general.deathmaze.objects;

import com.georlegacy.general.deathmaze.util.ItemStackSerializerUtil;
import com.georlegacy.general.deathmaze.util.SerializableLocation;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.inventory.InventoryHolder;

import java.io.Serializable;

public class ContainerLootable implements Serializable {

    public ContainerLootable(final long refillSeconds, final InventoryHolder container, final Location location) {
        this.refillSeconds = refillSeconds;
        this.location = new SerializableLocation(location);
        this.items = ItemStackSerializerUtil.itemStackArrayToBase64(container.getInventory().getContents());
    }

    @Getter @Setter public long refillSeconds;

    @Getter public SerializableLocation location;

    @Getter public String items;

}
