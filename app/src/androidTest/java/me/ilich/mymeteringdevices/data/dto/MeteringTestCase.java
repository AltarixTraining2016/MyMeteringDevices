package me.ilich.mymeteringdevices.data.dto;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MeteringTestCase{

    @Test
    public void equals() {
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
