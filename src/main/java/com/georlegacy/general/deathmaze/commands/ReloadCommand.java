package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.util.LangUtil;
import org.bukkit.command.CommandSender;

public class ReloadCommand {

    @Command(permission = "deathmaze.admin.reload", subCommand = "reload")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        DeathMaze.getInstance().reloadAll();
        sender.sendMessage(LangUtil.PREFIX + LangUtil.RELOAD_COMMAND_SUCCESS);
        return true;
    }

}
