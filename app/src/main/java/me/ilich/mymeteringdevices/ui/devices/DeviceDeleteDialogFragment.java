package me.ilich.mymeteringdevices.ui.devices;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import me.ilich.mymeteringdevices.MeteringDevicesApplication;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Device;

public class DeviceDeleteDialogFragment extends AppCompatDialogFragment {

    private static final String ARG_DEVICE = "device";

    public static DeviceDeleteDialogFragment create(Device device) {
        DeviceDeleteDialogFragment f = new DeviceDeleteDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DEVICE, device);
        f.setArguments(bundle);
        return f;
    }

    private Device device;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device = (Device) getArguments().getSerializable(ARG_DEVICE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.
                Builder(getActivity()).
                setTitle(R.string.dialog_title_delete_device).
                setMessage(String.format(getString(R.string.delete_device), device.getName())).
                setPositiveButton(R.string.dialog_button_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MeteringDevicesApplication.getDataSource().devicesDelete(device.getId());
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                    }
                }).
                setNegativeButton(R.string.dialog_button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                    }
                }).
                create();
    }

}
