package me.ilich.mymeteringdevices.data;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.File;

import me.ilich.mymeteringdevices.data.db.DatabaseHelper;
import me.ilich.mymeteringdevices.tools.AssetsReader;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DbDataSourceTestCase extends DataSourceTestCase {

    private String fileName;

    @Override
    @Before
    public void before() throws Exception {
        super.before();

        AssetsReader.byLine(getInstrumentation().getContext(), "db_fill_debug.sql", new AssetsReader.OnReadListener() {
            @Override
            public void onLine(String s) {
                DatabaseHelper.getInstance().getWritableDatabase().execSQL(s);
            }
        });
    }

    @Override
    protected DataSource createDataSource() {
        DbDataSource dbDataSource = new DbDataSource(getInstrumentation().getTargetContext());
        fileName = dbDataSource.getDbFileName();
        return dbDataSource;
    }

    @After
    public void after() throws Exception {
        File f = getInstrumentation().getTargetContext().getDatabasePath(fileName);
        if (f.exists()) {
            f.delete();
        }
    }

}
