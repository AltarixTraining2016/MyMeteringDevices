package me.ilich.mymeteringdevices.ui.devices;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.ui.Titleable;

public class AllMeteringFragment extends Fragment implements Titleable {

    public static AllMeteringFragment create() {
        return new AllMeteringFragment();
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
        return context.getString(R.string.title_all_metering);
    }

}
