package me.ilich.mymeteringdevices.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.DeviceType;

public class DeviceTypeEditActivity extends AppCompatActivity implements DeviceTypeEditFragment.DeviceTypeEdit {

    private static final String EXTRA_DEVICE_TYPE = "device type";

    public static Intent intent(Context context) {
        return new Intent(context, DeviceTypeEditActivity.class);
    }

    public static Intent intent(Context context, DeviceType deviceType) {
        return new Intent(context, DeviceTypeEditActivity.class).putExtra(EXTRA_DEVICE_TYPE, deviceType);
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_type_edit);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DeviceType deviceType = (DeviceType) getIntent().getSerializableExtra(EXTRA_DEVICE_TYPE);
        if (deviceType == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_content, DeviceTypeEditFragment.create()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_content, DeviceTypeEditFragment.create(deviceType)).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final boolean b;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                b = true;
                break;
            default:
                b = super.onOptionsItemSelected(item);
                break;
        }
        return b;
    }

    @Override
    public void onDeviceTypeEdited(DeviceType deviceType) {
        finish();
    }

}
