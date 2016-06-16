package me.ilich.mymeteringdevices.ui.devices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import me.ilich.mymeteringdevices.MeteringDevicesApplication;
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

    @BindView(R.id.device_name)
    TextView deviceNameTextView;

    @Override
    protected int getContentView() {
        return R.layout.activity_device_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceId = getIntent().getIntExtra(EXTRA_DEVICE_ID, Device.NOT_SET);
        device = MeteringDevicesApplication.getDataSource().deviceGet(deviceId);
        setTitle(device.getName());
        deviceNameTextView.setText(device.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_device_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case R.id.menu_edit:
                startActivity(DeviceEditActivity.intent(this, deviceId));
                b = true;
                break;
            case R.id.menu_delete:
                DeviceDeleteDialogFragment f = DeviceDeleteDialogFragment.create(device);
                //f.setTargetFragment(, RESULT_CODE_DELETE); //TODO
                f.show(getSupportFragmentManager(), "TAG");
                b = true;
                break;
            default:
                b = super.onOptionsItemSelected(item);
                break;
        }
        return b;
    }
}
