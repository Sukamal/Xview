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
import com.everi.xview.models.TerminalModel;

import java.util.ArrayList;
import java.util.List;


public class TerminalListAdapter extends RecyclerView.Adapter<TerminalListAdapter.ViewHolder> implements Filterable{

    private final List<TerminalModel> mValues;
    private final List<TerminalModel> mFilterValues;
    private final OnListItemClickListener mListener;
    private Context context;
    private TerminalFilter terminalFilter;


    public interface OnListItemClickListener{
        void onListItemClick(View view, int position, Object selectedItem);
    }

    public TerminalListAdapter(Context context, List<TerminalModel> items, OnListItemClickListener listener) {
        this.context = context;
        mValues = items;
        mFilterValues = new ArrayList<>();
        mFilterValues.addAll(items);
        mListener = listener;
        terminalFilter = new TerminalFilter(TerminalListAdapter.this);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_terminal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mFilterValues.get(position);
        holder.tv_terminal_id.setText(mFilterValues.get(position).terminalId);
        holder.tv_terminal_merchant.setText(mFilterValues.get(position).merchant);
        holder.tv_terminal_date.setText(mFilterValues.get(position).dateTime);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    mListener.onListItemClick(v,position,mFilterValues.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return mFilterValues.size();
    }

    @Override
    public Filter getFilter() {
        return terminalFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tv_terminal_id;
        public final TextView tv_terminal_merchant;
        public final TextView tv_terminal_date;

        public TerminalModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_terminal_id = (TextView) view.findViewById(R.id.tv_terminal_id);
            tv_terminal_merchant = (TextView) view.findViewById(R.id.tv_terminal_merchant);
            tv_terminal_date = (TextView) view.findViewById(R.id.tv_terminal_date);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public class TerminalFilter extends Filter{

        private TerminalListAdapter terminalListAdapter;
        private TerminalFilter(TerminalListAdapter terminalListAdapter){
            super();
            this.terminalListAdapter = terminalListAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            mFilterValues.clear();
            final FilterResults results = new FilterResults();
            if(constraint.length() == 0){
                mFilterValues.addAll(mValues);
            }else{
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for(TerminalModel terminalModel : mValues){

                    if(terminalModel.terminalId.toLowerCase().contains(filterPattern)){
                        mFilterValues.add(terminalModel);
                    }
                }
            }

            results.values = mFilterValues;
            results.count = mFilterValues.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            this.terminalListAdapter.notifyDataSetChanged();
        }
    }
}
