package me.ilich.mymeteringdevices.data.dto;

import junit.framework.TestCase;

import me.ilich.mymeteringdevices.data.dto.Type;

public class TypeTestCase extends TestCase {

    public void testEquals() {
        Type type1 = new Type(1, "A");
        Type type2 = new Type(1, "A");
        Type type3 = new Type(3, "B");
        assertEquals(type1, type2);
        assertNotSame(type1, type3);
    }

}
