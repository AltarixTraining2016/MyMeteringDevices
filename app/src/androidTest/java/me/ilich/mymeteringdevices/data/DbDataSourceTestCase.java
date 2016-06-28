package me.ilich.mymeteringdevices.data;

import android.util.Log;

import java.io.File;

import me.ilich.mymeteringdevices.data.db.DatabaseHelper;
import me.ilich.mymeteringdevices.tools.AssetsReader;

public class DbDataSourceTestCase extends DataSourceTestCase {

    private String fileName;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
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

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        File f = getInstrumentation().getTargetContext().getDatabasePath(fileName);
        if (f.exists()) {
            f.delete();
        }
    }
}
