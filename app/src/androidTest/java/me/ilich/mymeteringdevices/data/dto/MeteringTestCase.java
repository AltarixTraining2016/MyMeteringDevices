package me.ilich.mymeteringdevices.data.dto;

import junit.framework.TestCase;

import java.util.Date;

public class MeteringTestCase extends TestCase {

    public void testEquals() {
        Date date1 = new Date();
        Date date2 = new Date(1);

        Metering metering1 = new Metering(1, date1, 123, 1);
        Metering metering2 = new Metering(1, date1, 123, 1);
        Metering metering3 = new Metering(2, date2, 999, 2);

        assertEquals(metering1, metering1);
        assertEquals(metering1, metering2);
        assertNotSame(metering1, metering3);
    }

}
