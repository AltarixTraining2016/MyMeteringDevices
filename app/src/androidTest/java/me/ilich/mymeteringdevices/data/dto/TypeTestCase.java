package me.ilich.mymeteringdevices.data.dto;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TypeTestCase {

    @Test
    public void equals() {
        Type type1 = new Type(1, "A", 1);
        Type type2 = new Type(1, "A", 1);
        Type type3 = new Type(3, "B", 2);
        assertEquals(type1, type2);
        assertNotSame(type1, type3);
    }

}
