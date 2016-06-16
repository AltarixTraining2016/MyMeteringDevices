package me.ilich.mymeteringdevices.ui.types;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Type;
import me.ilich.mymeteringdevices.ui.BackActivity;

public class TypeEditActivity extends BackActivity implements TypeEditFragment.DeviceTypeEdit {

    private static final String EXTRA_DEVICE_TYPE = "device type";

    public static Intent intent(Context context) {
        return new Intent(context, TypeEditActivity.class);
    }

    public static Intent intent(Context context, Type deviceType) {
        return new Intent(context, TypeEditActivity.class).putExtra(EXTRA_DEVICE_TYPE, deviceType);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_type_edit;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Type deviceType = (Type) getIntent().getSerializableExtra(EXTRA_DEVICE_TYPE);
        if (deviceType == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_content, TypeEditFragment.create()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_content, TypeEditFragment.create(deviceType)).commit();
        }
    }

    @Override
    public void onDeviceTypeEdited(Type deviceType) {
        finish();
    }

}
