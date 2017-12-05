package com.everi.xview.screens.fragment;

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
import com.everi.xview.adapter.TerminalListAdapter;
import com.everi.xview.models.TerminalModel;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalDashBoardFragment;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TerminalListFragment extends Fragment{

    private MainActivity mainActivity;
    private LinearLayoutManager mLayoutManager;
    private View mRootView;
    private TerminalListAdapter terminalListAdapter;

    @Bind(R.id.rv_terminal_list)
    RecyclerView rvTerminalList;

    @Bind(R.id.search_terminal)
    SearchView searchTerminal;

    public TerminalListFragment() {

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
        mRootView = inflater.inflate(R.layout.fragment_terminal_list, container, false);
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
        rvTerminalList.setLayoutManager(mLayoutManager);
    }

    private void setRequiredEvents(){



    }




    private void setTerminalList(){
        List<TerminalModel> terminalModelList = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            TerminalModel terminalModel =  new TerminalModel();
            terminalModel.terminalId = "TER000A" + (i+1);
            terminalModel.merchant = "HQ Merchant";
            terminalModel.dateTime = "10/17/2017 4:11 PM";

            terminalModelList.add(terminalModel);
        }

        terminalListAdapter = new TerminalListAdapter(getActivity(),terminalModelList,new TerminalListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(View view, int position, Object selectedItem) {

                TerminalModel terminalModel = (TerminalModel)selectedItem;

                HashMap<String,String> bundleData = new HashMap<>();
                bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Terminal Dashboard");

                Utility.startFragmentInContainer1(getActivity(),TerminalDashBoardFragment.class.getName(),true,bundleData);


            }
        });

        rvTerminalList.setAdapter(terminalListAdapter);

        searchTerminal.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                terminalListAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }




}
