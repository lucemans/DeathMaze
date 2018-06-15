package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.ContainerLootable;
import com.georlegacy.general.deathmaze.util.LangUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddContainerLootableCommand implements Command {
    private final DeathMaze plugin;
    public AddContainerLootableCommand(DeathMaze plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player p = (Player) sender;
        Block block = p.getTargetBlock(null, 5);
        if (!(block instanceof Container)) {
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
        plugin.getMaze().getContainers().add(new ContainerLootable(
                plugin.getConfiguration().getDefaultRefillMillis(),
                (Container) block
        ));
        p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_CONTAINER_COMMAND_SUCCESS);
        return true;
    }

    @Override
    public String permission() {
        return "deathmaze.addlootablecontainer";
    }

    @Override
    public String subCommand() {
        return "add lootable";
    }
}
