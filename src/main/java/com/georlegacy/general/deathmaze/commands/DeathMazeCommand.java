package com.georlegacy.general.deathmaze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DeathMazeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            //TODO send help
            return true;
        }
        switch (args[0]) {
            //TODO add commands
            default:
                //TODO send help
                break;
        }

        return true;
    }

}
