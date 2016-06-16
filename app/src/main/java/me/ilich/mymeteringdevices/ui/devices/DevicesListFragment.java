package me.ilich.mymeteringdevices.ui.devices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ilich.mymeteringdevices.MeteringDevicesApplication;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Device;
import me.ilich.mymeteringdevices.tools.CursorRecyclerViewAdapter;
import me.ilich.mymeteringdevices.ui.Titleable;

public class DevicesListFragment extends Fragment implements Titleable {

    private static final int RESULT_CODE_DELETE = 1;

    public static DevicesListFragment create() {
        return new DevicesListFragment();
    }

    @BindView(R.id.devices_list)
    RecyclerView recyclerView;

    Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new Adapter(getContext(), MeteringDevicesApplication.getDataSource().devicesGet());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_devices_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_metering_devices_list);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_CODE_DELETE:
                if (resultCode == Activity.RESULT_OK) {
                    adapter.swapCursor(MeteringDevicesApplication.getDataSource().devicesGet());
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @OnClick(R.id.device_add)
    void onAddDeviceClick() {
        startActivity(DeviceEditActivity.intent(getContext()));
    }

    class ViewHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {

        @BindView(R.id.name)
        TextView nameTextView;

        @BindView(R.id.more)
        ImageView moreImageView;

        Device device;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(getContext()).inflate(R.layout.listitem_device, parent, false));
            ButterKnife.bind(this, itemView);
        }

        public void fill(Device device) {
            this.device = device;
            process();
        }

        private void process() {
            nameTextView.setText(device.getName());
            moreImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(getContext(), v);
                    popup.setOnMenuItemClickListener(ViewHolder.this);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.menu_context_device, popup.getMenu());
                    popup.show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(DeviceViewActivity.intent(getContext(), device.getId()));
                }
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            final boolean r;
            switch (item.getItemId()) {
                case R.id.menu_view:
                    startActivity(DeviceViewActivity.intent(getContext(), device.getId()));
                    r = true;
                    break;
                case R.id.menu_edit:
                    startActivity(DeviceEditActivity.intent(getContext(), device.getId()));
                    r = true;
                    break;
                case R.id.menu_delete:
                    DeviceDeleteDialogFragment f = DeviceDeleteDialogFragment.create(device);
                    f.setTargetFragment(DevicesListFragment.this, RESULT_CODE_DELETE);
                    f.show(getFragmentManager(), "TAG");
                    r = true;
                    break;
                default:
                    r = false;
                    break;
            }
            return r;
        }

    }

    class Adapter extends CursorRecyclerViewAdapter<ViewHolder> {

        public Adapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
            Device device = Device.fromCursor(cursor);
            viewHolder.fill(device);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

    }

}
