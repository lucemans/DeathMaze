package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.Maze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;

import java.io.*;

public class MazeEncoder {

    public static boolean encode(Maze maze) {
        try {
            File file = new File(DeathMaze.getInstance().getDataFolder() + File.separator + "maze.dat");
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeUnshared(maze);
            oos.flush();
            oos.close();
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
        File file = new File(DeathMaze.getInstance().getDataFolder() + File.separator + "maze.dat");
        if (!file.exists()) {
            return new Maze();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Maze) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
