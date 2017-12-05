package com.everi.xview.screens.fragment.terminaldashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.everi.xview.R;
import com.everi.xview.adapter.TerminalListAdapter;
import com.everi.xview.models.TerminalModel;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TerminalListNewFragment extends Fragment {

    private MainActivity mainActivity;
    private LinearLayoutManager mLayoutManager;
    private View mRootView;
    private TerminalListAdapter terminalListAdapter;
    private Toolbar mToolbar;

    @Bind(R.id.rv_terminal_list)
    RecyclerView rvTerminalList;

    @Bind(R.id.search_terminal)
    SearchView searchTerminal;

    public TerminalListNewFragment() {

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
        mRootView = inflater.inflate(R.layout.fragment_terminal_list_new, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeToolbar();
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

    private void initializeToolbar() {
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())) {
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mToolbar.setTitle(heading);
        }
    }


    private void initializeUiComponents() {

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvTerminalList.setLayoutManager(mLayoutManager);
    }

    private void setRequiredEvents() {

    }

    private void setTerminalList() {
        List<TerminalModel> terminalModelList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TerminalModel terminalModel = new TerminalModel();
            terminalModel.terminalId = "TER000A" + (i + 1);
            terminalModel.merchant = "HQ Merchant";
            terminalModel.dateTime = "10/17/2017 4:11 PM";

            terminalModelList.add(terminalModel);
        }

        terminalListAdapter = new TerminalListAdapter(getActivity(), terminalModelList, new TerminalListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(View view, int position, Object selectedItem) {

                TerminalModel terminalModel = (TerminalModel) selectedItem;

                HashMap<String, String> bundleData = new HashMap<>();
                bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(), terminalModel.terminalId);

                Utility.startFragmentInContainer1(getActivity(), TerminalDetailsFragment.class.getName(), true, bundleData);


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
