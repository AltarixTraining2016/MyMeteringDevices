package me.ilich.mymeteringdevices.ui.devices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class DeviceEditActivity extends AppCompatActivity {

    private final static String EXTRA_DEVICE_ID = "device_id";

    public static Intent intent(Context context) {
        return new Intent(context, DeviceEditActivity.class);
    }

    public static Intent intent(Context context, int deviceId) {
        Intent intent = new Intent(context, DeviceEditActivity.class);
        intent.putExtra(EXTRA_DEVICE_ID, deviceId);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int deviceId = getIntent().getIntExtra(EXTRA_DEVICE_ID, -1);
    }

}
