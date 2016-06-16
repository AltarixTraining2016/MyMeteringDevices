package me.ilich.mymeteringdevices.ui.devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.ui.MeteringFragment;

public class DeviceViewFragment extends MeteringFragment {

    private final static String EXTRA_DEVICE_ID = "device_id";
    private static final int REQUEST_CODE_DELETE = 12;

    public static DeviceViewFragment create(int decideId) {
        DeviceViewFragment f = new DeviceViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_DEVICE_ID, decideId);
        f.setArguments(bundle);
        return f;
    }

    private Device device;

    @BindView(R.id.device_name)
    TextView deviceNameTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        int deviceId = getArguments().getInt(EXTRA_DEVICE_ID, Device.NOT_SET);
        device = getDataSource().deviceGet(deviceId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_device_view, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        deviceNameTextView.setText(device.getName());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_device_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case R.id.menu_edit:
                startActivity(DeviceEditActivity.intent(getContext(), device.getId()));
                b = true;
                break;
            case R.id.menu_delete:
                DeviceDeleteDialogFragment f = DeviceDeleteDialogFragment.create(device);
                f.setTargetFragment(this, REQUEST_CODE_DELETE);
                f.show(getFragmentManager(), "TAG");
                b = true;
                break;
            default:
                b = super.onOptionsItemSelected(item);
                break;
        }
        return b;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_DELETE:
                getActivity().finish(); //TODO
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

}
