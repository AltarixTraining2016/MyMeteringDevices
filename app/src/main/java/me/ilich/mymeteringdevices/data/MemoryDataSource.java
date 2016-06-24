package me.ilich.mymeteringdevices.data;

import android.database.Cursor;
import android.database.MatrixCursor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.data.dto.Metering;
import me.ilich.mymeteringdevices.data.dto.Summary;
import me.ilich.mymeteringdevices.data.dto.Type;

public class MemoryDataSource implements DataSource {

    private List<Device> deviceList = new ArrayList<>();
    private List<Type> typesList = new ArrayList<>();
    private List<Metering> meteringsList = new ArrayList<>();

    public MemoryDataSource() {
        typesList.add(new Type(1, "Газ"));
        typesList.add(new Type(2, "Вода"));
        typesList.add(new Type(4, "Электричество"));

        deviceList.add(new Device(1, "Горячая вода"));
        deviceList.add(new Device(2, "Холодная вода"));
    }

    @Override
    public Cursor devicesGet() {
        final MatrixCursor cursor = new MatrixCursor(Device.COLUMN_NAMES);
        for (Device meteringDevice : deviceList) {
            meteringDevice.addToCursor(cursor);
        }
        return cursor;
    }

    @Override
    public Device deviceGet(int deviceId) {
        Device result = null;
        for (Device device : deviceList) {
            if (device.getId() == deviceId) {
                result = device;
            }
        }
        return result;
    }

    @Override
    public void devicesChange(Device device) {
        Device newDevice = null;
        if (device.getId() != -1) {
            for (Iterator<Device> iterator = deviceList.iterator(); iterator.hasNext(); ) {
                Device d = iterator.next();
                if (d.getId() == device.getId()) {
                    iterator.remove();
                    newDevice = new Device(d.getId(), device.getName());
                    break;
                }
            }
            if (newDevice == null) {
                newDevice = new Device(device.getId(), device.getName());
            }
        }
        if (newDevice == null) {
            newDevice = new Device(deviceList.size(), device.getName());
        }
        deviceList.add(newDevice);
    }

    @Override
    public void devicesDelete(int id) {
        Device device = null;
        for (Device meteringDevice : deviceList) {
            if (meteringDevice.getId() == id) {
                device = meteringDevice;
            }
        }
        deviceList.remove(device);
    }

    @Override
    public void devicesClear() {
        deviceList.clear();
    }

    @Override
    public Cursor getDeviceTypes() {
        MatrixCursor cursor = new MatrixCursor(Type.COLUMN_NAMES);
        for (Type deviceType : typesList) {
            deviceType.addToCursor(cursor);
        }
        return cursor;
    }

    @Override
    public void deleteAllDeviceTypes() {
        typesList.clear();
    }

    @Override
    public void addDeviceType(Type deviceType) {
        typesList.add(deviceType);
    }

    @Override
    public void updateDeviceType(Type deviceType) {
        for (Type d : typesList) {
            if (d.getId() == deviceType.getId()) {
                d.setName(deviceType.getName());
            }
        }
    }

    @Override
    public void deleteDeviceType(int id) {
        for (Iterator<Type> iterator = typesList.iterator(); iterator.hasNext(); ) {
            Type deviceType = iterator.next();
            if (deviceType.getId() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public Cursor summaryGet() {
        MatrixCursor c = new MatrixCursor(Summary.COLUMN_NAMES);
        c.addRow(new Object[]{"Газ", "5", "120", "22.10.2016"});
        c.addRow(new Object[]{"Горячая вода", "245", "12000", "20.10.2016"});
        return c;
    }

    @Override
    public Cursor meteringGet() {
        MatrixCursor c = new MatrixCursor(Metering.COLUMN_NAMES);
        for (Metering metering : meteringsList) {
            metering.addToCursor(c);
        }
        return c;
    }

    @Override
    public void meteringDelete(int id) {

    }

    @Override
    public void meteringClear() {
        meteringsList.clear();
    }

    @Override
    public void meteringChange(Metering metering) {
        if (metering.getId() == -1) {
            Metering newMetering = new Metering(meteringsList.size(), metering.getDate(), metering.getValue());
            meteringsList.add(newMetering);
        } else {
            for (Iterator<Metering> iterator = meteringsList.iterator(); iterator.hasNext(); ) {
                Metering currentMetering = iterator.next();
                if (currentMetering.getId() == metering.getId()) {
                    Metering newMetering = new Metering(metering.getId(), metering.getDate(), metering.getValue());
                    iterator.remove();
                    meteringsList.add(newMetering);
                }
            }
        }
    }

}
