package se.bahram.tdd.kata.countryrestapi.commons;

import java.util.Objects;

public class NumberUtils {

    public static boolean isInteger(String str) {
        if (Objects.isNull(str)) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
