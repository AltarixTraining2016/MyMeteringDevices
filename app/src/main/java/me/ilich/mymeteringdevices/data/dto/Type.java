package me.ilich.mymeteringdevices.data.dto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;

public class Type extends Dto {

    static String ID = "_id";
    static String NAME = "name";
    static String UNIT_ID = "unit_id";

    public static final String[] COLUMN_NAMES = {
            ID,
            NAME,
            UNIT_ID
    };

    public static Type fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        int unitId = cursor.getInt(cursor.getColumnIndex(UNIT_ID));
        return new Type(id, name, unitId);
    }

    private final int id;
    private final String name;
    private final int unitId;

    public Type(String name, int unitId) {
        this(NOT_SET, name, unitId);
    }

    public Type(int id, String name, int unitId) {
        this.id = id;
        this.name = name;
        this.unitId = unitId;
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

        Type type = (Type) o;

        if (id != type.id) return false;
        if (unitId != type.unitId) return false;
        return name != null ? name.equals(type.name) : type.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + unitId;
        return result;
    }

    public void addToCursor(MatrixCursor cursor) {
        cursor.addRow(new Object[]{id, name, unitId});
    }

    public int getUnitId() {
        return unitId;
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        if (id != Type.NOT_SET) {
            cv.put(ID, id);
        }
        cv.put(NAME, name);
        cv.put(UNIT_ID, unitId);
        return cv;
    }

}
