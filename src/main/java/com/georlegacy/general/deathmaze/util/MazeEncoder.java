package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.Maze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;

import java.io.*;

public class MazeEncoder {

    public static boolean encode(Maze maze) {
        try {
            File f = new File(DeathMaze.getInstance().getDataFolder() + File.separator + "maze.dat");
            if (!f.exists())
                f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(maze);
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

    public static Maze decode() {
        File f = new File(DeathMaze.getInstance().getDataFolder() + File.separator + "maze.dat");
        if (!f.exists())
            return new Maze();
        try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
            try {
                return (Maze) ois.readObject();
            } finally {
                fis.close();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
