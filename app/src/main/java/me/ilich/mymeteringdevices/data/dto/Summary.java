package me.ilich.mymeteringdevices.data.dto;

import android.database.Cursor;

import java.util.Date;

public class Summary extends Dto {

    private static String TITLE = "title";
    private static String LAST_VALUE = "last_value";
    private static String LAST_DATE = "last_date";
    private static String TOTAL_VALUE = "total_value";

    public static String[] COLUMN_NAMES = {
            TITLE,
            LAST_VALUE,
            LAST_DATE,
            TOTAL_VALUE
    };

    public static Summary fromCursor(Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(TITLE));
        return new Summary(title, 0, new Date(), 1);
    }

    private final String title;
    private final double lastMetaringValue;
    private final Date lastMetaringDate;
    private final double totalMetaringValue;

    public Summary(String title, double lastMetaringValue, Date lastMetaringDate, double totalMetaringValue) {
        this.title = title;
        this.lastMetaringValue = lastMetaringValue;
        this.lastMetaringDate = lastMetaringDate;
        this.totalMetaringValue = totalMetaringValue;
    }

}
