package me.ilich.mymeteringdevices.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ilich.mymeteringdevices.MeteringDevicesApplication;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.DeviceType;
import me.ilich.mymeteringdevices.tools.CursorRecyclerViewAdapter;

public class DeviceTypesListFragment extends Fragment implements Titleable {

    public static DeviceTypesListFragment create() {
        return new DeviceTypesListFragment();
    }

    @BindView(R.id.device_types)
    RecyclerView deviceTypesRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_devide_types_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Adapter adapter = new Adapter(getContext(), MeteringDevicesApplication.getDataSource().getDeviceTypes());
        deviceTypesRecyclerView.setAdapter(adapter);
        deviceTypesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @OnClick(R.id.add_device_type)
    void addDeviceType(View view) {
        startActivity(DeviceTypeEditActivity.intent(getContext()));
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_device_types);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView nameTextView;

        @BindView(R.id.show_context_menu)
        ImageView contextMenuImageView;

        public ViewHolder(ViewGroup root) {
            super(LayoutInflater.from(getContext()).inflate(R.layout.listitem_device_type, root, false));
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DeviceType deviceType) {
            nameTextView.setText(deviceType.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(DeviceTypeEditActivity.intent(getContext(), deviceType));
                }
            });
            contextMenuImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(getContext(), v);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.menu_context_device_type, popup.getMenu());
                    popup.show();
                }
            });
        }

    }

    class Adapter extends CursorRecyclerViewAdapter<ViewHolder> {

        public Adapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
            DeviceType deviceType = DeviceType.fromCursor(cursor);
            viewHolder.bind(deviceType);
        }

    }

}
