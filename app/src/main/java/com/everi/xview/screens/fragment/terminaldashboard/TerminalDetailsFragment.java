package com.everi.xview.screens.fragment.terminaldashboard;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.everi.xview.R;
import com.everi.xview.models.TransactionHeading;
import com.everi.xview.models.TransactionRowValue;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TerminalDetailsFragment extends Fragment implements View.OnClickListener{


    private MainActivity mainActivity;
    private View mRootView;
    private List<TransactionHeading> transactionHeadingList;
    private LayoutInflater layoutInflater;
    private Toolbar mToolbar;


    @Bind(R.id.ll_terminal_details_container)
    LinearLayout llTerminalDetailsContainer;



    public TerminalDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setNavDrawerEnabled(false);
        mainActivity.getToolbar().setVisibility(View.GONE);

        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_terminal_details, container, false);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeToolbar();
        initializeUiComponents();
        setRequiredEvents();
        setValuesToUiComponent();
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

    @Override
    public void onClick(View v) {

    }

    private void initializeToolbar() {
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())) {
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mToolbar.setTitle(heading);
        }
    }


    private void initializeUiComponents(){


    }

    private void setRequiredEvents(){


    }

    private void setValuesToUiComponent(){
        doTestBuildTransactionDetails();

        if(transactionHeadingList != null){
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            for(TransactionHeading transactionHeading : transactionHeadingList){
                View headerView = layoutInflater.inflate(R.layout.transaction_dash_details_header,null,false);
                ((TextView)headerView.findViewById(R.id.tv_trans_details_header)).setText(transactionHeading.heading);

                LinearLayout llRowContainer = (LinearLayout)headerView.findViewById(R.id.ll_trans_details_row_container);


                if(transactionHeading.transactionRowValueList != null){
                    for(int i = 0; i < transactionHeading.transactionRowValueList.size(); i++){

                        TransactionRowValue rowValue = transactionHeading.transactionRowValueList.get(i);
                        View rowView = layoutInflater.inflate(R.layout.terminal_details_row,null,false);

                        ((TextView)rowView.findViewById(R.id.tv_column_1)).setText(rowValue.columnValue1);
                        ((TextView)rowView.findViewById(R.id.tv_column_2)).setText(rowValue.columnValue2);

                        if(i%2 == 0){
                            rowView.setBackgroundColor(getResources().getColor(android.R.color.white));

                        }else{
                            rowView.setBackgroundColor(getResources().getColor(R.color.dashboard_rowcolor_3));

                        }

                        llRowContainer.addView(rowView);

                    }
                }

                llTerminalDetailsContainer.addView(headerView);
            }
        }


    }

    private void doTestBuildTransactionDetails(){
        transactionHeadingList = new ArrayList<>();
        TransactionHeading transactionHeading;
        TransactionRowValue transactionRowValue;

        //***********************************************
        transactionHeading= new TransactionHeading();
        transactionHeading.heading = "General Information";
        transactionHeading.transactionRowValueList = new ArrayList<>();

        transactionRowValue = new TransactionRowValue("Surcharge","0.00",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Card Reader Type","Swipe",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Timeout Reverse","No",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Created Date","12/10/2017 11:36",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Processors","CDS",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Terminal Subtype","ATM GCA ATM",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("IP Address","10.43.22.226",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Bill Split","High",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Print Address","No",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Terminal Zone","Floor",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);


        transactionHeadingList.add(transactionHeading);
        //***********************************************


        transactionHeading= new TransactionHeading();
        transactionHeading.heading = "Fee Profile";
        transactionHeading.transactionRowValueList = new ArrayList<>();

        transactionRowValue = new TransactionRowValue("Fee Profile","60608ACD",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Load Profile","ATM_MAX_3000_CB",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Media Profile","GCAAC-Least Bils",null);
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionHeadingList.add(transactionHeading);
        //***********************************************

    }

}
