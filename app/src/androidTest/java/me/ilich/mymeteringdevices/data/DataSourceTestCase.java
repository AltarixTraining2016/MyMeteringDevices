package me.ilich.mymeteringdevices.data;

import android.database.Cursor;

import junit.framework.TestCase;

import java.util.Date;

import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.data.dto.Metering;
import me.ilich.mymeteringdevices.data.dto.Type;

public abstract class DataSourceTestCase extends TestCase {

    private DataSource dataSource;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dataSource = createDataSource();
    }

    protected abstract DataSource createDataSource();

    private void assertCursorSizeAndClose(Cursor cursor, int expectedSize) {
        assertNotNull(cursor);
        assertEquals(expectedSize, cursor.getCount());
        cursor.close();
    }

    private void assertCursorContainsAndClose(Cursor cursor, Metering... meterings) {
        assertNotNull(cursor);
        assertEquals(meterings.length, cursor.getCount());
        assertTrue(cursor.moveToFirst());
        int i = 0;
        do {
            Metering actualMetering = Metering.fromCursor(cursor);
            Metering expectedMetering = meterings[i];
            assertEquals(expectedMetering.getDate().getTime(), actualMetering.getDate().getTime());
            assertEquals(expectedMetering.getValue(), actualMetering.getValue());
            i++;
        } while (cursor.moveToNext());
        cursor.close();
    }

    public void testDevices() {

        Cursor c;

        c = dataSource.devicesGet();
        assertNotNull(c);
        c.close();

        dataSource.devicesClear();
        assertCursorSizeAndClose(dataSource.devicesGet(), 0);

        Device device1 = new Device(1, "device 1");

        dataSource.devicesChange(device1);

        Cursor c2 = dataSource.devicesGet();
        assertEquals(1, c2.getCount());
        c2.moveToFirst();
        Device actualDevice = Device.fromCursor(c2);
        c2.close();

        assertEquals(device1, actualDevice);

        dataSource.devicesClear();

        {
            String name2 = "device 2";
            Device device2 = new Device(-1, name2);
            dataSource.devicesChange(device2);
            assertCursorSizeAndClose(dataSource.devicesGet(), 1);
            c = dataSource.devicesGet();
            c.moveToFirst();
            Device d = Device.fromCursor(c);
            assertEquals(name2, d.getName());
            assertNotSame(-1, d.getId());
            dataSource.devicesClear();
        }

        dataSource.devicesChange(device1);
        dataSource.devicesDelete(device1.getId());

        c2 = dataSource.devicesGet();
        assertEquals(0, c2.getCount());
        c2.close();


        dataSource.devicesClear();

        dataSource.devicesChange(device1);
        dataSource.devicesClear();

        c2 = dataSource.devicesGet();
        assertEquals(0, c2.getCount());
        c2.close();
    }

    public void testDeviceTypes() {

        Cursor c;

        c = dataSource.getDeviceTypes();
        assertNotNull(c);
        c.close();

        dataSource.deleteAllDeviceTypes();

        c = dataSource.getDeviceTypes();
        assertEquals(0, c.getCount());
        c.close();

        String title1 = "type1";
        Type t1 = new Type(title1);
        dataSource.addDeviceType(new Type(title1));

        c = dataSource.getDeviceTypes();
        assertEquals(1, c.getCount());
        c.moveToFirst();
        Type t2 = Type.fromCursor(c);
        c.close();

        assertEquals(t1.getName(), t2.getName());

        t2.setName("type1_A");

        dataSource.updateDeviceType(t2);

        c = dataSource.getDeviceTypes();
        assertEquals(1, c.getCount());
        c.moveToFirst();
        Type t3 = Type.fromCursor(c);
        c.close();

        assertNotSame(t1.getName(), t3.getName());

        dataSource.deleteDeviceType(t1.getId());

        c = dataSource.getDeviceTypes();
        assertEquals(0, c.getCount());
        c.close();

    }

    public void testSummary() {

        Cursor c = dataSource.summaryGet();
        assertNotNull(c);
        c.close();

    }

    public void testMeterings() {
        Cursor c;

        c = dataSource.meteringGet();
        assertNotNull(c);
        c.close();

        dataSource.meteringClear();
        assertCursorSizeAndClose(dataSource.meteringGet(), 0);

        Date date1 = new Date();
        double value1 = 100;
        double value2 = 200;
        Metering metering1 = new Metering(-1, date1, value2);

        dataSource.meteringChange(metering1);
        assertCursorContainsAndClose(dataSource.meteringGet(), metering1);

        c = dataSource.meteringGet();
        c.moveToFirst();
        Metering metering2 = Metering.fromCursor(c);
        c.close();
        Metering metering3 = new Metering(metering2.getId(), date1, value2);
        dataSource.meteringChange(metering3);
        assertCursorContainsAndClose(dataSource.meteringGet(), metering3);

        dataSource.meteringClear();
        assertCursorSizeAndClose(dataSource.meteringGet(), 0);

    }

}
