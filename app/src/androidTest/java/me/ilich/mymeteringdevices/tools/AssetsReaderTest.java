package me.ilich.mymeteringdevices.tools;

import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AssetsReaderTest {

    @Test
    public void read() {
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
