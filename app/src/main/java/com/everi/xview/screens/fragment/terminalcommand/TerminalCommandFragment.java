package com.everi.xview.screens.fragment.terminalcommand;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.everi.xview.R;
import com.everi.xview.models.TransactionRowValue;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TerminalCommandFragment extends Fragment {


    private MainActivity mainActivity;
    private View mRootView;

    private List<TransactionRowValue> transactionRowValueList;
    private LayoutInflater layoutInflater;

    @Bind(R.id.ll_terminal_command_container)
    LinearLayout llCommandContainer;


    public TerminalCommandFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        mainActivity.setNavDrawerEnabled(true);
        mainActivity.getToolbar().setVisibility(View.VISIBLE);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_terminal_command, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle();
        initializeUiComponents();
        setRequiredEvents();
        setValuesToUiComponent();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void setTitle() {
        if (getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())) {
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mainActivity.setToolbarTitle(heading);
        }
    }


    private void initializeUiComponents() {

    }

    private void setRequiredEvents() {


    }


    private void setValuesToUiComponent(){
        doTestBuildTerminalDetails();

        if(transactionRowValueList != null){
            layoutInflater =  (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            boolean status = true;
            for(TransactionRowValue rowValue : transactionRowValueList){

                View rowView = layoutInflater.inflate(R.layout.transaction_details_row,null,false);
                ((TextView)rowView.findViewById(R.id.tv_details_column_1)).setText(rowValue.columnValue1);
                ((TextView)rowView.findViewById(R.id.tv_details_column_2)).setText(rowValue.columnValue2);

                if(status)
                    rowView.setBackgroundColor(getResources().getColor(R.color.dashboard_rowcolor_3));
                else
                    rowView.setBackgroundColor(getResources().getColor(android.R.color.white));

                status = !status;

                llCommandContainer.addView(rowView);

            }
        }


    }

    private void doTestBuildTerminalDetails(){

        transactionRowValueList = new ArrayList<>();
        TransactionRowValue transactionRowValue;

        transactionRowValue = new TransactionRowValue(getString(R.string.txt_terminal_model),"CASHCLUB POS TERMINAL");
        transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue(getString(R.string.txt_address),"3555 Las Vegas");
        transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue(getString(R.string.txt_city),"Las Vegas");
        transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue(getString(R.string.txt_state_province),"NA");
        transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue(getString(R.string.txt_zipcode),"89109");
        transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue(getString(R.string.txt_cust_terminal_id),"50014549");
        transactionRowValueList.add(transactionRowValue);


    }




}
