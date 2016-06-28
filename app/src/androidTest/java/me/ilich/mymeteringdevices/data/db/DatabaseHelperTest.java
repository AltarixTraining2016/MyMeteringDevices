package me.ilich.mymeteringdevices.data.db;

import android.test.AndroidTestCase;

import java.io.File;

public class DatabaseHelperTest extends AndroidTestCase {

    private DatabaseHelper databaseHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DatabaseHelper.init(getContext());
        databaseHelper = DatabaseHelper.getInstance();
        deleteDb();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        deleteDb();
    }

    private void deleteDb() {
        File f = getContext().getDatabasePath(databaseHelper.getDatabaseName());
        if (f.exists()) {
            f.delete();
        }
    }

    public void testDb() {
        try {
            assertNotNull(databaseHelper.getWritableDatabase());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
