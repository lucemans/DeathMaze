package com.georlegacy.general.deathmaze.util;

import com.georlegacy.general.deathmaze.DeathMaze;
import com.georlegacy.general.deathmaze.objects.Maze;
import com.georlegacy.general.deathmaze.objects.PlayerStats;

import java.io.*;

public class MazeEncoder {

    public static boolean encode(Maze maze) {
        try {
            File file = new File(DeathMaze.getInstance().getDataFolder() + File.separator + "maze.dat");
            if (!file.exists())
                file.createNewFile();
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
        System.out.println("decoding");
        File file = new File(DeathMaze.getInstance().getDataFolder() + File.separator + "maze.dat");
        if (!file.exists()) {
            System.out.println("doesnt exist");
            return new Maze();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("returning");
            Maze m = (Maze) ois.readObject();
            System.out.println(m);
            return m;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("an exception");
            e.printStackTrace();
            return null;
        }
    }


}
