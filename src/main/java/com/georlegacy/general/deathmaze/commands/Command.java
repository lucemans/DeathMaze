package com.georlegacy.general.deathmaze.commands;

import org.bukkit.command.CommandSender;

public interface Command {

    String permission();

    String subCommand();

    boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);

}
