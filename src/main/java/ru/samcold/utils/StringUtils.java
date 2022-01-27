package ru.samcold.utils;

import padeg.lib.Padeg;
import ru.samcold.repairing.Extraction;

public class StringUtils {
    // region singleton
    private static StringUtils instance;

    private StringUtils() {
    }

    public static StringUtils getInstance() {
        if (instance == null) {
            instance = new StringUtils();
        }
        return instance;
    }
    // endregion

    public String strToRod(String inputStr) {
        inputStr = inputStr.replaceAll("\\s+", " ").trim();
        String[] arrStr = inputStr.split(" ");

        String resStr = "";
        for (String str : arrStr) {
            if (str != null && !str.equals("") && !str.equals(" ")) {
                resStr += Padeg.getAppointmentPadeg(str, 2) + " ";
            }
        }

        return resStr.trim();
    }

    public String firstToLower(String inputStr) {
        String resStr = inputStr.substring(0, 0) + Character.toLowerCase(inputStr.charAt(0)) + inputStr.substring(1);
        return resStr;
    }
}
