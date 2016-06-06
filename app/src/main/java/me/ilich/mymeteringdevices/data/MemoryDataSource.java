package me.ilich.mymeteringdevices.data;

import android.database.Cursor;
import android.database.MatrixCursor;

import java.util.ArrayList;
import java.util.List;

public class MemoryDataSource implements DataSource {

    private static final MemoryDataSource instance = new MemoryDataSource();

    public static MemoryDataSource getInstance() {
        return instance;
    }

    private List<MeteringDevice> meteringDeviceList = new ArrayList<>();
    private List<DeviceType> deviceTypesList = new ArrayList<>();

    private MemoryDataSource() {
        deviceTypesList.add(new DeviceType(1, "Газ"));
        deviceTypesList.add(new DeviceType(2, "Холодная вода"));
        deviceTypesList.add(new DeviceType(3, "Горячая вода"));
        deviceTypesList.add(new DeviceType(4, "Электричество"));
    }

    @Override
    public Cursor getMeteringDevices() {
        MatrixCursor cursor = new MatrixCursor(MeteringDevice.COLUMN_NAMES);
        for (MeteringDevice meteringDevice : meteringDeviceList) {
            meteringDevice.addToCursor(cursor);
        }
        return cursor;
    }

    @Override
    public void addMeteringDevice(MeteringDevice meteringDevice) {
        meteringDeviceList.add(meteringDevice);
    }

    @Override
    public void deleteMeteringDevice(int id) {
        MeteringDevice device = null;
        for (MeteringDevice meteringDevice : meteringDeviceList) {
            if (meteringDevice.getId() == id) {
                device = meteringDevice;
            }
        }
        meteringDeviceList.remove(device);
    }

    @Override
    public void deleteAllMeteringDevices() {
        meteringDeviceList.clear();
    }

    @Override
    public Cursor getDeviceTypes() {
        MatrixCursor cursor = new MatrixCursor(DeviceType.COLUMN_NAMES);
        for (DeviceType deviceType : deviceTypesList) {
            deviceType.addToCursor(cursor);
        }
        return cursor;
    }

    @Override
    public void deleteAllDeviceTypes() {
        deviceTypesList.clear();
    }

}
