package com.georlegacy.general.deathmaze.util;

import java.text.DecimalFormat;

public class DistanceFormatter {

    private enum Unit {
        METRES("m", 0, 1000, 1),
        KILOMETRES("km", 1000, 1000000, 0.001);

        String displayName;
        int min;
        int max;
        double multiplier;

        Unit(String displayName, int min, int max, double multiplier) {
            this.displayName = displayName;
            this.min = min;
            this.max = max;
            this.multiplier = multiplier;
        }
    }

    public static String format(int toFormat) {
        for (Unit u : Unit.values()) {
            if (toFormat >= u.min && toFormat <= u.max) {
                return new DecimalFormat("#.#").format(toFormat * u.multiplier) + u.displayName;
            }
        }
        try {
            throw new Exception("The number was not formatted. The most likely cause for this is that it was off the scale.");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(double toFormat) {
        for (Unit u : Unit.values()) {
            if (toFormat >= u.min && toFormat <= u.max) {
                return new DecimalFormat("#.#").format(toFormat * u.multiplier) + u.displayName;
            }
        }
        try {
            throw new Exception("The number was not formatted. The most likely cause for this is that it was off the scale.");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
