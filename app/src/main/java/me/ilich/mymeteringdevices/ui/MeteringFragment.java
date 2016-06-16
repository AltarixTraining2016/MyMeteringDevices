package me.ilich.mymeteringdevices.ui;

import android.support.v4.app.Fragment;

import me.ilich.mymeteringdevices.MeteringDevicesApplication;
import me.ilich.mymeteringdevices.data.DataSource;

public class MeteringFragment extends Fragment {

    protected DataSource getDataSource() {
        return MeteringDevicesApplication.getDataSource();
    }

}
