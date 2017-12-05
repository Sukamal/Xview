package com.everi.xview.screens.fragment.transactionlist;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.everi.xview.R;
import com.everi.xview.XviewApplication;
import com.everi.xview.adapter.TransactionListAdapter;
import com.everi.xview.models.TransactionDetail;
import com.everi.xview.networkapi.ApiServices;
import com.everi.xview.preference.AppPreferenceImpl;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.screens.fragment.transactiondetails.TransactionDetailsFragment;
import com.everi.xview.screens.fragment.TransactionFilterFragment;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.MessageFactory;
import com.everi.xview.util.Utility;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TransactionListFragment extends Fragment implements TransactionListViewInterface{

    @Inject
    AppPreferenceInterface appPreferenceInterface;
    @Inject
    ApiServices apiServices;
    @Inject
    MessageFactory messageFactory;

    private MainActivity mainActivity;
    private LinearLayoutManager mLayoutManager;
    private View mRootView;
    public static TransactionDetail selectedModel;
    private TransactionListLogicImpl listLogic;

    @Bind(R.id.rv_transactionlist)
    RecyclerView rvTransactionlist;

    @Bind(R.id.tv_filter_transaction)
    TextView tvFilterTransaction;

    @Bind(R.id.tv_reset_all_filter) TextView tvResetAllFilter;



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public TransactionListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        ((XviewApplication) getActivity().getApplication()).getComponent().inject(this);
        this.setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_transaction_list, container, false);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle();
        initializeUiComponents();
        setRequiredEvents();
        listLogic = new TransactionListLogicImpl(getActivity(),this,apiServices,messageFactory);
        getTransactionList();
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

    private void getTransactionList(){
        String userGroupSID = "0";
        String merchant = appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_MERCHANT.name());
        String transactionType = appPreferenceInterface.getStringValue(AppPreferenceImpl.TransactionFilterTag.TAG_TYPE.name());
        String fromDate = "";
        String toDate = "";
        String pageIndex = "1";
        String pageSize = "20";
        listLogic.displayTransactionList(userGroupSID,merchant,transactionType,fromDate,toDate,pageIndex,pageSize);
    }

    @Override
    public void displayTransactionList(List<TransactionDetail> transactionDetailList) {

        TransactionListAdapter transactionListAdapter = new TransactionListAdapter(getActivity(), transactionDetailList,TransactionListAdapter.VIEW_TYPE.TYPE_TRANSACTION.getEnumValue(), new TransactionListAdapter.OnListItemClickListener() {
            @Override
            public void onListItemClick(View view, int position, Object selectedItem) {

                TransactionDetail transactionDetail = (TransactionDetail)selectedItem;
                selectedModel = transactionDetail;

                HashMap<String,String> bundleData = new HashMap<>();
                bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Transaction Details");
                bundleData.put(AppConstant.ExtraTag.TAG_TRANSACTION_UID.getEnumValue(),transactionDetail.getTransactionHistoryUID());

                Utility.startFragmentInContainer1(getActivity(),TransactionDetailsFragment.class.getName(),true,bundleData);

            }
        });

        rvTransactionlist.setAdapter(transactionListAdapter);
    }

    @Override
    public void errorInData(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.tv_filter_transaction)
    void onFilterClicked(){
        resetAllFilterOption();
        HashMap<String,String> bundleData = new HashMap<>();
        bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Transactions");
        Utility.startFragmentInContainer1(getActivity(),TransactionFilterFragment.class.getName(),true,bundleData);

    }

    @OnClick(R.id.tv_reset_all_filter)
    void resetAllFilterData(){
        resetAllFilterOption();
    }



    private void setTitle(){
        if(getArguments() != null && getArguments().containsKey(AppConstant.ExtraTag.TAG_HEADING.getEnumValue())){
            String heading = getArguments().getString(AppConstant.ExtraTag.TAG_HEADING.getEnumValue());
            mainActivity.setToolbarTitle(heading);
        }
    }


    private void initializeUiComponents(){
        mLayoutManager = new LinearLayoutManager(getActivity());
        rvTransactionlist.setLayoutManager(mLayoutManager);
    }

    private void setRequiredEvents(){

    }


    private void resetAllFilterOption(){
        for(AppPreferenceImpl.TransactionFilterTag filter : AppPreferenceImpl.TransactionFilterTag.values()){
            appPreferenceInterface.clearPreferences(filter.name());
        }
    }

}
