package me.ilich.mymeteringdevices.data.dto;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class DeviceTestCase {

    @Test
    public void equals() {
        Device device1 = new Device(1, "dev1", 1);
        Device device2 = new Device(1, "dev1", 1);
        Device device3 = new Device(2, "dev2", 2);
        assertEquals(device1, device2);
        assertNotSame(device1, device3);
    }

}
