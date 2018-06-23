package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;

import java.io.*;

public class StatsEncoder {

    public static boolean encode(PlayerStats stats) {
        try {
            File f = new File(DeathMaze.getInstance().getDataFolder() + File.separator + "players", stats.getUuid() + ".dat");
            if (!f.exists())
                f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(stats);
            oos.flush();
            oos.close();
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static PlayerStats decode(String name) {
        File f = new File(DeathMaze.getInstance().getDataFolder() + File.separator + "players", name + ".dat");
        if (!f.exists())
            return null;
        try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
            try {
                return (PlayerStats) ois.readObject();
            } finally {
                fis.close();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PlayerStats decode(File file) {
        if (!file.exists())
            return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (PlayerStats) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
