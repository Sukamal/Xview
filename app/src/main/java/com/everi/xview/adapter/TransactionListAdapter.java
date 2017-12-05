package com.everi.xview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.everi.xview.R;
import com.everi.xview.models.TransactionDetail;

import java.util.List;


public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder> {


    public enum VIEW_TYPE {
        TYPE_TRANSACTION(1),
        TYPE_TERMINAL_TRANSACTION(2);

        private int value;
        VIEW_TYPE(int value){
            this.value = value;
        }

        public int getEnumValue(){return value;}

    }

    private final List<TransactionDetail> mValues;
    private final OnListItemClickListener mListener;
    private Context context;
    private int viewType;


    public interface OnListItemClickListener{
        void onListItemClick(View view, int position, Object selectedItem);
    }

    public TransactionListAdapter(Context context, List<TransactionDetail> items, int type, OnListItemClickListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
        viewType = type;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.tv_transaction_id.setText(mValues.get(position).getTerminalID());
        holder.tv_transaction_datetime.setText(mValues.get(position).getTransactionDtTm());
        holder.tv_transaction_amount.setText(mValues.get(position).getReceivedTotalAmount());
        holder.tv_transaction_type.setText(mValues.get(position).getTransactionType());
        holder.tv_transaction_status.setText(mValues.get(position).getResponse());

        if(mValues.get(position).getResponse().equalsIgnoreCase("Approved")){
            holder.tv_transaction_status.setTextColor(context.getResources().getColor(R.color.color_dark_green));
        }else if(mValues.get(position).getResponse().equalsIgnoreCase("Declined")){
            holder.tv_transaction_status.setTextColor(context.getResources().getColor(R.color.color_red));
        }else {
            holder.tv_transaction_status.setTextColor(context.getResources().getColor(android.R.color.black));
        }

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
        public final TextView tv_transaction_id;
        public final TextView tv_transaction_datetime;
        public final TextView tv_transaction_amount;
        public final TextView tv_transaction_type;
        public final TextView tv_transaction_status;

        public final LinearLayout ll_id_section;

        public TransactionDetail mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_transaction_id = (TextView) view.findViewById(R.id.tv_terminal_id);
            tv_transaction_datetime = (TextView) view.findViewById(R.id.tv_transaction_datetime);
            tv_transaction_amount = (TextView) view.findViewById(R.id.tv_transaction_amount);
            tv_transaction_type = (TextView) view.findViewById(R.id.tv_transaction_type);
            tv_transaction_status = (TextView) view.findViewById(R.id.tv_transaction_status);
            ll_id_section = (LinearLayout) view.findViewById(R.id.ll_id_section);

            if(viewType == VIEW_TYPE.TYPE_TERMINAL_TRANSACTION.getEnumValue())
                ll_id_section.setVisibility(View.GONE);
            else
                ll_id_section.setVisibility(View.VISIBLE);

        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
