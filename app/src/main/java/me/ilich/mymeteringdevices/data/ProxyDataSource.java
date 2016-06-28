package me.ilich.mymeteringdevices.data;

import android.database.Cursor;

import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.data.dto.Metering;
import me.ilich.mymeteringdevices.data.dto.Type;

public class ProxyDataSource implements DataSource {


    @Override
    public Cursor unitsGetAll() {
        return null;
    }

    @Override
    public Cursor devicesGetAll() {
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
    public Cursor typesGetAll() {
        return null;
    }

    @Override
    public void typesDeleteAll() {

    }

    @Override
    public void typeChange(Type deviceType) {

    }

    @Override
    public void typeDelete(int id) {

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
}
