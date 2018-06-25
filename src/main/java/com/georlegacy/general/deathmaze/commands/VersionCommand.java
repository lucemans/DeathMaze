package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.util.ColorUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import org.bukkit.command.CommandSender;

public class VersionCommand {

    @Command(permission = "deathmaze.admin.version", subCommand = "version")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        sender.sendMessage(LangUtil.PREFIX + ColorUtil.format("&erunning &a&l" + DeathMaze.getInstance().getDescription().getVersion()));
        sender.sendMessage(LangUtil.PREFIX + ColorUtil.format("&eGet help at &d" + DeathMaze.getInstance().getDescription().getWebsite()));
        return true;
    }
}
