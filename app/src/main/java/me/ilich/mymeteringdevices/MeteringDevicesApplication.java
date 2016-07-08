package me.ilich.mymeteringdevices;

import android.app.Application;

import me.ilich.mymeteringdevices.data.DataSource;
import me.ilich.mymeteringdevices.data.DbDataSource;
import me.ilich.mymeteringdevices.data.MemoryDataSource;

public class MeteringDevicesApplication extends Application {

    private static DataSource dataSource;

    public static DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        switch (BuildConfig.DATA_SOURCE) {
            case DB:
                dataSource = new DbDataSource(getApplicationContext());
                break;
            case MEMORY:
                dataSource = new MemoryDataSource();
                break;
        }
    }

    public enum DataSourceType {
        DB,
        MEMORY
    }

}
