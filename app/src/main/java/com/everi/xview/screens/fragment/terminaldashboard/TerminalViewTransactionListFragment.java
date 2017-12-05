package com.everi.xview.screens.fragment.terminaldashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.everi.xview.R;
import com.everi.xview.adapter.TransactionListAdapter;
import com.everi.xview.models.TransactionDetail;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.screens.fragment.transactiondetails.TransactionDetailsFragment;
import com.everi.xview.screens.fragment.transactionlist.TransactionListFragment;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;


public class TerminalViewTransactionListFragment extends Fragment{

    private MainActivity mainActivity;
    private RecyclerView RV_transactionlist;
    private LinearLayoutManager mLayoutManager;
    private View mRootView;


    public TerminalViewTransactionListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_terminal_view_transaction_list, container, false);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle();
        initializeUiComponents();
        setRequiredEvents();
        settransactionList();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            mainActivity.onBackPressed();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }



    private void setTitle(){
        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())){
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mainActivity.setToolbarTitle(heading);
        }
    }


    private void initializeUiComponents(){

        RV_transactionlist = (RecyclerView) mRootView.findViewById(R.id.rv_transactionlist);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_transactionlist.setLayoutManager(mLayoutManager);
    }

    private void setRequiredEvents(){

    }


    private void settransactionList(){
        List<TransactionDetail> transactionDetailList = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            TransactionDetail transactionDetail = new TransactionDetail();

            transactionDetail.setTerminalID( "NV000A1 " + (i+1));
            transactionDetail.setTransactionDtTm( "02/13/2017 05:00:59 PM PST");
            transactionDetail.setReceivedTotalAmount( "$100.00");
            transactionDetail.setTransactionType( "Withdrawal");
            transactionDetail.setResponse( "Approved");
            transactionDetailList.add(transactionDetail);
        }




        TransactionListAdapter transactionListAdapter = new TransactionListAdapter(getActivity(), transactionDetailList,TransactionListAdapter.VIEW_TYPE.TYPE_TERMINAL_TRANSACTION.getEnumValue(),  new TransactionListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(View view, int position, Object selectedItem) {

                TransactionDetail transactionDetail = (TransactionDetail)selectedItem;
                TransactionListFragment.selectedModel = transactionDetail;

                HashMap<String,String> bundleData = new HashMap<>();
                bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Transaction Details");

                Utility.startFragmentInContainer1(getActivity(),TransactionDetailsFragment.class.getName(),true,bundleData);


            }
        });

        RV_transactionlist.setAdapter(transactionListAdapter);
    }




}
