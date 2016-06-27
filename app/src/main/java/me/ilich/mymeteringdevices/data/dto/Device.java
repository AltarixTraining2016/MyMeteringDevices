package me.ilich.mymeteringdevices.data.dto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.annotation.VisibleForTesting;

public class Device extends Dto {

    static String ID = "_id";
    static String NAME = "name";
    static String DEVICE_TYPE_ID = "device_type_id";

    public static String[] COLUMN_NAMES = {
            ID,
            NAME
    };

    public static Device fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        int deviceTypeId = cursor.getInt(cursor.getColumnIndex(DEVICE_TYPE_ID));
        return new Device(id, name, deviceTypeId);
    }

    private final int id;
    private final String name;
    private final int typeId;

    public Device(String name, int deviceTypeId) {
        this.id = NOT_SET;
        this.name = name;
        this.typeId = deviceTypeId;
    }

    public Device(int id, String name, int deviceTypeId) {
        this.id = id;
        this.name = name;
        this.typeId = deviceTypeId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @VisibleForTesting
    public void addToCursor(MatrixCursor cursor) {
        cursor.addRow(new Object[]{id, name});
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (id != device.id) return false;
        return name != null ? name.equals(device.name) : device.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MeteringDevice " + id + " " + name;
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        if (id != NOT_SET) {
            cv.put(ID, id);
        }
        cv.put(NAME, name);
        cv.put(DEVICE_TYPE_ID, typeId);
        return cv;
    }

    public int getTypeId() {
        return typeId;
    }

}
