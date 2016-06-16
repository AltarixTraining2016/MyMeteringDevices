package me.ilich.mymeteringdevices.ui.types;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.MeteringDevicesApplication;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Type;

public class TypeEditFragment extends Fragment {

    private static final String ARG_DEVICE_TYPE = "device type";

    public static TypeEditFragment create() {
        return new TypeEditFragment();
    }

    public static TypeEditFragment create(Type deviceType) {
        TypeEditFragment fragment = new TypeEditFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DEVICE_TYPE, deviceType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    Type deviceType;

    @BindView(R.id.device_type_title_wrapper)
    TextInputLayout deviceTypeTitleInputLayout;

    @BindView(R.id.device_type_title)
    TextInputEditText deviceTypeTitleEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            deviceType = (Type) getArguments().getSerializable(ARG_DEVICE_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_type_edit, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (deviceType != null) {
            deviceTypeTitleEditText.setText(deviceType.getName());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case R.id.menu_save:
                b = true;
                String name = deviceTypeTitleEditText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    deviceTypeTitleInputLayout.setError(getString(R.string.error_field_required));
                } else {
                    final Type newDeviceType;
                    if (deviceType == null) {
                        newDeviceType = new Type(name);
                        MeteringDevicesApplication.getDataSource().addDeviceType(newDeviceType);
                    } else {
                        newDeviceType = new Type(deviceType.getId(), name);
                        MeteringDevicesApplication.getDataSource().updateDeviceType(newDeviceType);
                    }
                    ((DeviceTypeEdit) getActivity()).onDeviceTypeEdited(newDeviceType);
                }
                break;
            default:
                b = super.onOptionsItemSelected(item);
                break;
        }
        return b;
    }

    public interface DeviceTypeEdit {

        void onDeviceTypeEdited(Type deviceType);

    }

}
