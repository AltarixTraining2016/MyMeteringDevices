package me.ilich.mymeteringdevices.data.dto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.annotation.VisibleForTesting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Запись о покозаниях счётчика.
 */
public class Metering extends Dto {

    private static final SimpleDateFormat DB_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

    static final String _ID = "_id";
    static final String CREATED = "created";
    static final String MEASURE = "measure";
    static final String DEVICE_ID = "device_id";

    public static final String[] COLUMN_NAMES = {
            _ID,
            CREATED,
            MEASURE,
            DEVICE_ID
    };

    public static Metering fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(_ID));
        String dateStr = cursor.getString(cursor.getColumnIndex(CREATED));
        Date date = null;
        try {
            date = DB_SDF.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double value = cursor.getDouble(cursor.getColumnIndex(MEASURE));
        int deviceId = cursor.getInt(cursor.getColumnIndex(DEVICE_ID));
        return new Metering(id, date, value, deviceId);
    }

    private final int id;
    private final Date date;
    private final double value;
    private final int deviceId;

    public Metering(Date date, double value, int deviceId) {
        this(NOT_SET, date, value, deviceId);
    }

    public Metering(int id, Date date, double value, int deviceId) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metering metering = (Metering) o;

        if (id != metering.id) return false;
        if (Double.compare(metering.value, value) != 0) return false;
        return date != null ? date.equals(metering.date) : metering.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    @VisibleForTesting
    public void addToCursor(MatrixCursor c) {
        c.addRow(new Object[]{id, DB_SDF.format(date), value, deviceId});
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        if (id != NOT_SET) {
            cv.put(_ID, id);
        }
        cv.put(CREATED, DB_SDF.format(date));
        cv.put(MEASURE, value);
        cv.put(DEVICE_ID, deviceId);
        return cv;
    }

    public int getDeviceId() {
        return deviceId;
    }
}
