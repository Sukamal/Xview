package com.everi.xview.screens.fragment.transactiondetails;

import android.content.Context;
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
import com.everi.xview.XviewApplication;
import com.everi.xview.models.TransactionDetail;
import com.everi.xview.models.TransactionDetails;
import com.everi.xview.models.TransactionRowValue;
import com.everi.xview.networkapi.ApiServices;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.screens.fragment.transactionlist.TransactionListFragment;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.MessageFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TransactionDetailsFragment extends Fragment implements View.OnClickListener,TransactionDetailViewInterface{

    @Inject
    ApiServices apiServices;
    @Inject
    MessageFactory messageFactory;

    private MainActivity mainActivity;
    private View mRootView;
    private Toolbar mToolbar;

    private TransactionDetail selectedtransactionDetail;
    private List<TransactionRowValue> transactionRowValueList;
    private LayoutInflater layoutInflater;

    private TransactionDetailLogicImpl detailLogic;
    private String transactionUid;

    @Bind(R.id.ll_details_container)
    LinearLayout llDetailsContainer;



    public TransactionDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setNavDrawerEnabled(false);
        mainActivity.getToolbar().setVisibility(View.GONE);
        ((XviewApplication) getActivity().getApplication()).getComponent().inject(this);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_transaction_details, container, false);
        ButterKnife.bind(this,mRootView);
        detailLogic = new TransactionDetailLogicImpl(getActivity(),this,apiServices,messageFactory);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeToolbar();
        initializeUiComponents();
        setRequiredEvents();
//        setValuesToUiComponent();

        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_TRANSACTION_UID.getEnumValue())){
            transactionUid = getArguments().getString(AppConstant.ExtraTag.TAG_TRANSACTION_UID.getEnumValue());
        }

        detailLogic.displayTransactionDetails(transactionUid);
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

    private void initializeToolbar(){
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())){
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mToolbar.setTitle(heading);
        }
    }


    private void initializeUiComponents(){


    }

    private void setRequiredEvents(){


    }

    private void setValuesToUiComponent(){

//        doTestBuildTransactionDetails();

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

                llDetailsContainer.addView(rowView);

            }
        }


    }


    private void doTestBuildTransactionDetails(){

        transactionRowValueList = new ArrayList<>();
        TransactionRowValue transactionRowValue;

        selectedtransactionDetail = TransactionListFragment.selectedModel;

        if(selectedtransactionDetail != null){

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_id), selectedtransactionDetail.getTerminalID());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_date_time), selectedtransactionDetail.getTransactionDtTm());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_total_amount), selectedtransactionDetail.getReceivedTotalAmount());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_xctn_type), selectedtransactionDetail.getTransactionType());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_xctn_status), selectedtransactionDetail.getResponse());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_req_amount),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_req_surcharge),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_completed_amount),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_completed_surcharge),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_auth_amount),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_card_no),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_receipt_no),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_ref_no),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_sequence_no),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_entry_mode),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_financial_network),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_processor),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_terminal_id_initiated),"");
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_terminal_id_completed),"");
            transactionRowValueList.add(transactionRowValue);


        }
    }

    @Override
    public void displayTransactionDetails(List<TransactionDetails> transactionDetailList) {
        if(transactionDetailList != null && transactionDetailList.size() > 0){
            TransactionDetails transactionDetails = transactionDetailList.get(0);
            populateRowView(transactionDetails);
        }
    }

    @Override
    public void errorInData(String message) {

    }

    private void populateRowView(TransactionDetails transactionDetails){
        transactionRowValueList = new ArrayList<>();
        TransactionRowValue transactionRowValue;

        if(transactionDetails != null){

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_transaction_category), transactionDetails.getTransactionCategory());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_transaction_type), transactionDetails.getTransactionType());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_transaction_subtype), transactionDetails.getTransactionSubType());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_transaction_system_datetime), transactionDetails.getTransactionSystemDateTime());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_transaction_datetime), transactionDetails.getTransactionDateTime());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_transaction_status),transactionDetails.getTransactionStatus());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_response),transactionDetails.getResponse());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_currency),transactionDetails.getCurrency());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_requested_amount),transactionDetails.getRequestedAmount());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_requested_fee),transactionDetails.getRequestedFee());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_requested_total_amount),transactionDetails.getRequestedTotalAmount());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_authorized_amount),transactionDetails.getAuthorizedAmount());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_authorized_currency),transactionDetails.getAuthorizedCurrency());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_received_amount),transactionDetails.getReceivedAmount());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_received_fee),transactionDetails.getReceivedFee());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_received_total_amount),transactionDetails.getRequestedTotalAmount());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_card_account_umber),transactionDetails.getCardOrAccountNumber());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_card_type),transactionDetails.getCardType());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_dispensed),transactionDetails.getDispensed());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_authorization_number),transactionDetails.getAuthorizationNumber());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_everi_receipt_number),transactionDetails.getEVERIReceiptNumber());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_reference_number),transactionDetails.getReferenceNumber());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_sequence_number),transactionDetails.getSequenceNumber());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_rrn),transactionDetails.getRRN());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_entry_mode),transactionDetails.getEntryMode());
            transactionRowValueList.add(transactionRowValue);
            transactionRowValue = new TransactionRowValue(getString(R.string.txt_address_verification_code),transactionDetails.getAddressVerificationCode());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_avs_zip_code_verified),transactionDetails.getAVSZipCodeVerified());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_financial_network),transactionDetails.getFinancialNetwork());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_processor),transactionDetails.getProcessor());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_settlement_date),transactionDetails.getSettlementDate());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_merchant_id),transactionDetails.getMerchantID());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_merchant_name),transactionDetails.getMerchantName());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_country_code),transactionDetails.getCountryCode());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_terminal_id_initiated),transactionDetails.getTerminalIDInitiated());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_terminal_id_completed),transactionDetails.getTerminalIDCompleted());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_terminal_category),transactionDetails.getTerminalCategory());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_terminal_type),transactionDetails.getTerminalType());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_user_id),transactionDetails.getUserID());
            transactionRowValueList.add(transactionRowValue);

            transactionRowValue = new TransactionRowValue(getString(R.string.txt_patron_name),transactionDetails.getPatronName());
            transactionRowValueList.add(transactionRowValue);


        }

        setValuesToUiComponent();
    }
}
