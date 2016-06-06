package me.ilich.mymeteringdevices.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.R;

public class SummaryFragment extends Fragment {

    public static SummaryFragment create(){
        return new SummaryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_summary, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

}
