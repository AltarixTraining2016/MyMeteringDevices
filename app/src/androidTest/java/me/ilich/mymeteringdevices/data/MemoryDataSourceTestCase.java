package me.ilich.mymeteringdevices.data;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MemoryDataSourceTestCase extends DataSourceTestCase {

    @Before
    @Override
    public void before() throws Exception {
        super.before();
    }

    @Override
    protected DataSource createDataSource() {
        return new MemoryDataSource();
    }

}
