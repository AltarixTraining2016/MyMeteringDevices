package me.ilich.mymeteringdevices.ui.types;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Type;
import me.ilich.mymeteringdevices.tools.CursorRecyclerViewAdapter;
import me.ilich.mymeteringdevices.ui.MeteringFragment;
import me.ilich.mymeteringdevices.ui.Titleable;

public class TypesListFragment extends MeteringFragment implements Titleable {

    public static TypesListFragment create() {
        return new TypesListFragment();
    }

    @BindView(R.id.types_list)
    RecyclerView deviceTypesRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_types_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Adapter adapter = new Adapter(getContext(), getDataSource().typesGetAll());
        deviceTypesRecyclerView.setAdapter(adapter);
        deviceTypesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @OnClick(R.id.type_add)
    void addDeviceType() {
        startActivity(TypeEditActivity.intent(getContext()));
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_device_types);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.type_name)
        TextView nameTextView;

        @BindView(R.id.type_context)
        ImageView contextMenuImageView;

        public ViewHolder(ViewGroup root) {
            super(LayoutInflater.from(getContext()).inflate(R.layout.listitem_device_type, root, false));
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Type deviceType) {
            nameTextView.setText(deviceType.getName());
        }

        @OnClick(R.id.type_root)
        void onItemClick() {
            startActivity(TypeEditActivity.intent(getContext(), null)); //TODO
        }

        @OnClick(R.id.type_context)
        void onContextMenuClick(View v) {
            PopupMenu popup = new PopupMenu(getContext(), v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_context_type, popup.getMenu());
            popup.show();
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
            Type deviceType = Type.fromCursor(cursor);
            viewHolder.bind(deviceType);
        }

    }

}
