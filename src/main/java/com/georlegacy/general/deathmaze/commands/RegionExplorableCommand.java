package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.nio.ch.SelectorImpl;

public class RegionExplorableCommand {

    @Command(permission = "deathmaze.admin.region", subCommand = "region")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args[1].equalsIgnoreCase("add")) {
            Selection s = DeathMaze.getInstance().getWorldedit().getSelection(p);
            if (s == null) {
                //TODO no selection lol
                return true;
            }
            if (s.getHeight() < 2 || s.getLength() < 2 || s.getWidth() < 2) {
                //TODO too small xd
                return true;
            }
            if (!(s instanceof CuboidSelection)) {
                //TODO not allowed xd
                return true;
            }
        }
        if (args[1].equalsIgnoreCase("remove")) {

        }
        if (args[1].equalsIgnoreCase("set")) {

        }
        if (args[1].equalsIgnoreCase("splash")) {

        }


        return true;
    }

}
