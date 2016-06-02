package me.ilich.mymeteringdevices.data;

import android.database.Cursor;
import android.database.MatrixCursor;

import java.io.Serializable;

public class MeteringDevice implements Serializable {

    static String ID = "_id";
    static String NAME = "name";

    static String[] COLUMN_NAMES = {
            ID,
            NAME
    };

    public static MeteringDevice fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        return new MeteringDevice(id, name);
    }

    private final int id;
    private final String name;

    public MeteringDevice(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    void addToCursor(MatrixCursor cursor) {
        cursor.addRow(new Object[]{id, name});
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeteringDevice device = (MeteringDevice) o;

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
