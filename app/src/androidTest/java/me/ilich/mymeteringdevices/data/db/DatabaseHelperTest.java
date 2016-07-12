package me.ilich.mymeteringdevices.data.db;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class DatabaseHelperTest {

    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() throws Exception {
        DatabaseHelper.init(getInstrumentation().getTargetContext());
        databaseHelper = DatabaseHelper.getInstance();
        deleteDb();
    }

    @After
    public void tearDown() throws Exception {
        deleteDb();
    }

    private void deleteDb() {
        File f = getInstrumentation().getTargetContext().getDatabasePath(databaseHelper.getDatabaseName());
        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    public void db() {
        try {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            assertNotNull(db);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
