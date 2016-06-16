package me.ilich.mymeteringdevices.ui.devices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.ui.BackActivity;

public class DeviceViewActivity extends BackActivity {

    private final static String EXTRA_DEVICE_ID = "device_id";

    public static Intent intent(Context context, int deviceId) {
        Intent intent = new Intent(context, DeviceViewActivity.class);
        intent.putExtra(EXTRA_DEVICE_ID, deviceId);
        return intent;
    }

    private int deviceId;
    private Device device;

    @Override
    protected int getContentView() {
        return R.layout.activity_device_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceId = getIntent().getIntExtra(EXTRA_DEVICE_ID, Device.NOT_SET);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_content, DeviceViewFragment.create(deviceId)).commit();
        }
        device = getDataSource().deviceGet(deviceId);
        setTitle(device.getName());
    }

}
