package me.ilich.mymeteringdevices.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.VisibleForTesting;

import me.ilich.mymeteringdevices.data.db.DatabaseHelper;
import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.data.dto.Metering;
import me.ilich.mymeteringdevices.data.dto.Type;

public class DbDataSource implements DataSource {

    private final DatabaseHelper databaseHelper;
    private final SQLiteDatabase db;

    public DbDataSource(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    @VisibleForTesting
    public String getDbFileName() {
        return databaseHelper.getDatabaseName();
    }

    @Override
    public Cursor unitsGetAll() {
        return db.rawQuery("SELECT _id, name FROM units ORDER BY name", null);
    }

    @Override
    public Cursor devicesGetAll() {
        return db.rawQuery("SELECT devices._id AS '_id', devices.name AS 'name', devices.device_type_id AS 'device_type_id', device_types.name AS 'device_type_name' FROM devices LEFT JOIN device_types ON device_types._id = devices.device_type_id ORDER BY devices.name", null);
    }

    @Override
    public Device deviceGet(int deviceId) {
        return null;
    }

    @Override
    public void devicesChange(Device meteringDevice) {
        ContentValues cv = meteringDevice.toContentValues();
        db.insertWithOnConflict("devices", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void devicesDelete(int id) {
        db.execSQL("DELETE FROM devices WHERE _id = ?", new Object[]{id});
    }

    @Override
    public void devicesClear() {
        db.execSQL("DELETE FROM devices");
    }

    @Override
    public Cursor typesGetAll() {
        return db.rawQuery("SELECT _id, name, unit_id FROM device_types ORDER BY name", null);
    }

    @Override
    public void typesDeleteAll() {
        db.execSQL("DELETE FROM device_types");
    }

    @Override
    public void typeChange(Type deviceType) {
        ContentValues cv = deviceType.toContentValues();
        db.insertWithOnConflict("device_types", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void deleteDeviceType(int id) {
        db.delete("device_types", "_id = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public Cursor summaryGet() {
        return null;
    }

    @Override
    public Cursor meteringGet() {
        return db.rawQuery("SELECT _id, created, measure, device_id FROM meterings ORDER BY created", null);
    }

    @Override
    public void meteringDelete(int id) {
        db.execSQL("DELETE FROM meterings WHERE _id = ?", new Object[]{id});
    }

    @Override
    public void meteringClear() {
        db.execSQL("DELETE FROM meterings", new Object[]{});
    }

    @Override
    public void meteringChange(Metering metering) {
        ContentValues cv = metering.toContentValues();
        db.insertWithOnConflict("meterings", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

}
