package me.ilich.mymeteringdevices.data;

import android.database.Cursor;

import me.ilich.mymeteringdevices.data.dto.DeviceType;
import me.ilich.mymeteringdevices.data.dto.MeteringDevice;

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
