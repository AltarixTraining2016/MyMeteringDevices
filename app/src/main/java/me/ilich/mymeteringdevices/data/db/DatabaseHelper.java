package me.ilich.mymeteringdevices.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.ilich.mymeteringdevices.BuildConfig;
import me.ilich.mymeteringdevices.tools.AssetsReader;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "metering.sqlite";
    private static final int VERSION = 1;

    private static DatabaseHelper instance = null;

    public static void init(Context context) {
        instance = new DatabaseHelper(context);
    }

    public static DatabaseHelper getInstance() {
        return instance;
    }

    private final Context context;

    private DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        AssetsReader.OnReadListener listener = new AssetsReader.OnReadListener() {
            @Override
            public void onLine(String line) {
                db.execSQL(line);
            }
        };
        AssetsReader.byLine(context, "db_create_v1.sql", listener);
        if (BuildConfig.DEBUG) {
            AssetsReader.byLine(context, "db_fill_debug.sql", listener);
        } else {
            AssetsReader.byLine(context, "db_fill_prod.sql", listener);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
