package me.ilich.mymeteringdevices.data;

import android.database.Cursor;

import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.data.dto.Metering;
import me.ilich.mymeteringdevices.data.dto.Type;

public interface DataSource {

    Cursor unitsGetAll();


    Cursor typesGetAll();

    void typeChange(Type deviceType);

    void typesDeleteAll();

    void typeDelete(int id);


    Cursor devicesGetAll();

    Device deviceGet(int deviceId);

    void devicesChange(Device meteringDevice);

    void devicesDelete(int id);

    void devicesClear();


    Cursor meteringGet();

    void meteringChange(Metering metering);

    void meteringDelete(int id);

    void meteringClear();


    Cursor summaryGet();

}
