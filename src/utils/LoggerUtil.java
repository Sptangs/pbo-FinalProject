// ===============================
// FILE: utils/LoggerUtil.java
// ===============================

package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {

    private static final boolean DEBUG = true;

    private static final SimpleDateFormat FORMATTER =
            new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void debug(String message) {

        if (DEBUG) {

            System.out.println(
                    "[DEBUG] "
                    + FORMATTER.format(new Date())
                    + " -> "
                    + message);
        }
    }

    public static void error(String message) {

        System.err.println(
                "[ERROR] "
                + FORMATTER.format(new Date())
                + " -> "
                + message);
    }
}