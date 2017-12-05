package com.everi.xview.screens.fragment.transactiondashboard;

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


public class TransactionDashBoardGraphFragment extends Fragment implements View.OnClickListener{

    private MainActivity mainActivity;
    private View mRootView;

    /*String[] petNames = new String[] {getString(R.string.txt_pos_debit),getString(R.string.txt_pinless_credit),
            getString(R.string.txt_atm_withdrawal),getString(R.string.txt_atm_bal_inq),getString(R.string.txt_check_cash),
            getString(R.string.txt_dcc_sing)};

    int[] percentage = new int[] { 50, 25, 10, 5, 5, 5 };*/

    @Bind(R.id.pie_view) PieChartView pieChartView;
    @Bind(R.id.tv_pos_debit) TextView tvPosDebit;
    @Bind(R.id.tv_pinless_credit) TextView tvPinlessCredit;
    @Bind(R.id.tv_atm_withdrawal) TextView tvAtmWithdrawal;
    @Bind(R.id.tv_atm_bal_inq) TextView tvAtmBalInq;
    @Bind(R.id.tv_check_cash) TextView tvCheckCash;
    @Bind(R.id.tv_dcc_sign) TextView tvDccSign;


    @Bind(R.id.v_color_1) View vColor_1;
    @Bind(R.id.v_color_2) View vColor_2;
    @Bind(R.id.v_color_3) View vColor_3;
    @Bind(R.id.v_color_4) View vColor_4;
    @Bind(R.id.v_color_5) View vColor_5;
    @Bind(R.id.v_color_6) View vColor_6;

    public TransactionDashBoardGraphFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_transaction_dashboard_graph, container, false);
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
        int color2 = getResources().getColor(R.color.g_color_2);
        int color3 = getResources().getColor(R.color.g_color_3);
        int color4 = getResources().getColor(R.color.g_color_4);
        int color5 = getResources().getColor(R.color.g_color_5);
        int color6 = getResources().getColor(R.color.g_color_6);

        pieHelperArrayList.add(new PieChartHelper(45, color1));
        pieHelperArrayList.add(new PieChartHelper(25, color2));
        pieHelperArrayList.add(new PieChartHelper(10, color3));
        pieHelperArrayList.add(new PieChartHelper(10, color4));
        pieHelperArrayList.add(new PieChartHelper(5, color5));
        pieHelperArrayList.add(new PieChartHelper(5, color6));



        vColor_1.setBackgroundColor(color1);
        vColor_2.setBackgroundColor(color2);
        vColor_3.setBackgroundColor(color3);
        vColor_4.setBackgroundColor(color4);
        vColor_5.setBackgroundColor(color5);
        vColor_6.setBackgroundColor(color6);



        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieChartView.OnPieClickListener() {
            @Override
            public void onPieClick(int index) {
                if (index != PieChartView.NO_SELECTED_INDEX) {
//                    txtinfo.setText(percentage[index] + "% owns "
//                            + petNames[index] + ".");
                } else {
//                    txtinfo.setText("No selected pie");
                }
            }
        });
    }

}
