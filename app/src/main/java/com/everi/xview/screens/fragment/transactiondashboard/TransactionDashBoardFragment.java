package com.everi.xview.screens.fragment.transactiondashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.everi.xview.R;
import com.everi.xview.adapter.DashBoardPagerAdapter;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TransactionDashBoardFragment extends Fragment implements View.OnClickListener{


    private MainActivity mainActivity;
    private View mRootView;
    private List<Fragment> fragmentList;

    @Bind(R.id.pager)
    ViewPager pager;

    @Bind(R.id.ll_page_indicator)
    LinearLayout llPageIndicator;



    public TransactionDashBoardFragment() {

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
        mRootView = inflater.inflate(R.layout.fragment_transaction_dashboard, container, false);
        ButterKnife.bind(this,mRootView);
        setPagerAdapter();
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle();
        initializeUiComponents();
        setRequiredEvents();
        addPageIndicator();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

        }
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

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                showPageIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPagerAdapter(){
        fragmentList = new ArrayList<>();
        fragmentList.add(new TransactionDashBoardGraphFragment());
        fragmentList.add(new TransactionDashBoardDetailsFragment());

        DashBoardPagerAdapter pagerAdapter = new DashBoardPagerAdapter(getActivity(),getChildFragmentManager(),fragmentList);
        pager.setAdapter(pagerAdapter);
    }


    private void addPageIndicator(){
        if(fragmentList != null && fragmentList.size() > 1){
            for(int i =0 ; i < fragmentList.size(); i ++){

                ImageView imageView = new ImageView(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Utility.dip2px(getActivity(),10),Utility.dip2px(getActivity(),10));
                layoutParams.rightMargin = Utility.dip2px(getActivity(),5);
                imageView.setLayoutParams(layoutParams);
                llPageIndicator.addView(imageView);

            }

        }

        showPageIndicator(0);
    }

    private void showPageIndicator(int index){

        for(int i = 0; i < llPageIndicator.getChildCount(); i++){
            View view = llPageIndicator.getChildAt(i);
            if(view instanceof ImageView){
                ((ImageView) view).setImageResource(R.drawable.shape_page_indicator_inactive);
            }
        }

        ((ImageView) llPageIndicator.getChildAt(index)).setImageResource(R.drawable.shape_page_indicator_active);
    }




}
