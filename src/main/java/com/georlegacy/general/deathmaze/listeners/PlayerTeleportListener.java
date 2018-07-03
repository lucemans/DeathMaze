package com.georlegacy.general.deathmaze.listeners;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.NoRegion;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
import com.georlegacy.general.deathmaze.util.PlayerUtil;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {
    private final DeathMaze plugin;
    public PlayerTeleportListener(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player p = event.getPlayer();

        for (RegionExplorable r : plugin.getMaze().getRegions()) {
            CuboidSelection cs = new CuboidSelection(r.getPos1().getLocation().getWorld(), r.getPos1().getLocation(), r.getPos2().getLocation());
            if (cs.contains(event.getTo())) {
                if (plugin.getRegions().containsKey(p)) {
                    if (plugin.getRegions().get(p).equals(r)) {
                        return;
                    }
                    PlayerUtil.setRegion(p, r);
                    return;
                }
                PlayerUtil.setRegion(p, r);
                return;
            }
        }
        PlayerUtil.setRegion(p, new NoRegion());
        plugin.getRegions().remove(p);
    }

}
