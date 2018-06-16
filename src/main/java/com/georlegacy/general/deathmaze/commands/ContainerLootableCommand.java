package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.util.ColorUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContainerLootableCommand {

    @Command(permission = "deathmaze.addlootablecontainer", subCommand = "lootable")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player p = (Player) sender;
        Set<Material> transparent = new HashSet<Material>();
        transparent.add(Material.AIR);
        Block block = p.getTargetBlock(transparent, 5);
        if (!(block.getState() instanceof InventoryHolder)) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER);
            return true;
        }
        if (block == null) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER);
            return true;
        }
        if (block.getType().equals(Material.AIR)) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER);
            return true;
        }
        if (args.length == 1) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.INCORRECT_ARGS_MESSAGE);
            return true;
        }
        if (args[1].equalsIgnoreCase("add")) {
            boolean isEmpty = true;
            for (ItemStack it : ((InventoryHolder) block.getState()).getInventory().getContents()) {
                if (it != null)
                    isEmpty = false;
            }
            if (isEmpty) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_COMMAND_EMPTY);
                return true;
            }
            DeathMaze.getInstance().getMaze().getContainers().add(new ContainerLootable(
                    DeathMaze.getInstance().getConfiguration().getDefaultRefillSeconds(),
                    (InventoryHolder) block.getState(),
                    block.getLocation()
            ));
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_COMMAND_SUCCESS);
            DeathMaze.getInstance().reloadAll();
            return true;
        }
        if (args[1].equalsIgnoreCase("remove")) {
            List<ContainerLootable> containers = DeathMaze.getInstance().getMaze().getContainers();
            for (ContainerLootable c : containers) {
                if (c.getLocation().getLocation().equals(block.getLocation())) {
                    DeathMaze.getInstance().getMaze().getContainers().remove(c);
                    p.sendMessage(LangUtil.PREFIX + LangUtil.REMOVE_CONTAINER_COMMAND_SUCCESS);
                    DeathMaze.getInstance().reloadAll();
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.REMOVE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER);
            return true;
        }
        if (args[1].equalsIgnoreCase("set")) {
            if (args[2].equalsIgnoreCase("refill")) {
                if (args.length > 4) {
                    //TODO fail no number
                    return true;
                }
                try {
                    long seconds = Long.parseLong(args[3]);
                    //TODO success msg and changing period
                    return true;
                } catch (NumberFormatException e) {
                    //TODO not a number lol
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.HELP_HEADER);
            p.sendMessage(ColorUtil.format("&c/deathmaze lootable set refill <seconds> - &7Sets the refill time for the container"));
            return true;
        }
        if (args[1].equalsIgnoreCase("update")) {
            boolean isEmpty = true;
            for (ItemStack it : ((InventoryHolder) block.getState()).getInventory().getContents()) {
                if (it != null)
                    isEmpty = false;
            }
            if (isEmpty) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_COMMAND_EMPTY);
                return true;
            }
            List<ContainerLootable> containers = DeathMaze.getInstance().getMaze().getContainers();
            for (ContainerLootable c : containers) {
                if (c.getLocation().getLocation().equals(block.getLocation())) {
                    DeathMaze.getInstance().getMaze().getContainers().add(new ContainerLootable(
                            c.getRefillSeconds(),
                            (InventoryHolder) block.getState(),
                            c.getLocation().getLocation()
                    ));
                    DeathMaze.getInstance().getMaze().getContainers().remove(c);
                    DeathMaze.getInstance().reloadAll();
                    p.sendMessage(LangUtil.PREFIX + LangUtil.UPDATE_CONTAINER_COMMAND_SUCCESS);
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.UPDATE_CONTAINER_COMMAND_FAIL_NOT_CONTAINER);
            return true;
        }
        p.sendMessage(LangUtil.PREFIX + LangUtil.INCORRECT_ARGS_MESSAGE);
        return true;
    }
}
