package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.objects.pagination.EmptyPaginationPage;
import com.georlegacy.general.deathmaze.objects.pagination.PaginationPage;
import com.georlegacy.general.deathmaze.objects.pagination.PaginationSet;
import com.georlegacy.general.deathmaze.util.ColorUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ContainerLootableCommand {

    @Command(permission = "deathmaze.admin.lootable", subCommand = "lootable")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player p = (Player) sender;
        Set<Material> transparent = new HashSet<Material>();
        transparent.add(Material.AIR);
        Block block = p.getTargetBlock(transparent, 5);
        if (args.length == 1) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.INCORRECT_ARGS_MESSAGE);
            return true;
        }
        if (args[1].equalsIgnoreCase("list")) {
            PaginationSet storedSet = null;
            PaginationSet set;
            if (DeathMaze.getInstance().getPlayerLootableLists().containsKey(p.getUniqueId().toString())) {
                storedSet = DeathMaze.getInstance().getPlayerLootableLists().get(p.getUniqueId().toString());
            }
            List<String> containerCoords = new ArrayList<String>();
            DeathMaze.getInstance().getMaze().getContainers().forEach(cnt -> containerCoords.add(
                    cnt.getLocation().getLocation().getBlockX() +
                            "," +
                            cnt.getLocation().getLocation().getBlockY() +
                            "," +
                            cnt.getLocation().getLocation().getBlockZ() +
                            "-" +
                            cnt.getLocation().getLocation().getWorld().getName()));
            if (containerCoords.size() == 0) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_NO_CONTAINERS);
                return true;
            }
            set = new PaginationSet(containerCoords, 6);
            PaginationPage page;
            if (args.length == 2) {
                page = set.getPage(0);
                p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_HEADER);
                for (String item : page.getItems()) {
                    TextComponent toSend = new TextComponent(item);
                    toSend.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                    toSend.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("Teleport")}));
                    toSend.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deathmaze lootable tp " + item));
                    p.spigot().sendMessage(toSend);
                }
                DeathMaze.getInstance().getPlayerLootableLists().put(p.getUniqueId().toString(), set);
                sendListFooter(p, page.getNumber());
                return true;
            }
            if (args[2].equalsIgnoreCase("next")) {
                page = storedSet!=null ? storedSet.getNextPage() : set.getNextPage();
                if (page instanceof EmptyPaginationPage) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_NOT_PAGE);
                    return true;
                }
                p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_HEADER);
                for (String item : page.getItems()) {
                    TextComponent toSend = new TextComponent(item);
                    toSend.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                    toSend.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("Teleport")}));
                    toSend.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deathmaze lootable tp " + item));
                    p.spigot().sendMessage(toSend);
                }
                sendListFooter(p, page.getNumber());
                return true;
            }
            if (args[2].equalsIgnoreCase("previous")) {
                page = storedSet!=null ? storedSet.getPreviousPage() : set.getPreviousPage();
                if (page instanceof EmptyPaginationPage) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_NOT_PAGE);
                    return true;
                }
                p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_HEADER);
                for (String item : page.getItems()) {
                    TextComponent toSend = new TextComponent(item);
                    toSend.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                    toSend.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("Teleport")}));
                    toSend.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deathmaze lootable tp " + item));
                    p.spigot().sendMessage(toSend);
                }
                sendListFooter(p, page.getNumber());
                return true;
            }
            int pageNo;
            try {
                pageNo = Integer.parseInt(args[2]);
            } catch (NumberFormatException ex) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_NOT_PAGE);
                return true;
            }
            if ((pageNo > set.getPages().size()) || (pageNo <= 0)) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_NOT_PAGE);
                return true;
            }
            page = set.getPage(pageNo - 1);
            p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINERS_LIST_HEADER);
            for (String item : page.getItems()) {
                TextComponent toSend = new TextComponent(item);
                toSend.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                toSend.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("Teleport")}));
                toSend.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deathmaze lootable tp " + item));
                p.spigot().sendMessage(toSend);
            }
            DeathMaze.getInstance().getPlayerLootableLists().put(p.getUniqueId().toString(), set);
            sendListFooter(p, pageNo);
            return true;
        }
        if (args[1].equalsIgnoreCase("tp")) {
            if (args.length == 2) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINER_TP_NO_LOOTABLE);
                return true;
            }
            for (ContainerLootable lootable : DeathMaze.getInstance().getMaze().getContainers()) {
                Location loc = lootable.getLocation().getLocation();
                if ((loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "-" + loc.getWorld().getName()).equalsIgnoreCase(args[2])) {
                    loc.setY(loc.getY() + 1);
                    p.teleport(loc);
                    p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINER_TP_SUCCESS);
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.CONTAINER_TP_NOT_LOOTABLE);
            return true;
        }
        if (!DeathMaze.getInstance().getConfiguration().getEnabledWorlds().contains(p.getWorld())) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.COMMAND_WRONG_WORLD_MESSAGE);
            return true;
        }
        if (!(block.getState() instanceof InventoryHolder)) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_LOOTABLE_COMMAND_FAIL_NO_CONTAINER);
            return true;
        }
        if (Arrays.asList(
                InventoryType.ANVIL,
                InventoryType.BEACON,
                InventoryType.CRAFTING,
                InventoryType.CREATIVE,
                InventoryType.ENCHANTING,
                InventoryType.ENDER_CHEST,
                InventoryType.MERCHANT,
                InventoryType.PLAYER,
                InventoryType.WORKBENCH
        ).contains(((InventoryHolder) block.getState()).getInventory().getType())) {
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
        if (args[1].equalsIgnoreCase("add")) {
            List<ContainerLootable> containers = DeathMaze.getInstance().getMaze().getContainers();
            for (ContainerLootable c : containers) {
                if (c.getLocation().getLocation().equals(block.getLocation())) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_COMMAND_ALREADY_REGISTERED);
                    return true;
                }
            }
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
            if (args.length < 3) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.HELP_HEADER);
                p.sendMessage(ColorUtil.format("&c/deathmaze lootable set refill <seconds> - &7Sets the refill time for the container"));
                return true;
            }
            if (args[2].equalsIgnoreCase("refill")) {
                if (args.length < 4) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.SET_REFILL_TIME_CONTAINER_NO_NUMBER);
                    return true;
                }
                try {
                    long seconds = Long.parseLong(args[3]);
                    List<ContainerLootable> containers = DeathMaze.getInstance().getMaze().getContainers();
                    for (ContainerLootable c : containers) {
                        if (c.getLocation().getLocation().equals(block.getLocation())) {
                            DeathMaze.getInstance().getMaze().getContainers().remove(c);
                            DeathMaze.getInstance().getMaze().getContainers().add(new ContainerLootable(
                                    seconds,
                                    (InventoryHolder) block.getState(),
                                    c.getLocation().getLocation()
                            ));
                            p.sendMessage(LangUtil.PREFIX + LangUtil.SET_REFILL_TIME_CONTAINER_SUCCESS);
                            DeathMaze.getInstance().reloadAll();
                            return true;
                        }
                    }
                    p.sendMessage(LangUtil.PREFIX + LangUtil.SET_REFILL_TIME_CONTAINER_COMMAND_FAIL_NOT_CONTAINER);
                    return true;
                } catch (NumberFormatException e) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.SET_REFILL_TIME_CONTAINER_NOT_NUMBER);
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
        if (args[1].equalsIgnoreCase("check")) {
            if (!DeathMaze.getInstance().getConfiguration().getEnabledWorlds().contains(p.getWorld())) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.CHECK_CONTAINER_LOOTABLE_FALSE);
                return true;
            }
            for (ContainerLootable container : DeathMaze.getInstance().getMaze().getContainers()) {
                if (container.getLocation().getLocation().equals(block.getLocation())) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.CHECK_CONTAINER_LOOTABLE_TRUE(container));
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.CHECK_CONTAINER_LOOTABLE_FALSE);
            return true;
        }
        p.sendMessage(LangUtil.PREFIX + LangUtil.INCORRECT_ARGS_MESSAGE);
        return true;
    }

    private void sendListFooter(Player player, int pageNumber) {
        TextComponent end = new TextComponent("]----[");
        end.setColor(net.md_5.bungee.api.ChatColor.GRAY);
        end.setStrikethrough(true);

        TextComponent previous = new TextComponent("◀◀");
        previous.setColor(net.md_5.bungee.api.ChatColor.DARK_RED);
        previous.setBold(true);
        previous.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("Previous")}));
        previous.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deathmaze lootable list previous"));

        TextComponent splitLeft = new TextComponent("]+");
        splitLeft.setColor(net.md_5.bungee.api.ChatColor.GRAY);
        splitLeft.setStrikethrough(true);

        TextComponent number = new TextComponent(String.valueOf(pageNumber));
        number.setBold(true);
        number.setColor(net.md_5.bungee.api.ChatColor.RED);

        TextComponent splitRight = new TextComponent("+[");
        splitRight.setColor(net.md_5.bungee.api.ChatColor.GRAY);
        splitRight.setStrikethrough(true);

        TextComponent next = new TextComponent("▶▶");
        next.setColor(net.md_5.bungee.api.ChatColor.DARK_RED);
        next.setBold(true);
        next.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("Next")}));
        next.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deathmaze lootable list next"));

        player.spigot().sendMessage(end, previous, splitLeft, number, splitRight, next, end);
    }

}
