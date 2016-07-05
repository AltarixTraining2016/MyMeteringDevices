package me.ilich.mymeteringdevices.ui.meterings;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TimePicker;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

import me.ilich.mymeteringdevices.ui.MeteringDialogFragment;

public class TimePickerDialogFragment extends MeteringDialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static final String ARG_DATETIME = "datetime";
    public static final String EXTRA_DATETIME = "datetime";

    public static TimePickerDialogFragment create() {
        return new TimePickerDialogFragment();
    }

    public static TimePickerDialogFragment create(@NotNull Date dateTime) {
        TimePickerDialogFragment f = new TimePickerDialogFragment();
        Bundle b = new Bundle();
        b.putSerializable(ARG_DATETIME, dateTime);
        f.setArguments(b);
        return f;
    }

    private Date dateTime = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dateTime = (Date) getArguments().getSerializable(ARG_DATETIME);
        } else {
            dateTime = new Date();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        return new TimePickerDialog(getContext(), this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATETIME, calendar.getTime());
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}
