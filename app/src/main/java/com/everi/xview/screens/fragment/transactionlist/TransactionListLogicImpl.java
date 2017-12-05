package com.everi.xview.screens.fragment.transactionlist;

import android.content.Context;
import android.util.Log;

import com.everi.xview.models.AuthenticateUserResponse;
import com.everi.xview.models.Menus;
import com.everi.xview.models.TransactionDetail;
import com.everi.xview.models.TransactionListResponse;
import com.everi.xview.networkapi.ApiServices;
import com.everi.xview.networkapi.RetrofitSetup;
import com.everi.xview.preference.AppPreferenceImpl;
import com.everi.xview.util.MessageFactory;
import com.everi.xview.util.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RSAdmin on 07-03-2017.
 */

public class TransactionListLogicImpl implements TransactionListLogicInterface{

    private TransactionListViewInterface viewInterface;
    private MessageFactory messageFactory;
    private Context context;
    private ApiServices apiServices;

    public TransactionListLogicImpl(Context context,TransactionListViewInterface viewInterface,ApiServices apiServices, MessageFactory messageFactory){
        this.context = context;
        this.viewInterface = viewInterface;
        this.apiServices = apiServices;
        this.messageFactory = messageFactory;
    }

    @Override
    public void displayTransactionList(String userGroupSID,String merchant,String transactionType,String fromDate,String toDate,String pageIndex,String pageSize) {


//        List<TransactionDetail> transactionDetailList = new ArrayList<>();
//
//        for(int i = 0; i < 5; i++){
//            TransactionDetail transactionDetail = new TransactionDetail();
//            transactionDetail.terminalId = "NV000A " + (i+1);
//            transactionDetail.dateTime = "02/13/2017 05:00:59 PM PST";
//            transactionDetail.amount = "$100.00";
//            transactionDetail.type = "Withdrawal";
//            transactionDetail.status = "COMPLETED";
//            transactionDetailList.add(transactionDetail);
//        }
//
//        for(int i = 5; i < 10; i++){
//            TransactionDetail transactionDetail = new TransactionDetail();
//            transactionDetail.terminalId = "NV000B " + (i+1);
//            transactionDetail.dateTime = "02/13/2017 05:00:59 PM PST";
//            transactionDetail.amount = "$100.00";
//            transactionDetail.type = "Deposit";
//            transactionDetail.status = "DECLINED";
//            transactionDetailList.add(transactionDetail);
//        }
//
//        for(int i = 10; i < 15; i++){
//            TransactionDetail transactionDetail = new TransactionDetail();
//            transactionDetail.terminalId = "NV000C " + (i+1);
//            transactionDetail.dateTime = "02/13/2017 05:00:59 PM PST";
//            transactionDetail.amount = "$100.00";
//            transactionDetail.type = "Withdrawal";
//            transactionDetail.status = "DENIED";
//            transactionDetailList.add(transactionDetail);
//        }
//
//        for(int i = 15; i < 20; i++){
//            TransactionDetail transactionDetail = new TransactionDetail();
//            transactionDetail.terminalId = "NV000D " + (i+1);
//            transactionDetail.dateTime = "02/13/2017 05:00:59 PM PST";
//            transactionDetail.amount = "$100.00";
//            transactionDetail.type = "Deposit";
//            transactionDetail.status = "VOIDED";
//            transactionDetailList.add(transactionDetail);
//        }
//
//        viewInterface.displayTransactionList(transactionDetailList);

        if(Utility.isNullOrBlank(userGroupSID)){
            userGroupSID = "0";
        }
        if(Utility.isNullOrBlank(merchant)){
            merchant = "all";
        }
        if(Utility.isNullOrBlank(transactionType)){
            transactionType = "all";
        }
        if(Utility.isNullOrBlank(fromDate)){
            fromDate = "";
        }
        if(Utility.isNullOrBlank(toDate)){
            toDate = "";
        }
        if(Utility.isNullOrBlank(pageIndex)){
            pageIndex = "1";
        }
        if(Utility.isNullOrBlank(pageSize)){
            pageSize = "20";
        }

        getTransactionList(userGroupSID,merchant,transactionType,fromDate,toDate,pageIndex,pageSize);
    }


    private void getTransactionList(String userGroupSID,String merchant,String transactionType,String fromDate,String toDate,String pageIndex,String pageSize) {

        Call<ResponseBody> apiCall = apiServices.getTransactionList(userGroupSID, merchant,transactionType,fromDate,toDate,pageIndex,pageSize);
        apiCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Gson gson = new Gson();
                TransactionListResponse listResponse = null;
                try {

                    String raw = response.body().string();
                    raw = raw.substring(1, raw.lastIndexOf('"'));
                    raw = raw.replaceAll("\\\\", "");
                    Log.d("RESPONSE ", raw);
                    listResponse = gson.fromJson(raw, TransactionListResponse.class);

                    if (listResponse != null) {
                        if (listResponse.getResponseStatus().get(0).getStatus().equalsIgnoreCase("Success")) {

                            viewInterface.displayTransactionList(listResponse.getTransactionDetail());
                        } else {
                            viewInterface.errorInData(listResponse.getResponseStatus().get(0).getResponseMessage());
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                viewInterface.errorInData(messageFactory.somethingWentWrongError());

            }
        });
    }
}
