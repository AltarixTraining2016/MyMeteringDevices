package me.ilich.mymeteringdevices.data;

import android.database.Cursor;
import android.database.MatrixCursor;

public class DeviceType {

    static String ID = "_id";
    static String NAME = "name";

    public static final String[] COLUMN_NAMES = {
            ID,
            NAME
    };

    public static DeviceType fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        return new DeviceType(id, name);
    }

    private final int id;
    private final String name;

    public DeviceType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceType type = (DeviceType) o;

        if (id != type.id) return false;
        return name != null ? name.equals(type.name) : type.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public void addToCursor(MatrixCursor cursor) {
        cursor.addRow(new Object[]{id, name});
    }

}
