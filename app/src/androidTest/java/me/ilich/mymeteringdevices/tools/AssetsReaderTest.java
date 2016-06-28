package me.ilich.mymeteringdevices.tools;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import java.util.ArrayList;
import java.util.List;

public class AssetsReaderTest extends InstrumentationTestCase {

    public void testRead() {
        final List<String> l = new ArrayList<>();
        l.add("string1");
        l.add("string2");
        final int[] i = {0};
        AssetsReader.byLine(getInstrumentation().getContext(), "file1.txt", new AssetsReader.OnReadListener() {
            @Override
            public void onLine(String s) {
                assertTrue(l.size() > i[0]);
                assertEquals(l.get(i[0]), s);
                i[0]++;
            }
        });
        assertEquals(2, i[0]);
    }

}
