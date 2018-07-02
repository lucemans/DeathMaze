package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.util.LangUtil;
import com.georlegacy.general.deathmaze.util.SerializableLocation;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand {

    @Command(permission = "deathmaze.admin.setspawn", subCommand = "setspawn")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player player = (Player) sender;

        DeathMaze.getInstance().getMaze().setSpawn(
                new SerializableLocation(
                        player.getLocation()
                )
        );

        player.sendMessage(LangUtil.PREFIX + LangUtil.MAZE_SET_SPAWN_SUCCESS);

        return true;
    }

}
