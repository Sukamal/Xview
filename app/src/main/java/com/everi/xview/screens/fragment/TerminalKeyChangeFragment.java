package com.everi.xview.screens.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.everi.xview.R;
import com.everi.xview.customviews.TerminalKey;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TerminalKeyChangeFragment extends Fragment {


    private MainActivity mainActivity;
    private View mRootView;
   private List<String> keys;

    @Bind(R.id.ll_terminal_container)
    LinearLayout llTerminalContainer;
    /*@Bind(R.terminalId.fab_add_key)
    FloatingActionButton actionButton;*/
    @Bind(R.id.sv_terminal_key_scroll)
    ScrollView svKeyScroll;

    public TerminalKeyChangeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keys = new ArrayList<>();
        mainActivity = (MainActivity) getActivity();
        mainActivity.setNavDrawerEnabled(true);
        mainActivity.getToolbar().setVisibility(View.VISIBLE);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_terminal_key_change, container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle();
        initializeUiComponents();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*@OnClick(R.terminalId.fab_add_key)
    void addNewTerminalView(){
        addNewKey();
    }*/


    private void setTitle() {
        if (getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())) {
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mainActivity.setToolbarTitle(heading);
        }
    }


    private void initializeUiComponents() {

        addNewKey();

    }
    private void addNewKeyChangeTerminal(){

        llTerminalContainer.removeAllViews();
        for(String key : keys){
            TerminalKey terminalKey = new TerminalKey(getActivity());
            llTerminalContainer.addView(terminalKey);
        }

    }

    private void addNewKey(){
        keys.add("New Key");
        addNewKeyChangeTerminal();
        svKeyScroll.post(new Runnable() {
            @Override
            public void run() {
                svKeyScroll.fullScroll(ScrollView.FOCUS_DOWN);

            }
        });

    }



}
