package com.georlegacy.general.deathmaze.util;

import java.util.HashMap;

public class XPCalcUtil {

    public HashMap<Integer, Integer> levelXps = new HashMap<Integer, Integer>();

    public XPCalcUtil() {
        this.levelXps.put(1, 10);
        this.levelXps.put(2, 50);
        this.levelXps.put(3, 100);
        this.levelXps.put(4, 150);
        this.levelXps.put(5, 200);
        this.levelXps.put(6, 250);
        this.levelXps.put(7, 300);
        this.levelXps.put(8, 400);
        this.levelXps.put(9, 500);
        this.levelXps.put(10, 750);
        this.levelXps.put(11, 1000);
        this.levelXps.put(12, 1500);
        this.levelXps.put(13, 2000);
        this.levelXps.put(14, 3000);
        this.levelXps.put(15, 5000);
    }

}
