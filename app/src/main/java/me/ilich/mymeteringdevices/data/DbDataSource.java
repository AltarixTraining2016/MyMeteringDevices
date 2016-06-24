package me.ilich.mymeteringdevices.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    @Override
    public Cursor devicesGet() {
        return null;
    }

    @Override
    public Device deviceGet(int deviceId) {
        return null;
    }

    @Override
    public void devicesChange(Device meteringDevice) {

    }

    @Override
    public void devicesDelete(int id) {

    }

    @Override
    public void devicesClear() {

    }

    @Override
    public Cursor getDeviceTypes() {
        return databaseHelper.getWritableDatabase().rawQuery("SELECT _id, name FROM device_types ORDER BY name", null);
    }

    @Override
    public void deleteAllDeviceTypes() {

    }

    @Override
    public void addDeviceType(Type deviceType) {
        ContentValues cv = new ContentValues();
        if (deviceType.getId() != Type.NOT_SET) {
            cv.put("_id", deviceType.getId());
        }
        cv.put("name", deviceType.getName());
        databaseHelper.getWritableDatabase().insertWithOnConflict("device_types", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void updateDeviceType(Type deviceType) {

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
        return null;
    }

    @Override
    public void meteringDelete(int id) {

    }

    @Override
    public void meteringClear() {

    }

    @Override
    public void meteringChange(Metering metering) {

    }

    public String getDbFileName() {
        return databaseHelper.getDatabaseName();
    }

}
