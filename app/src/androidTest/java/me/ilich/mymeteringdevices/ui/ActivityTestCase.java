package me.ilich.mymeteringdevices.ui;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;

public class ActivityTestCase extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public ActivityTestCase() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

}
