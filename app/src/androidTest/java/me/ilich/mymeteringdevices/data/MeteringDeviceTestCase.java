package me.ilich.mymeteringdevices.data;

import junit.framework.TestCase;

import me.ilich.mymeteringdevices.data.dto.MeteringDevice;

public class MeteringDeviceTestCase extends TestCase {

    public void testEquals() {
        MeteringDevice device1 = new MeteringDevice(1, "dev1");
        MeteringDevice device2 = new MeteringDevice(1, "dev1");
        MeteringDevice device3 = new MeteringDevice(2, "dev2");
        assertEquals(device1, device2);
        assertNotSame(device1, device3);
    }

}
