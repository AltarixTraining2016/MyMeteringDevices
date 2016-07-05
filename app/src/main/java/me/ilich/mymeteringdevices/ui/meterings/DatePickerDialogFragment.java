package me.ilich.mymeteringdevices.ui.meterings;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.DatePicker;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

import me.ilich.mymeteringdevices.ui.MeteringDialogFragment;

public class DatePickerDialogFragment extends MeteringDialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String ARG_DATETIME = "datetime";
    public static final String EXTRA_DATETIME = "datetime";

    public static DatePickerDialogFragment create() {
        return new DatePickerDialogFragment();
    }

    public static DatePickerDialogFragment create(@NotNull Date dateTime) {
        DatePickerDialogFragment f = new DatePickerDialogFragment();
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
        return new DatePickerDialog(getContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATETIME, calendar.getTime());
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}
