package me.ilich.mymeteringdevices.data;

import android.database.Cursor;

public interface DataSource {

    Cursor getMeteringDevices();

    void addMeteringDevice(MeteringDevice meteringDevice);

    void deleteMeteringDevice(int id);

    void deleteAllMeteringDevices();

    Cursor getDeviceTypes();

    void deleteAllDeviceTypes();

    void addDeviceType(DeviceType deviceType);

    void updateDeviceType(DeviceType deviceType);

    void deleteDeviceType(int id);

}
