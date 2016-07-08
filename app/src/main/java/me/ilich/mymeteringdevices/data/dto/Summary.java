package me.ilich.mymeteringdevices.data.dto;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.annotation.VisibleForTesting;

import java.text.ParseException;
import java.util.Date;

public class Summary extends Dto {

    private static String DEVICE_ID = "device_id";
    private static String DEVICE_NAME = "device_name";
    private static String LAST_VALUE = "last_measure";
    private static String LAST_DATE = "last_created";
    private static String UNIT = "unit";

    public static String[] COLUMN_NAMES = {
            DEVICE_ID,
            DEVICE_NAME,
            LAST_VALUE,
            LAST_DATE,
            UNIT
    };

    public static Summary fromCursor(Cursor cursor) {
        int deviceId = cursor.getInt(cursor.getColumnIndex(DEVICE_ID));
        String deviceName = cursor.getString(cursor.getColumnIndex(DEVICE_NAME));
        double lastMeteringValue = cursor.getDouble(cursor.getColumnIndex(LAST_VALUE));
        Date lastMeteringDate = null;
        try {
            lastMeteringDate = DB_SDF.parse(cursor.getString(cursor.getColumnIndex(LAST_DATE)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String unit = cursor.getString(cursor.getColumnIndex(UNIT));
        return new Summary(deviceId, deviceName, lastMeteringValue, unit, lastMeteringDate);
    }

    private final int deviceId;
    private final String deviceName;
    private final double lastMeteringValue;
    private final String unit;
    private final Date lastMeteringDate;

    public Summary(int deviceId, String deviceName, double lastMeteringValue, String unit, Date lastMeteringDate) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.lastMeteringValue = lastMeteringValue;
        this.unit = unit;
        this.lastMeteringDate = lastMeteringDate;
    }

    @VisibleForTesting
    public void addToCursor(MatrixCursor c) {
        c.addRow(new Object[]{deviceId, deviceName, lastMeteringValue, lastMeteringDate, unit});
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Date getLastDate() {
        return lastMeteringDate;
    }

}
