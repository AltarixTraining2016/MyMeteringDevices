package me.ilich.mymeteringdevices.data.dto;

import android.database.Cursor;
import android.database.MatrixCursor;

public class Unit extends Dto {

    static String ID = "_id";
    static String NAME = "name";

    public static final String[] COLUMN_NAMES = {
            ID,
            NAME
    };

    public static Unit fromCursor(Cursor c) {
        int id = c.getInt(c.getColumnIndex(ID));
        String name = c.getString(c.getColumnIndex(NAME));
        return new Unit(id, name);
    }

    private final int id;
    private final String name;

    public Unit(String name) {
        this(NOT_SET, name);
    }

    public Unit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addToCursor(MatrixCursor matrixCursor) {
        matrixCursor.addRow(new Object[]{id, name});
    }

}
