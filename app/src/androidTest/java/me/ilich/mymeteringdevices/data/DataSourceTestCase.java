package me.ilich.mymeteringdevices.data;

import android.database.Cursor;

import junit.framework.TestCase;

public abstract class DataSourceTestCase extends TestCase {

    private DataSource dataSource;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dataSource = createDataSource();
    }

    protected abstract DataSource createDataSource();

    public void testMeteringDevicesListNotNull() {
        Cursor c = dataSource.getMeteringDevices();
        assertNotNull(c);
        c.close();
    }

    public void testMeteringDevicesClear() {
        dataSource.deleteAllMeteringDevices();
        Cursor c = dataSource.getMeteringDevices();
        assertEquals(0, c.getCount());
        c.close();
    }

    public void testMeteringDevicesAdd() {
        dataSource.deleteAllMeteringDevices();
        MeteringDevice device1 = new MeteringDevice(1, "device 1");

        dataSource.addMeteringDevice(device1);

        Cursor c2 = dataSource.getMeteringDevices();
        assertEquals(1, c2.getCount());
        c2.moveToFirst();
        MeteringDevice actualDevice = MeteringDevice.fromCursor(c2);
        c2.close();

        assertEquals(device1, actualDevice);
    }

    public void testMeteringDevicesDeleteById() {
        dataSource.deleteAllMeteringDevices();
        MeteringDevice device1 = new MeteringDevice(1, "device 1");

        dataSource.addMeteringDevice(device1);
        dataSource.deleteMeteringDevice(device1.getId());

        Cursor c2 = dataSource.getMeteringDevices();
        assertEquals(0, c2.getCount());
        c2.close();

    }

    public void testMeteringDevicesDeleteAll() {
        dataSource.deleteAllMeteringDevices();
        MeteringDevice device1 = new MeteringDevice(1, "device 1");

        dataSource.addMeteringDevice(device1);
        dataSource.deleteAllMeteringDevices();

        Cursor c2 = dataSource.getMeteringDevices();
        assertEquals(0, c2.getCount());
        c2.close();

    }

    public void testDeviceTypes() {

        Cursor c;

        c = dataSource.getMeteringDevices();
        assertNotNull(c);
        c.close();

        dataSource.deleteAllDeviceTypes();

        c = dataSource.getMeteringDevices();
        assertEquals(0, c.getCount());
        c.close();

    }

}
