package me.ilich.mymeteringdevices;

import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ExampleTest {

    @BeforeClass
    public static void beforeClass() {
        Log.v("Sokolov", "@BeforeClass");
    }

    @AfterClass
    public static void afterClass() {
        Log.v("Sokolov", "@AfterClass");
    }

    @Before
    public void before() {
        Log.v("Sokolov", "@Before");
    }

    @After
    public void after() {
        Log.v("Sokolov", "@After");
    }

    @Test
    public void testA(){
        Log.v("Sokolov", "test a");
    }

    @Test
    public void testB(){
        Log.v("Sokolov", "test b");
    }

}
