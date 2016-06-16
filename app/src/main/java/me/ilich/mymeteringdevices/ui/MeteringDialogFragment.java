package me.ilich.mymeteringdevices.ui;

import android.support.v7.app.AppCompatDialogFragment;

import me.ilich.mymeteringdevices.MeteringDevicesApplication;
import me.ilich.mymeteringdevices.data.DataSource;

public class MeteringDialogFragment extends AppCompatDialogFragment {

    protected DataSource getDataSource() {
        return MeteringDevicesApplication.getDataSource();
    }

}
