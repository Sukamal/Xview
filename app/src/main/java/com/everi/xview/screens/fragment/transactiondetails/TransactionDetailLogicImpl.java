package com.everi.xview.screens.fragment.transactiondetails;

import android.content.Context;
import android.util.Log;

import com.everi.xview.models.TransactionDetailsResponse;
import com.everi.xview.networkapi.ApiServices;
import com.everi.xview.util.MessageFactory;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RSAdmin on 09-03-2017.
 */

public class TransactionDetailLogicImpl implements TransactionDetailLogicInterface{

    private Context context;
    private TransactionDetailViewInterface viewInterface;
    private ApiServices apiServices;
    private MessageFactory messageFactory;


    public TransactionDetailLogicImpl(Context context, TransactionDetailViewInterface viewInterface, ApiServices apiServices, MessageFactory messageFactory ){

        this.context = context;
        this.viewInterface = viewInterface;
        this.apiServices = apiServices;
        this.messageFactory = messageFactory;
    }

    @Override
    public void displayTransactionDetails(String transactionHistoryUID) {
        getTransactionDetailst(transactionHistoryUID);
    }


    private void getTransactionDetailst(String transactionHistoryUID) {

        Call<ResponseBody> apiCall = apiServices.getTransactionDetail(transactionHistoryUID);
        apiCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Gson gson = new Gson();
                TransactionDetailsResponse detailsResponse = null;
                try {

                    String raw = response.body().string();
                    raw = raw.substring(1, raw.lastIndexOf('"'));
                    raw = raw.replaceAll("\\\\", "");
                    Log.d("RESPONSE ", raw);
                    detailsResponse = gson.fromJson(raw, TransactionDetailsResponse.class);

                    if (detailsResponse != null) {
                        if (detailsResponse.getResponseStatus().get(0).getStatus().equalsIgnoreCase("Success")) {

                            viewInterface.displayTransactionDetails(detailsResponse.getTransactionDetail());
                        } else {
                            viewInterface.errorInData(detailsResponse.getResponseStatus().get(0).getResponseMessage());
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
