package me.ilich.mymeteringdevices.data;

import android.database.Cursor;

import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.data.dto.Metering;
import me.ilich.mymeteringdevices.data.dto.Type;

public interface DataSource {

    Cursor devicesGet();

    Device deviceGet(int deviceId);

    void devicesChange(Device meteringDevice);

    void devicesDelete(int id);

    void devicesClear();

    Cursor getDeviceTypes();

    void deleteAllDeviceTypes();

    void addDeviceType(Type deviceType);

    void updateDeviceType(Type deviceType);

    void deleteDeviceType(int id);

    Cursor summaryGet();

    Cursor meteringGet();

    void meteringDelete(int id);

    void meteringClear();

    void meteringChange(Metering metering);

}
