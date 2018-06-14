package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.util.PlayerUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerKillEntityListener implements Listener {
    private final DeathMaze plugin;
    public PlayerKillEntityListener(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onKill(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (e.getEntity().getType().equals(EntityType.PLAYER)) {
            return;
        }
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        if (e.getFinalDamage() < ((LivingEntity) e.getEntity()).getHealth()) {
            return;
        }
        if (plugin.getConfiguration().getEnabledWorlds().contains(((Player) e.getDamager()).getWorld())) {
            PlayerUtil.addKill((Player) e.getDamager());
        }
    }

}
