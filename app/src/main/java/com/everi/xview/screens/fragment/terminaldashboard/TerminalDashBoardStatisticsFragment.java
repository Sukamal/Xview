package com.everi.xview.screens.fragment.terminaldashboard;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


public class TerminalDashBoardStatisticsFragment extends Fragment implements View.OnClickListener{


    private MainActivity mainActivity;
    private View mRootView;
    private List<TransactionHeading> transactionHeadingList;
    private LayoutInflater layoutInflater;


    @Bind(R.id.ll_terminal_statistics_container)
    LinearLayout llStatisticsContainer;



    public TerminalDashBoardStatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();


        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_terminal_dashboard_statistics, container, false);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    private void setTitle(){
        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())){
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mainActivity.setToolbarTitle(heading);
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
                    int totalCount = 0;
                    for(int i = 1; i < transactionHeading.transactionRowValueList.size(); i++){
                        totalCount = totalCount + Integer.valueOf(transactionHeading.transactionRowValueList.get(i).columnValue2);
                    }

                    for(int i = 0; i < transactionHeading.transactionRowValueList.size(); i++){

                        TransactionRowValue rowValue = transactionHeading.transactionRowValueList.get(i);
                        View rowView = layoutInflater.inflate(R.layout.transaction_dash_details_row,null,false);

                        ((TextView)rowView.findViewById(R.id.tv_column_1)).setText(rowValue.columnValue1);
                        ((TextView)rowView.findViewById(R.id.tv_column_2)).setText(rowValue.columnValue2);


                        if(i == 0){
                            rowView.setBackgroundColor(getResources().getColor(R.color.dashboard_rowcolor_2));
                            ((TextView)rowView.findViewById(R.id.tv_column_3)).setText(rowValue.columnValue3);

                            ((TextView)rowView.findViewById(R.id.tv_column_1)).setTypeface((Typeface.DEFAULT_BOLD));
                            ((TextView)rowView.findViewById(R.id.tv_column_2)).setTypeface((Typeface.DEFAULT_BOLD));
                            ((TextView)rowView.findViewById(R.id.tv_column_3)).setTypeface((Typeface.DEFAULT_BOLD));
                        }else{
                            double ss = Double.parseDouble(rowValue.columnValue2);
                            double percent = (ss / totalCount) * 100;
                            ((TextView)rowView.findViewById(R.id.tv_column_3)).setText(String.format("%.2f",percent) + "%");
                            if(i%2 == 0){
                                rowView.setBackgroundColor(getResources().getColor(R.color.dashboard_rowcolor_3));
                            }else{
                                rowView.setBackgroundColor(getResources().getColor(android.R.color.white));

                            }
                        }


                        llRowContainer.addView(rowView);

                    }
                }

                llStatisticsContainer.addView(headerView);
            }
        }


    }

    private void doTestBuildTransactionDetails(){
        transactionHeadingList = new ArrayList<>();
        TransactionHeading transactionHeading;
        TransactionRowValue transactionRowValue;

        //***********************************************
        transactionHeading= new TransactionHeading();
        transactionHeading.heading = "Card Type";
        transactionHeading.transactionRowValueList = new ArrayList<>();

        transactionRowValue = new TransactionRowValue("Card Type","Count","%");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Visa","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Master Card","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Discover","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("American Express","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("POS DCC","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("ATM DCC","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionHeadingList.add(transactionHeading);
        //***********************************************

        transactionHeading= new TransactionHeading();
        transactionHeading.heading = "Transaction Type";
        transactionHeading.transactionRowValueList = new ArrayList<>();

        transactionRowValue = new TransactionRowValue("transaction Type","Count", "%");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Pos Debit","39");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Pinless Credit","27");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("ATM Withdrawal","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("ATM Balance Inquiry","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Check Cashing","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionHeadingList.add(transactionHeading);

        //***********************************************

        transactionHeading= new TransactionHeading();
        transactionHeading.heading = "Response";
        transactionHeading.transactionRowValueList = new ArrayList<>();

        transactionRowValue = new TransactionRowValue("Response","Count","%");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Approved","61");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Declined","5");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Denied","2");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Void","2");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Reversal","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionHeadingList.add(transactionHeading);

        //***********************************************

        transactionHeading= new TransactionHeading();
        transactionHeading.heading = "Financial Network";
        transactionHeading.transactionRowValueList = new ArrayList<>();

        transactionRowValue = new TransactionRowValue("Network","Count","%");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Planet Payment","24");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Shazam","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Nyce","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Interact","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Visa plus","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Star","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("MDS","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("FDMS","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Fiserv","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("MC Cirrus","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("PULSE","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("MCCredit","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("VisaBASE1","0");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionHeadingList.add(transactionHeading);


        //***********************************************

        transactionHeading= new TransactionHeading();
        transactionHeading.heading = "Entry Mode";
        transactionHeading.transactionRowValueList = new ArrayList<>();

        transactionRowValue = new TransactionRowValue("Entry Mode","Count","%");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Chip","4");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Non Chip","34");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionRowValue = new TransactionRowValue("Fall Back","1");
        transactionHeading.transactionRowValueList.add(transactionRowValue);

        transactionHeadingList.add(transactionHeading);

    }

}
