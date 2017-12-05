package com.everi.xview.screens.fragment.atmstaedashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.everi.xview.R;
import com.everi.xview.customviews.piechart.PieChartHelper;
import com.everi.xview.customviews.piechart.PieChartView;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.util.AppConstant;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ATMstateDashBoardGraphFragment extends Fragment implements View.OnClickListener{

    private MainActivity mainActivity;
    private View mRootView;


    @Bind(R.id.pie_view) PieChartView pieChartView;
    @Bind(R.id.tv_state_active) TextView tvStateActive;
    @Bind(R.id.tv_state_nonactive) TextView tvStateNonactive;

    @Bind(R.id.v_color_1) View vColor_1;
    @Bind(R.id.v_color_2) View vColor_2;

    public ATMstateDashBoardGraphFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_atmstate_dashboard_graph, container, false);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle();
        initializeUiComponents();
        setRequiredEvents();
        set(pieChartView);
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

    }

    private void set(PieChartView pieView) {
        ArrayList<PieChartHelper> pieHelperArrayList = new ArrayList<PieChartHelper>();

        int color1 = getResources().getColor(R.color.g_color_1);
        int color2 = getResources().getColor(R.color.g_color_3);

        pieHelperArrayList.add(new PieChartHelper(65, color1));
        pieHelperArrayList.add(new PieChartHelper(35, color2));

        vColor_1.setBackgroundColor(color1);
        vColor_2.setBackgroundColor(color2);


        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieChartView.OnPieClickListener() {
            @Override
            public void onPieClick(int index) {
                if (index != PieChartView.NO_SELECTED_INDEX) {
                } else {
                }
            }
        });
    }

}
