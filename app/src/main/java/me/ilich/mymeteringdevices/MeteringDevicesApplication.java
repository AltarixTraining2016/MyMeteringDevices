package me.ilich.mymeteringdevices;

import android.app.Application;

import me.ilich.mymeteringdevices.data.DataSource;
import me.ilich.mymeteringdevices.data.MemoryDataSource;

public class MeteringDevicesApplication extends Application {

    private static DataSource dataSource;

    public static DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataSource = new MemoryDataSource();
    }

}
