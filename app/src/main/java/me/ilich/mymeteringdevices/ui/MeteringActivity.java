package me.ilich.mymeteringdevices.ui;

import android.support.v7.app.AppCompatActivity;

import me.ilich.mymeteringdevices.MeteringDevicesApplication;
import me.ilich.mymeteringdevices.data.DataSource;

public class MeteringActivity extends AppCompatActivity {

    protected DataSource getDataSource() {
        return MeteringDevicesApplication.getDataSource();
    }

}
