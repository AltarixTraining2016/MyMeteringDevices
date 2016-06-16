package me.ilich.mymeteringdevices.data.dto;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.support.annotation.VisibleForTesting;

import java.io.Serializable;

public class Device extends Dto {

    static String ID = "_id";
    static String NAME = "name";

    public static String[] COLUMN_NAMES = {
            ID,
            NAME
    };

    public static Device fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        return new Device(id, name);
    }

    private final int id;
    private final String name;

    public Device(int id, String name) {
        this.id = id;
        this.name = name;
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

}
