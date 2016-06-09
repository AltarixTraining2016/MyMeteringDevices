package me.ilich.mymeteringdevices.data;

import junit.framework.TestCase;

import me.ilich.mymeteringdevices.data.dto.DeviceType;

public class DeviceTypeTestCase extends TestCase {

    public void testEquals() {
        DeviceType type1 = new DeviceType(1, "A");
        DeviceType type2 = new DeviceType(1, "A");
        DeviceType type3 = new DeviceType(3, "B");
        assertEquals(type1, type2);
        assertNotSame(type1, type3);
    }

}
