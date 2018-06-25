package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.util.ColorUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DeathMazeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(LangUtil.PREFIX + LangUtil.HELP_HEADER);
            sender.sendMessage(ColorUtil.format("&c/deathmaze - &7Displays this help menu"));
            sender.sendMessage(ColorUtil.format("&c/deathmaze lootable <add|remove|set|update|check> - &7Controls lootable containers"));
            sender.sendMessage(ColorUtil.format("&c/deathmaze region <add|preview|remove|set|splash> - &7Controls regions"));
            return true;
        }
        com.georlegacy.general.deathmaze.commands.Command c;
        switch (args[0].toLowerCase()) {
            case "lootable":
                c = getAnnotation(ContainerLootableCommand.class);
                if (!sender.hasPermission(c.permission()))
                    sender.sendMessage(LangUtil.PREFIX + LangUtil.NO_PERMISSION_MESSAGE);
                new ContainerLootableCommand().onCommand(sender, command, label, args);
                break;
            case "region":
                c = getAnnotation(RegionExplorableCommand.class);
                if (!sender.hasPermission(c.permission()))
                    sender.sendMessage(LangUtil.PREFIX + LangUtil.NO_PERMISSION_MESSAGE);
                new RegionExplorableCommand().onCommand(sender, command, label, args);
                break;
            case "reload":
                break;
            case "version":
                c = getAnnotation(VersionCommand.class);
                if (!sender.hasPermission(c.permission()))
                    sender.hasPermission(LangUtil.PREFIX + LangUtil.NO_PERMISSION_MESSAGE);
                new VersionCommand().onCommand(sender, command, label, args);
                break;
            default:
                sender.sendMessage(LangUtil.PREFIX + LangUtil.HELP_HEADER);
                sender.sendMessage(ColorUtil.format("&c/deathmaze - &7Displays this help menu"));
                sender.sendMessage(ColorUtil.format("&c/deathmaze lootable <add|remove|set|update|check> - &7Controls lootable containers"));
                sender.sendMessage(ColorUtil.format("&c/deathmaze region <add|preview|remove|set|splash> - &7Controls regions"));
                sender.sendMessage(ColorUtil.format("&c/deathmaze reload - &7Coming soon"));
                sender.sendMessage(ColorUtil.format("&c/deathmaze version - &7Displays version and info"));
                break;
        }
        return true;
    }

    private com.georlegacy.general.deathmaze.commands.Command getAnnotation(Class clazz) {
        try {
            Method m = clazz.getMethod("onCommand", CommandSender.class, Command.class, String.class, String[].class);
            return m.getAnnotation(com.georlegacy.general.deathmaze.commands.Command.class);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
