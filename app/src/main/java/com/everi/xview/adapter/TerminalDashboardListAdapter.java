package com.everi.xview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.everi.xview.R;
import com.everi.xview.models.TerminalDashboardModel;
import com.everi.xview.models.TerminalModel;

import java.util.ArrayList;
import java.util.List;


public class TerminalDashboardListAdapter extends RecyclerView.Adapter<TerminalDashboardListAdapter.ViewHolder> {

    private final List<TerminalDashboardModel> mValues;
    private final OnListItemClickListener mListener;
    private Context context;


    public interface OnListItemClickListener{
        void onListItemClick(View view, int position, Object selectedItem);
    }

    public TerminalDashboardListAdapter(Context context, List<TerminalDashboardModel> items, OnListItemClickListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_terminal_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvActivityTitle.setText(mValues.get(position).getActivityTitle());
        holder.tvActivityCount.setText(mValues.get(position).getActivityCount());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    mListener.onListItemClick(v,position,mValues.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView tvActivityTitle;
        public final TextView tvActivityCount;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvActivityTitle = (TextView) view.findViewById(R.id.tv_activity_title);
            tvActivityCount = (TextView) view.findViewById(R.id.tv_activity_count);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}
