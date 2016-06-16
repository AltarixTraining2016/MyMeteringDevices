package me.ilich.mymeteringdevices.data.dto;

import android.database.Cursor;
import android.database.MatrixCursor;

public class Type extends Dto {

    static String ID = "_id";
    static String NAME = "name";

    public static final String[] COLUMN_NAMES = {
            ID,
            NAME
    };

    public static Type fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        return new Type(id, name);
    }

    private final int id;
    private String name;

    public Type(String name) {
        this.id = -1;
        this.name = name;
    }

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

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
