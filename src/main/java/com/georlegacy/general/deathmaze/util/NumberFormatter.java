package com.georlegacy.general.deathmaze.util;

import java.text.DecimalFormat;

import static java.lang.Long.MAX_VALUE;

public class NumberFormatter {

    private enum Unit {
        DEFAULT("", 0, 1000, 1),
        THOUSAND("k", 1000, 1000000, 0.001),
        MILLION("m", 1000000, 1000000000, 0.000001),
        BILLION("b", 1000000000, MAX_VALUE, 0.000000001);

        String displayName;
        long min;
        long max;
        double multiplier;

        Unit(String displayName, long min, long max, double multiplier) {
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
        return new DecimalFormat("#.#").format(toFormat * Unit.BILLION.multiplier) + Unit.BILLION.displayName;
    }

}
