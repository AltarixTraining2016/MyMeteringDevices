package me.ilich.mymeteringdevices.data;

import android.database.Cursor;

public interface DataSource {

    Cursor getMeteringDevices();

    void addMeteringDevice(MeteringDevice meteringDevice);

    void deleteMeteringDevice(int id);

    void deleteAllMeteringDevices();

    Cursor getDeviceTypes();

    void deleteAllDeviceTypes();

}
