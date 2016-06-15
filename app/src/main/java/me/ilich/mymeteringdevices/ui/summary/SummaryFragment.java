package me.ilich.mymeteringdevices.ui.summary;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.ilich.mymeteringdevices.MeteringDevicesApplication;
import me.ilich.mymeteringdevices.R;
import me.ilich.mymeteringdevices.data.dto.Summary;
import me.ilich.mymeteringdevices.tools.CursorRecyclerViewAdapter;
import me.ilich.mymeteringdevices.ui.Titleable;

public class SummaryFragment extends Fragment implements Titleable {

    public static SummaryFragment create() {
        return new SummaryFragment();
    }

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_summary, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Cursor c = MeteringDevicesApplication.getDataSource().summaryGet();
        Adapter adapter = new Adapter(getContext(), c);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_summary);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(ViewGroup root) {
            super(LayoutInflater.from(getContext()).inflate(R.layout.listitem_summary, root, false));
            ButterKnife.bind(this, itemView);
        }

        public void fill(Summary summary) {
            //TODO
        }

    }

    class Adapter extends CursorRecyclerViewAdapter<ViewHolder> {

        public Adapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
            Summary summary = Summary.fromCursor(cursor);
            viewHolder.fill(summary);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

    }

}
