package com.everi.xview.networkapi;

import com.everi.xview.models.ExampleModel;
import com.everi.xview.models.TransactionType;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sukamal on 21/2/17.
 */

public interface ApiServices {

    @POST("/XViewMobileWCFService/MobileService.svc/MobileData/AuthenticateUser")
    Call<ResponseBody> doUserAuthentication(@Query("OnlineID") String onlineid,@Query("PIN") String pin);

    @POST("/XViewMobileWCFService/MobileService.svc/MobileData/TransactionTypes")
    Call<ResponseBody> getTransactionType();

    @POST("/XViewMobileWCFService/MobileService.svc/MobileData/MerchantList")
    Call<ResponseBody> getMerchantList(@Query("UserGroupSID") String userGroupSID);

    @POST("/XViewMobileWCFService/MobileService.svc/MobileData/TransactionList")
    Call<ResponseBody> getTransactionList(@Query("UserGroupSID") String userGroupSID,
                                          @Query("Merchant") String merchant,
                                          @Query("TransactionType") String transactionType,
                                          @Query("FromDate") String fromDate,
                                          @Query("ToDate") String toDate,
                                          @Query("PageIndex") String pageIndex,
                                          @Query("PageSize") String pageSize);

    @POST("/XViewMobileWCFService/MobileService.svc/MobileData/TransactionDetail")
    Call<ResponseBody> getTransactionDetail(@Query("TransactionHistoryUID") String transactionHistoryUID);

}
