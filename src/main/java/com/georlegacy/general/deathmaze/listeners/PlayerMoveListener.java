package com.georlegacy.general.deathmaze.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class PlayerMoveListener implements Listener {
    private HashMap<Player, Location> locs = new HashMap<Player, Location>();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

    }

}