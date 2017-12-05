package com.everi.xview.screens.fragment;

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
import android.widget.TextView;

import com.everi.xview.R;
import com.everi.xview.XviewApplication;
import com.everi.xview.customviews.wheelpicker.WheelPicker;
import com.everi.xview.database.MerchantDbHandler;
import com.everi.xview.database.TransactionTypeDbHandler;
import com.everi.xview.models.Merchant;
import com.everi.xview.models.TransactionType;
import com.everi.xview.preference.AppPreferenceImpl;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/*import com.aigestudio.wheelpicker.WheelPicker;*/


public class TransactionFilterWheelFragment extends Fragment implements WheelPicker.OnItemSelectedListener{

    @Inject
    AppPreferenceInterface appPreferenceInterface;

    private MainActivity mainActivity;
    private View mRootView;
    private Toolbar mToolbar;

    private String filterBy;

    @Bind(R.id.main_wheel_left)
    WheelPicker wheelPicker;
    @Bind(R.id.tv_filter_type)
    TextView tvFilterType;


    public TransactionFilterWheelFragment() {

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
        mRootView = inflater.inflate(R.layout.fragment_transaction_filter_wheel, container, false);
        ButterKnife.bind(this,mRootView);
        ((XviewApplication) getActivity().getApplication()).getComponent().inject(this);

        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeToolbar();
        initializeUiComponents();
        setRequiredEvents();
        setDataBasedOnFilter();
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

    private void initializeToolbar(){
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())){
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mToolbar.setTitle(heading);
        }
    }


    private void setDataBasedOnFilter(){
        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_FILTER_BY.getEnumValue())){
            filterBy = getArguments().getString(AppConstant.ExtraTag.TAG_FILTER_BY.getEnumValue());
            if(filterBy.equalsIgnoreCase(AppConstant.FilterCategory.FILTER_BY_MERCHANT.getEnumValue())){
                tvFilterType.setText("Merchants");
//                displayTestDataMerchant();
                displayMerchantList();
            }else if(filterBy.equalsIgnoreCase(AppConstant.FilterCategory.FILTER_BY_STATUS.getEnumValue())){
                tvFilterType.setText("Transaction Status");
                displayTestDataStaus();
            }else if(filterBy.equalsIgnoreCase(AppConstant.FilterCategory.FILTER_BY_TYPE.getEnumValue())){
                tvFilterType.setText("Transaction Type");
//                displayTestDataType();
                displayTransactionTypeList();
            }
        }
    }


    private void initializeUiComponents(){


    }

    private void setRequiredEvents(){

        wheelPicker.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {

//        if(data instanceof TransactionDetail){
//            Toast.makeText(getActivity(), String.valueOf(((TransactionDetail) data).terminalId) +" , "+ String.valueOf(((TransactionDetail) data).type), Toast.LENGTH_SHORT).show();
//        }

//        AppPreference appPreference = new AppPreference(getActivity());

        if(filterBy.equalsIgnoreCase(AppConstant.FilterCategory.FILTER_BY_MERCHANT.getEnumValue())){
//            appPreference.saveStringValue(AppPreference.TransactionFilterTag.TAG_MERCHANT.name(),String.valueOf(data));
            appPreferenceInterface.saveStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_MERCHANT.name(),String.valueOf(data));

        }else  if(filterBy.equalsIgnoreCase(AppConstant.FilterCategory.FILTER_BY_STATUS.getEnumValue())){
//            appPreference.saveStringValue(AppPreference.TransactionFilterTag.TAG_STATUS.name(),String.valueOf(data));
            appPreferenceInterface.saveStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_STATUS.name(),String.valueOf(data));


        }else  if(filterBy.equalsIgnoreCase(AppConstant.FilterCategory.FILTER_BY_TYPE.getEnumValue())){
//            appPreference.saveStringValue(AppPreference.TransactionFilterTag.TAG_TYPE.name(),String.valueOf(data));
            appPreferenceInterface.saveStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_TYPE.name(),String.valueOf(data));


        }

    }

    private void displayMerchantList(){

        List<Merchant> merchantList = null;
        MerchantDbHandler dbHandler = new MerchantDbHandler(getActivity());
        merchantList = dbHandler.getAllMerchant();
        if(merchantList != null)
            wheelPicker.setData(merchantList);

    }

    private void displayTestDataMerchant(){

        List<Merchant> merchantList = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            Merchant merchant = new Merchant();
            merchant.setMerchantSID(i+1);
            merchant.setMerchantName("Merchant_"+ (i+1)) ;
            merchantList.add(merchant);
        }

        wheelPicker.setData(merchantList);

    }

    private void displayTestDataStaus(){
        wheelPicker.setData(Arrays.asList(getResources().getStringArray(R.array.transaction_status_array)));
    }

    private void displayTestDataType(){
        wheelPicker.setData(Arrays.asList(getResources().getStringArray(R.array.transaction_type_array)));

    }

    private void displayTransactionTypeList(){

        List<TransactionType> transactionTypeList = null;
        TransactionTypeDbHandler dbHandler = new TransactionTypeDbHandler(getActivity());
        transactionTypeList = dbHandler.getAllTransactionType();
        wheelPicker.setData(transactionTypeList);

    }
}
