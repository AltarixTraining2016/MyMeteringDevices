package me.ilich.mymeteringdevices.ui.devices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.ui.BackActivity;

public class DeviceEditActivity extends BackActivity {

    private final static String EXTRA_DEVICE_ID = "device_id";
    private final static int NOT_SET = -1;

    public static Intent intent(Context context) {
        return new Intent(context, DeviceEditActivity.class);
    }

    public static Intent intent(Context context, int deviceId) {
        Intent intent = new Intent(context, DeviceEditActivity.class);
        intent.putExtra(EXTRA_DEVICE_ID, deviceId);
        return intent;
    }

    private int deviceId;

    @BindView(R.id.device_name)
    EditText deviceNameEditText;

    @Override
    protected int getContentView() {
        return R.layout.activity_device_edit;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceId = getIntent().getIntExtra(EXTRA_DEVICE_ID, NOT_SET);
        if (deviceId != NOT_SET) {
            Device device = getDataSource().deviceGet(deviceId);
            if (device != null) {
                deviceNameEditText.setText(device.getName());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_device_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case R.id.menu_save:
                String name = deviceNameEditText.getText().toString();
                Device newDevice = new Device(deviceId, name, 1); //TODO device type id
                getDataSource().devicesChange(newDevice);
                finish();
                b = true;
                break;
            default:
                b = super.onOptionsItemSelected(item);
                break;
        }
        return b;
    }
}
