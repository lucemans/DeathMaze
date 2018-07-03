package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VisitCommand {

    @Command(permission = "deathmaze.user.visit", subCommand = "visit")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player player = (Player) sender;
        sender.sendMessage(DeathMaze.getInstance().getConfiguration().getVisitMessage(player));
        player.playSound(player.getLocation(), DeathMaze.getInstance().getConfiguration().getVisitSound(), 1, 1);
        player.teleport(DeathMaze.getInstance().getMaze().getSpawn().getLocation());
        return true;
    }

}
