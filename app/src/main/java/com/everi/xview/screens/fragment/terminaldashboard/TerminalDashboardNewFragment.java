package com.everi.xview.screens.fragment.terminaldashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.everi.xview.R;
import com.everi.xview.adapter.TerminalDashboardListAdapter;
import com.everi.xview.adapter.TerminalListAdapter;
import com.everi.xview.models.TerminalDashboardModel;
import com.everi.xview.models.TerminalModel;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TerminalDashboardNewFragment extends Fragment{

    private MainActivity mainActivity;
    private LinearLayoutManager mLayoutManager;
    private View mRootView;

    private TerminalDashboardListAdapter dashboardListAdapter;

    @Bind(R.id.rv_terminal_dash_list)
    RecyclerView rvTerminalDashList;

    public TerminalDashboardNewFragment() {

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
        mRootView = inflater.inflate(R.layout.fragment_terminal_dashboard_new, container, false);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setTitle();
        initializeUiComponents();
        setRequiredEvents();
        setTerminalList();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    private void setTitle(){
        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())){
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mainActivity.setToolbarTitle(heading);
        }
    }


    private void initializeUiComponents(){

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvTerminalDashList.setLayoutManager(mLayoutManager);
    }

    private void setRequiredEvents(){

    }

    private void setTerminalList(){
        List<TerminalDashboardModel> terminalModelList = new ArrayList<>();
        TerminalDashboardModel terminalModel;

        terminalModel =  new TerminalDashboardModel("Inactivity","42");
        terminalModelList.add(terminalModel);
        terminalModel =  new TerminalDashboardModel("Communication Failure","57");
        terminalModelList.add(terminalModel);
        terminalModel =  new TerminalDashboardModel("Dispense Failure","29");
        terminalModelList.add(terminalModel);
        terminalModel =  new TerminalDashboardModel("Card Reader Failure","38");
        terminalModelList.add(terminalModel);
        terminalModel =  new TerminalDashboardModel("Operator Mode","75");
        terminalModelList.add(terminalModel);
        terminalModel =  new TerminalDashboardModel("Beast Mode","100");
        terminalModelList.add(terminalModel);

        dashboardListAdapter = new TerminalDashboardListAdapter(getActivity(),terminalModelList,new TerminalDashboardListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(View view, int position, Object selectedItem) {

                TerminalDashboardModel terminalModel = (TerminalDashboardModel)selectedItem;

                HashMap<String,String> bundleData = new HashMap<>();
                bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),terminalModel.getActivityTitle());

                Utility.startFragmentInContainer1(getActivity(),TerminalListNewFragment.class.getName(),true,bundleData);
            }
        });

        rvTerminalDashList.setAdapter(dashboardListAdapter);
    }

}
