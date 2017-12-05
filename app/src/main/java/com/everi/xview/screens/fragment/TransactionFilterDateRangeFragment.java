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
import com.everi.xview.screens.activity.main.MainLogicInterface;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.AppDialog;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TransactionFilterDateRangeFragment extends Fragment implements View.OnClickListener{

    @Inject
    AppPreferenceInterface appPreferenceInterface;

    private MainActivity mainActivity;
    private View mRootView;
    private Toolbar mToolbar;

    @Bind(R.id.rl_filterdate_start)
    RelativeLayout rlFilterdateStart;
    @Bind(R.id.rl_filterdate_end)
    RelativeLayout rlFilterdateEnd;

    @Bind(R.id.tv_selected_startdate)
    TextView tvSelectedStartdate;
    @Bind(R.id.tv_selected_enddate)
    TextView tvSelectedEnddate;


    public TransactionFilterDateRangeFragment() {

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
        mRootView = inflater.inflate(R.layout.fragment_transaction_filter_daterange, container, false);
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

        rlFilterdateStart.setOnClickListener(this);
        rlFilterdateEnd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        HashMap<String,String> bundleData = new HashMap<>();
        bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Transactions");

//        final AppPreference appPreference = new AppPreference(getActivity());
        switch (v.getId()){
            case R.id.rl_filterdate_start:

                AppDialog.displayDatePickerDialog(getActivity(), tvSelectedStartdate, "MM/dd/yyyy", new AppDialog.PickDateListener() {
                    @Override
                    public void OnDateSelected(String date) {
                        appPreferenceInterface.saveStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_DATE_TO.name(),date);
//                        appPreference.saveStringValue(AppPreference.TransactionFilterTag.TAG_DATE_TO.name(),date);
                    }
                });

                break;

            case R.id.rl_filterdate_end:

                AppDialog.displayDatePickerDialog(getActivity(), tvSelectedEnddate, "MM/dd/yyyy", new AppDialog.PickDateListener() {
                    @Override
                    public void OnDateSelected(String date) {
//                        appPreference.saveStringValue(AppPreference.TransactionFilterTag.TAG_DATE_FROM.name(),date);
                        appPreferenceInterface.saveStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_DATE_FROM.name(),date);


                    }
                });
                break;

        }
    }


}
