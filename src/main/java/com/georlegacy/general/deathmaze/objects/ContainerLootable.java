package com.georlegacy.general.deathmaze.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Container;

import java.io.Serializable;

public class ContainerLootable implements Serializable {

    public ContainerLootable(final long refillMillis, final Container container) {
        this.refillMillis = refillMillis;
        this.container = container;
    }

    @Getter @Setter public long refillMillis;

    @Getter public Container container;

}
