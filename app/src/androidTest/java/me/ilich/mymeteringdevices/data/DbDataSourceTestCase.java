package me.ilich.mymeteringdevices.data;

import android.util.Log;

import java.io.File;

public class DbDataSourceTestCase extends DataSourceTestCase {

    private String fileName;

    @Override
    protected DataSource createDataSource() {
        DbDataSource dbDataSource = new DbDataSource(getContext());
        fileName = dbDataSource.getDbFileName();
        Log.v("Sokolov", fileName);
        return dbDataSource;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        File f = getContext().getDatabasePath(fileName);
        Log.v("Sokolov", f.getAbsolutePath());
        if (f.exists()) {
            Log.v("Sokolov", "A");
            boolean b = f.delete();
            Log.v("Sokolov", b + "");
        } else {
            Log.v("Sokolov", "B");
        }
    }
}
