package me.ilich.mymeteringdevices.data.dto;

import junit.framework.TestCase;

public class TypeTestCase extends TestCase {

    public void testEquals() {
        Type type1 = new Type(1, "A", 1);
        Type type2 = new Type(1, "A", 1);
        Type type3 = new Type(3, "B", 2);
        assertEquals(type1, type2);
        assertNotSame(type1, type3);
    }

}
