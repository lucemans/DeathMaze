package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.util.LangUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashSet;
import java.util.Set;

public class ContainerLootableCommand {

    @Command(permission = "deathmaze.addlootablecontainer", subCommand = "lootable")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player p = (Player) sender;
        Set<Material> transparent = new HashSet<Material>();
        transparent.add(Material.AIR);
        Block block = p.getTargetBlock(transparent, 5);
        if (!(block.getState() instanceof InventoryHolder)) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_LOOATABLE_COMMAND_FAIL_NO_CONTAINER);
            return true;
        }
        if (block == null) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_LOOATABLE_COMMAND_FAIL_NO_CONTAINER);
            return true;
        }
        if (block.getType().equals(Material.AIR)) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_LOOATABLE_COMMAND_FAIL_NO_CONTAINER);
            return true;
        }
        if (args[1].equalsIgnoreCase("add")) {
            DeathMaze.getInstance().getMaze().getContainers().add(new ContainerLootable(
                    DeathMaze.getInstance().getConfiguration().getDefaultRefillMillis(),
                    (InventoryHolder) block.getState(),
                    block.getLocation()
            ));
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_COMMAND_SUCCESS);
            return true;
        }
        //TODO remove and help
        return false;
    }
}
