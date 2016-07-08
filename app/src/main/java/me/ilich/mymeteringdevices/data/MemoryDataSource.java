package me.ilich.mymeteringdevices.data;

import android.database.Cursor;
import android.database.MatrixCursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.data.dto.Metering;
import me.ilich.mymeteringdevices.data.dto.Summary;
import me.ilich.mymeteringdevices.data.dto.Type;
import me.ilich.mymeteringdevices.data.dto.Unit;

public class MemoryDataSource implements DataSource {

    private List<Unit> unitList = new ArrayList<>();
    private List<Device> deviceList = new ArrayList<>();
    private List<Type> typesList = new ArrayList<>();
    private List<Metering> meteringsList = new ArrayList<>();

    public MemoryDataSource() {
        unitList.add(new Unit(1, "м3"));
        unitList.add(new Unit(2, "кВт*ч"));

        typesList.add(new Type(1, "Газ", 1));
        typesList.add(new Type(2, "Вода", 1));
        typesList.add(new Type(4, "Электричество", 2));

        deviceList.add(new Device(1, "Горячая вода", 2));
        deviceList.add(new Device(2, "Холодная вода", 2));
    }

    @Override
    public Cursor unitsGetAll() {
        MatrixCursor matrixCursor = new MatrixCursor(Unit.COLUMN_NAMES);
        for (Unit unit : unitList) {
            unit.addToCursor(matrixCursor);
        }
        return matrixCursor;
    }

    @Override
    public Cursor devicesGetAll() {
        final MatrixCursor cursor = new MatrixCursor(Device.COLUMN_NAMES);
        for (Device device : deviceList) {
            device.addToCursor(cursor);
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
                    newDevice = new Device(d.getId(), device.getName(), device.getTypeId());
                    break;
                }
            }
            if (newDevice == null) {
                newDevice = new Device(device.getId(), device.getName(), device.getTypeId());
            }
        }
        if (newDevice == null) {
            newDevice = new Device(deviceList.size(), device.getName(), device.getTypeId());
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
    public Cursor typesGetAll() {
        MatrixCursor cursor = new MatrixCursor(Type.COLUMN_NAMES);
        for (Type deviceType : typesList) {
            deviceType.addToCursor(cursor);
        }
        return cursor;
    }

    @Override
    public void typesDeleteAll() {
        typesList.clear();
    }

    @Override
    public void typeChange(Type newType) {
        boolean contains = false;
        Type oldType = null;
        for (Iterator<Type> iterator = typesList.iterator(); iterator.hasNext(); ) {
            Type deviceType = iterator.next();
            if (deviceType.getId() == newType.getId()) {
                oldType = deviceType;
                contains = true;
                iterator.remove();
            }
        }
        if (!contains) {
            Type type = new Type(typesList.size(), newType.getName(), newType.getUnitId());
            typesList.add(type);
        } else {
            Type type = new Type(oldType.getId(), newType.getName(), newType.getUnitId());
            typesList.add(type);
        }
    }

    @Override
    public void typeDelete(int id) {
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
        new Summary(1, "device 1", 10, unit, new Date()).addToCursor(c);
        new Summary(2, "device 2", 20, unit, new Date()).addToCursor(c);
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
        for (Iterator<Metering> iterator = meteringsList.iterator(); iterator.hasNext(); ) {
            Metering metering = iterator.next();
            if (metering.getId() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public void meteringClear() {
        meteringsList.clear();
    }

    @Override
    public void meteringChange(Metering metering) {
        if (metering.getId() == -1) {
            Metering newMetering = new Metering(meteringsList.size(), metering.getDate(), metering.getValue(), metering.getDeviceId());
            meteringsList.add(newMetering);
        } else {
            for (Iterator<Metering> iterator = meteringsList.iterator(); iterator.hasNext(); ) {
                Metering currentMetering = iterator.next();
                if (currentMetering.getId() == metering.getId()) {
                    Metering newMetering = new Metering(metering.getId(), metering.getDate(), metering.getValue(), metering.getDeviceId());
                    iterator.remove();
                    meteringsList.add(newMetering);
                }
            }
        }
    }

}
