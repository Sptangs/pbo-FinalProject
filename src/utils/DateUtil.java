package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat FORMATTER =
            new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static String format(Date date) {

        if (date == null) {
            return "-";
        }

        return FORMATTER.format(date);
    }
}