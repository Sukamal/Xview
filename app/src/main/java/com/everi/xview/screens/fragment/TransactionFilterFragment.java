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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.everi.xview.R;
import com.everi.xview.XviewApplication;
import com.everi.xview.preference.AppPreferenceImpl;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.Utility;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TransactionFilterFragment extends Fragment implements View.OnClickListener{

    @Inject
    AppPreferenceInterface appPreferenceInterface;

    private MainActivity mainActivity;
    private View mRootView;
    private Toolbar mToolbar;
//    private AppPreference appPreference;

    @Bind(R.id.rl_filter_by_merchant)    RelativeLayout rlFilterByMerchant;
    @Bind(R.id.rl_filter_by_status)    RelativeLayout rlFilterByStatus;
    @Bind(R.id.rl_filter_by_type)    RelativeLayout rlFilterByType;
    @Bind(R.id.rl_filter_by_date)    RelativeLayout rlFilterByDate;
    @Bind(R.id.tv_selected_merchant)    TextView tvSelectedMerchant;
    @Bind(R.id.tv_selected_status)    TextView tvSelectedStatus;
    @Bind(R.id.tv_selected_type)    TextView tvSelectedType;
    @Bind(R.id.tv_selected_date)    TextView tvSelectedDate;
    @Bind(R.id.tv_reset_merchant) TextView tvResetMerchant;
    @Bind(R.id.tv_reset_status) TextView tvResetStatus;
    @Bind(R.id.tv_reset_type) TextView tvResetType;
    @Bind(R.id.tv_reset_date) TextView tvRresetDate;



    public TransactionFilterFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mainActivity.setNavDrawerEnabled(false);
        mainActivity.getToolbar().setVisibility(View.GONE);
//        appPreference = new AppPreference(getActivity());

        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_transaction_filter, container, false);
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
        setSelectedFilter();
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


    private void initializeUiComponents(){


    }

    private void setRequiredEvents(){

        rlFilterByMerchant.setOnClickListener(this);
        rlFilterByStatus.setOnClickListener(this);
        rlFilterByType.setOnClickListener(this);
        rlFilterByDate.setOnClickListener(this);

        tvResetMerchant.setOnClickListener(this);
        tvResetStatus.setOnClickListener(this);
        tvResetType.setOnClickListener(this);
        tvRresetDate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        HashMap<String,String> bundleData = new HashMap<>();
        bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Transactions");
        switch (v.getId()){
            case R.id.rl_filter_by_merchant:

                bundleData.put(AppConstant.ExtraTag.TAG_FILTER_BY.getEnumValue(), AppConstant.FilterCategory.FILTER_BY_MERCHANT.getEnumValue());
                Utility.startFragmentInContainer1(getActivity(),TransactionFilterWheelFragment.class.getName(),true,bundleData);

                break;

            case R.id.rl_filter_by_status:

                bundleData.put(AppConstant.ExtraTag.TAG_FILTER_BY.getEnumValue(), AppConstant.FilterCategory.FILTER_BY_STATUS.getEnumValue());
                Utility.startFragmentInContainer1(getActivity(),TransactionFilterWheelFragment.class.getName(),true,bundleData);


                break;

            case R.id.rl_filter_by_type:

                bundleData.put(AppConstant.ExtraTag.TAG_FILTER_BY.getEnumValue(), AppConstant.FilterCategory.FILTER_BY_TYPE.getEnumValue());
                Utility.startFragmentInContainer1(getActivity(),TransactionFilterWheelFragment.class.getName(),true,bundleData);

                break;

            case R.id.rl_filter_by_date:
                Utility.startFragmentInContainer1(getActivity(),TransactionFilterDateRangeFragment.class.getName(),true,bundleData);

                break;

            case R.id.tv_reset_merchant:
                resetFilterOption(AppPreferenceImpl.TransactionFilterTag.TAG_MERCHANT.name());
                break;

            case R.id.tv_reset_status:
                resetFilterOption(AppPreferenceImpl.TransactionFilterTag.TAG_STATUS.name());
                break;

            case R.id.tv_reset_type:
                resetFilterOption(AppPreferenceImpl.TransactionFilterTag.TAG_TYPE.name());
                break;

            case R.id.tv_reset_date:
                resetFilterOption(AppPreferenceImpl.TransactionFilterTag.TAG_DATE_FROM.name());
                resetFilterOption(AppPreferenceImpl.TransactionFilterTag.TAG_DATE_TO.name());
                break;
        }
    }



    private void resetFilterOption(String key){
        appPreferenceInterface.clearPreferences(key);
        setSelectedFilter();

    }

    private void setSelectedFilter(){

        String filterOption;

        filterOption = appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_MERCHANT.name());

        if(!Utility.isNullOrBlank(filterOption)){
            tvSelectedMerchant.setText(filterOption);
            tvResetMerchant.setVisibility(View.VISIBLE);
        }else{
            tvSelectedMerchant.setText("");
            tvResetMerchant.setVisibility(View.GONE);
        }

        filterOption = appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_STATUS.name());
        if(!Utility.isNullOrBlank(filterOption)){
            tvSelectedStatus.setText(filterOption);
            tvResetStatus.setVisibility(View.VISIBLE);
        }else{
            tvSelectedStatus.setText("");
            tvResetStatus.setVisibility(View.GONE);
        }

        filterOption = appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_TYPE.name());
        if(!Utility.isNullOrBlank(filterOption)){
            tvSelectedType.setText(filterOption);
            tvResetType.setVisibility(View.VISIBLE);
        }else{
            tvSelectedType.setText("");
            tvResetType.setVisibility(View.GONE);
        }


        if(!Utility.isNullOrBlank(appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_DATE_TO.name())) &&
                !Utility.isNullOrBlank(appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_DATE_FROM.name())) ){
            String dateRange = Utility.convertDateFormat(appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_DATE_TO.name()),"yyyy-MM-dd","MM/dd/yyyy")
                    + " - " + Utility.convertDateFormat(appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_DATE_FROM.name()),"yyyy-MM-dd","MM/dd/yyyy");
            tvSelectedDate.setText(dateRange);
            tvRresetDate.setVisibility(View.VISIBLE);


        }else{
            tvSelectedDate.setText("");
            tvRresetDate.setVisibility(View.GONE);

        }
    }
}
