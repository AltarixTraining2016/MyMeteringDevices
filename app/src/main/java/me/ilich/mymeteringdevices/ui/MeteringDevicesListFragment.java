package me.ilich.mymeteringdevices.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.R;

public class MeteringDevicesListFragment extends Fragment implements Titleable {

    public static MeteringDevicesListFragment create() {
        return new MeteringDevicesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_under_construction, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_metering_devices_list);
    }

}
