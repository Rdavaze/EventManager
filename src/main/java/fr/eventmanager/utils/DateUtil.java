package fr.eventmanager.utils;

/**
 * Created by guillaume-chs on 31/10/16.
 */
public class DateUtil {
    private static final String[] months = {
            "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
    };

    public static String getMonth(int index) {
        if (index < 0 || index > 11) {
            return "";
        }
        return months[index];
    }

    public static int getMonthIndex(String month) {
        final String toUpperCase = month.toUpperCase();
        for (int i = 0; i < 12; i++) {
            if (months[i].toUpperCase().equals(toUpperCase)) {
                return i;
            }
        }
        return -1;
    }

    public static String[] getMonths() {
        return months;
    }
}
