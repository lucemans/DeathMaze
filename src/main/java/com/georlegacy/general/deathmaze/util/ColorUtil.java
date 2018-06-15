package com.georlegacy.general.deathmaze.util;

import org.bukkit.ChatColor;

public class ColorUtil {

    public static String format(String toFormat) {
        return ChatColor.translateAlternateColorCodes('&', toFormat);
    }

}
