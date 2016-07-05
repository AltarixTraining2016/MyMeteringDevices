package me.ilich.mymeteringdevices.ui.meterings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Metering;
import me.ilich.mymeteringdevices.ui.MeteringFragment;
import me.ilich.mymeteringdevices.ui.Titleable;

public class EditMeteringFragment extends MeteringFragment implements Titleable {

    private static final String EXTRA_METERING = "metering";
    private static final String STATE_DATETIME = "datetime";
    private static final String TAG_DIALOG = "dialog";

    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final int REQUEST_CODE_PICK_DATE = 1;
    private static final int REQUEST_CODE_PICK_TIME = 2;

    public static EditMeteringFragment create() {
        return new EditMeteringFragment();
    }

    public static EditMeteringFragment create(Metering metering) {
        EditMeteringFragment f = new EditMeteringFragment();
        Bundle b = new Bundle();
        b.putSerializable(EXTRA_METERING, metering);
        f.setArguments(b);
        return f;
    }

    @BindView(R.id.change_date)
    Button dateButton;

    @BindView(R.id.change_time)
    Button timeButton;

    @BindView(R.id.device)
    Spinner deviceSpinner;

    @BindView(R.id.measure)
    EditText measureEditText;

    @Nullable
    private Date meteringDateTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState == null) {
            if (getArguments() != null) {
                Metering metering = (Metering) getArguments().getSerializable(EXTRA_METERING);
                if (metering != null) {
                    meteringDateTime = metering.getDate();
                } else {
                    meteringDateTime = new Date();
                }
            } else {
                meteringDateTime = new Date();
            }
        } else {
            meteringDateTime = (Date) savedInstanceState.getSerializable(STATE_DATETIME);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_DATETIME, meteringDateTime);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_metering_edit, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        deviceSpinner.setAdapter(new Adapter(getDataSource().devicesGetAll()));
        processDateTimeTitle();
    }

    private void processDateTimeTitle() {
        if (meteringDateTime == null) {
            dateButton.setText(R.string.metering_edit_date);
            timeButton.setText(R.string.metering_edit_time);
        } else {
            dateButton.setText(SDF_DATE.format(meteringDateTime));
            timeButton.setText(SDF_TIME.format(meteringDateTime));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            final Metering newMetering;
            //newMetering = new Metering();
            //getDataSource().meteringChange(newMetering);
            ((Listener) getActivity()).onSaved();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_enter_metering);
    }

    @OnClick(R.id.change_date)
    void onDateClick() {
        DatePickerDialogFragment f;
        if (meteringDateTime == null) {
            f = DatePickerDialogFragment.create();
        } else {
            f = DatePickerDialogFragment.create(meteringDateTime);
        }
        f.setTargetFragment(this, REQUEST_CODE_PICK_DATE);
        f.show(getFragmentManager(), TAG_DIALOG);
    }

    @OnClick(R.id.change_time)
    void onTimeClick() {
        TimePickerDialogFragment f;
        if (meteringDateTime == null) {
            f = TimePickerDialogFragment.create();
        } else {
            f = TimePickerDialogFragment.create(meteringDateTime);
        }
        f.setTargetFragment(this, REQUEST_CODE_PICK_TIME);
        f.show(getFragmentManager(), TAG_DIALOG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_DATE) {
            if (resultCode == Activity.RESULT_OK) {
                Date date = (Date) data.getSerializableExtra(DatePickerDialogFragment.EXTRA_DATETIME);
                Calendar newDate = Calendar.getInstance();
                newDate.setTime(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(meteringDateTime);
                calendar.set(Calendar.YEAR, newDate.get(Calendar.YEAR));
                calendar.set(Calendar.MONTH, newDate.get(Calendar.MONTH));
                calendar.set(Calendar.DAY_OF_MONTH, newDate.get(Calendar.DAY_OF_MONTH));
                meteringDateTime = calendar.getTime();
                processDateTimeTitle();
            }
        } else if (requestCode == REQUEST_CODE_PICK_TIME) {
            if (resultCode == Activity.RESULT_OK) {
                Date time = (Date) data.getSerializableExtra(DatePickerDialogFragment.EXTRA_DATETIME);
                Calendar newTime = Calendar.getInstance();
                newTime.setTime(time);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(meteringDateTime);
                calendar.set(Calendar.HOUR_OF_DAY, newTime.get(Calendar.HOUR_OF_DAY));
                calendar.set(Calendar.MINUTE, newTime.get(Calendar.MINUTE));
                meteringDateTime = calendar.getTime();
                processDateTimeTitle();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private class Adapter extends CursorAdapter {

        public Adapter(Cursor c) {
            super(getContext(), c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

        }

    }

    public interface Listener {

        void onSaved();

    }

}
