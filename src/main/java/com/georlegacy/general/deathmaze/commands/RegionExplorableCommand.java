package com.georlegacy.general.deathmaze.commands;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;
import com.georlegacy.general.deathmaze.objects.RegionExplorable;
import com.georlegacy.general.deathmaze.util.ColorUtil;
import com.georlegacy.general.deathmaze.util.LangUtil;
import com.georlegacy.general.deathmaze.util.PositionPreview;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionExplorableCommand {

    @Command(permission = "deathmaze.admin.region", subCommand = "region")
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length == 1) {
            p.sendMessage(LangUtil.PREFIX + LangUtil.INCORRECT_ARGS_MESSAGE);
            return true;
        }
        if (args[1].equalsIgnoreCase("add")) {
            if (args.length < 3) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.INCORRECT_ARGS_MESSAGE);
                return true;
            }
            if (!DeathMaze.getInstance().getConfiguration().getEnabledWorlds().contains(p.getWorld())) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.COMMAND_WRONG_WORLD_MESSAGE);
                return true;
            }
            for (RegionExplorable r : DeathMaze.getInstance().getMaze().getRegions()) {
                if (args[2].equalsIgnoreCase(r.getName())) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_REGION_ALREADY_EXISTS);
                    return true;
                }
            }
            Selection s = DeathMaze.getInstance().getWorldedit().getSelection(p);
            if (s == null) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_REGION_NO_SELECTION);
                return true;
            }
            if (s.getHeight() < 2 || s.getLength() < 2 || s.getWidth() < 2) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_REGION_SELECTION_TOO_SMALL);
                return true;
            }
            if (!(s instanceof CuboidSelection)) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_REGION_NON_CUBOID_SELECTION);
                return true;
            }
            CuboidSelection cs = (CuboidSelection) s;
            for (RegionExplorable r : DeathMaze.getInstance().getMaze().getRegions()) {
                if (r.overlaps(cs)) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_REGION_EXISTING_OVERLAP);
                    return true;
                }
            }
            DeathMaze.getInstance().getMaze().getRegions().add(new RegionExplorable(
                    args[2],
                    cs.getMinimumPoint(),
                    cs.getMaximumPoint()
            ));
            p.sendMessage(LangUtil.PREFIX + LangUtil.ADD_REGION_SUCCESS);
            DeathMaze.getInstance().reloadAll();
            return true;
        }
        if (args[1].equalsIgnoreCase("preview")) {
            if (args.length < 3) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.PREVIEW_REGION_NO_REGION);
                return true;
            }
            for (RegionExplorable region : DeathMaze.getInstance().getMaze().getRegions()) {
                if (region.getName().equalsIgnoreCase(args[2])) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.PREVIEW_REGION_SUCCESS);
                    PositionPreview preview = new PositionPreview(region, p);
                    preview.show();
                    Bukkit.getScheduler().scheduleAsyncDelayedTask(DeathMaze.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            p.sendMessage(LangUtil.PREFIX + LangUtil.PREVIEW_REGION_END);
                            preview.hide();
                        }
                    }, 100L);
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.PREVIEW_REGION_NOT_REGION);
            return true;
        }
        if (args[1].equalsIgnoreCase("remove")) {
            if (args.length < 3) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.REMOVE_REGION_NO_REGION);
                return true;
            }
            for (RegionExplorable region : DeathMaze.getInstance().getMaze().getRegions()) {
                if (region.getName().equalsIgnoreCase(args[2])) {
                    p.sendMessage(LangUtil.PREFIX + LangUtil.REMOVE_REGION_SUCCESS);
                    DeathMaze.getInstance().getMaze().getRegions().remove(region);
                    DeathMaze.getInstance().reloadAll();
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.REMOVE_REGION_NOT_REGION);
            return true;
        }
        if (args[1].equalsIgnoreCase("set")) {
            if (args.length < 4) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.HELP_HEADER);
                p.sendMessage(ColorUtil.format("&c/deathmaze region set <region> sound <sound> - &7Sets the entry sound for the region."));
                return true;
            }
            for (RegionExplorable region : DeathMaze.getInstance().getMaze().getRegions()) {
                if (region.getName().equalsIgnoreCase(args[2])) {
                    if (args[3].equalsIgnoreCase("sound")) {
                        if (args.length < 5) {
                            p.sendMessage(LangUtil.PREFIX + LangUtil.SET_REGION_SOUND_NO_SOUND);
                            return true;
                        }
                        Sound sound;
                        try {
                            sound = Sound.valueOf(args[4].toUpperCase());
                        } catch (IllegalArgumentException e) {
                            p.sendMessage(LangUtil.PREFIX + LangUtil.SET_REGION_SOUND_NOT_SOUND);
                            return true;
                        }
                        region.setEntrySound(sound);
                        p.sendMessage(LangUtil.PREFIX + LangUtil.SET_REGION_SOUND_SUCCESS);
                        return true;
                    }
                    p.sendMessage(LangUtil.PREFIX + LangUtil.HELP_HEADER);
                    p.sendMessage(ColorUtil.format("&c/deathmaze region set sound <sound> - &7Sets the entry sound for the region."));
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.SET_REGION_NOT_REGION);
            return true;
        }
        if (args[1].equalsIgnoreCase("splash")) {
            if (args.length < 4) {
                p.sendMessage(LangUtil.PREFIX + LangUtil.HELP_HEADER);
                p.sendMessage(ColorUtil.format("&c/deathmaze region splash <region> <add|remove|list> <splash> - &7Controls the splashes for the region."));
                return true;
            }
            for (RegionExplorable region : DeathMaze.getInstance().getMaze().getRegions()) {
                if (region.getName().equalsIgnoreCase(args[2])) {
                    if (args[3].equalsIgnoreCase("add")) {
                        if (args.length < 5) {
                            p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_ADD_SPLASH_NO_SPLASH);
                            return true;
                        }
                        StringBuilder builder = new StringBuilder();
                        int i = 0;
                        for (String arg : args) {
                            if (!(i < 4)) builder.append(arg + " ");
                            i++;
                        }
                        String splash = builder.toString();
                        if (region.getEntrySplashes().contains(splash)) {
                            p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_ADD_SPLASH_EXISTS);
                            return true;
                        }
                        p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_ADD_SPLASH_SUCCESS);
                        region.getEntrySplashes().add(splash);
                        return true;
                    }
                    if (args[3].equalsIgnoreCase("remove")) {
                        if (args.length < 5) {
                            p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_REMOVE_SPLASH_NO_SPLASH);
                            return true;
                        }
                        StringBuilder builder = new StringBuilder();
                        int i = 0;
                        for (String arg : args) {
                            if (!(i < 4)) builder.append(arg + " ");
                            i++;
                        }
                        String splash = builder.toString();
                        for (String oldSplash : region.getEntrySplashes()) {
                            if (oldSplash.equalsIgnoreCase(splash)|| ChatColor.stripColor(oldSplash).equalsIgnoreCase(splash)) {
                                if (region.getEntrySplashes().size() == 1) {
                                    p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_REMOVE_SPLASH_NOT_ENOUGH);
                                    return true;
                                }
                                p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_REMOVE_SPLASH_SUCCESS);
                                region.getEntrySplashes().remove(oldSplash);
                                return true;
                            }
                        }
                        p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_REMOVE_SPLASH_NOT_SPLASH);
                        return true;
                    }
                    if (args[3].equalsIgnoreCase("list")) {
                        p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_LIST_SPLASH_HEADER);
                        for (String splash : region.getEntrySplashes()) {
                            p.sendMessage(ColorUtil.format("&a" + splash));
                        }
                        return true;
                    }
                    p.sendMessage(LangUtil.PREFIX + LangUtil.HELP_HEADER);
                    p.sendMessage(ColorUtil.format("&c/deathmaze region splash <region> <add|remove|list> <splash> - &7Controls the splashes for the region."));
                    return true;
                }
            }
            p.sendMessage(LangUtil.PREFIX + LangUtil.REGION_SPLASH_NOT_REGION);
            return true;
        }
        //TODO dm region check - provides current region, and shows the entry sound
        p.sendMessage(LangUtil.PREFIX + LangUtil.INCORRECT_ARGS_MESSAGE);
        return true;
    }

}
