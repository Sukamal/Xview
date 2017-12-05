package com.everi.xview.screens.fragment.terminaldashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


public class TerminalDashBoardFragment extends Fragment {


    private MainActivity mainActivity;
    private View mRootView;
    private List<Fragment> fragmentList;
    private Toolbar mToolbar;

    @Bind(R.id.pager)
    ViewPager pager;

    @Bind(R.id.ll_page_indicator)
    LinearLayout llPageIndicator;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;


    public TerminalDashBoardFragment() {

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
        mRootView = inflater.inflate(R.layout.fragment_terminal_dashboard, container, false);
        ButterKnife.bind(this, mRootView);
        setPagerAdapter();
        setTabs();
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setTitle();
        initializeToolbar();
        initializeUiComponents();
        setRequiredEvents();
        addPageIndicator();
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

    private void initializeToolbar(){
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())){
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mToolbar.setTitle(heading);
        }
    }


    private void initializeUiComponents() {

    }

    private void setRequiredEvents() {

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    private void setPagerAdapter() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new TerminalDashBoardDetailsFragment());
        fragmentList.add(new TerminalDashBoardStatisticsFragment());
        fragmentList.add(new TerminalViewTransactionListFragment());

        DashBoardPagerAdapter pagerAdapter = new DashBoardPagerAdapter(getActivity(), getChildFragmentManager(), fragmentList);
        pager.setAdapter(pagerAdapter);


    }


    private void addPageIndicator() {
        if (fragmentList != null && fragmentList.size() > 1) {
            for (int i = 0; i < fragmentList.size(); i++) {

                ImageView imageView = new ImageView(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Utility.dip2px(getActivity(), 10), Utility.dip2px(getActivity(), 10));
                layoutParams.rightMargin = Utility.dip2px(getActivity(), 5);
                imageView.setLayoutParams(layoutParams);
                llPageIndicator.addView(imageView);

            }

            showPageIndicator(0);
        }

    }

    private void showPageIndicator(int index) {

        for (int i = 0; i < llPageIndicator.getChildCount(); i++) {
            View view = llPageIndicator.getChildAt(i);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(R.drawable.shape_page_indicator_inactive);
            }
        }

        ((ImageView) llPageIndicator.getChildAt(index)).setImageResource(R.drawable.shape_page_indicator_active);
    }

    private void setTabs(){
        tabLayout.addTab(tabLayout.newTab().setText("General Information"));
        tabLayout.addTab(tabLayout.newTab().setText("Statistics"));
        tabLayout.addTab(tabLayout.newTab().setText("View Transactions"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
