package com.everi.xview.screens.activity.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.everi.xview.database.MenuDbHandler;
import com.everi.xview.database.MerchantDbHandler;
import com.everi.xview.database.TransactionTypeDbHandler;
import com.everi.xview.models.AuthenticateUserResponse;
import com.everi.xview.models.Menus;
import com.everi.xview.models.Merchant;
import com.everi.xview.models.MerchantResult;
import com.everi.xview.models.TransactionType;
import com.everi.xview.models.TransactionTypeResponse;
import com.everi.xview.networkapi.ApiServices;
import com.everi.xview.networkapi.RetrofitSetup;
import com.everi.xview.preference.AppPreferenceImpl;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.util.MessageFactory;
import com.everi.xview.util.Validations;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RSAdmin on 06-03-2017.
 */

public class LoginLogicImpl implements LoginLogicInterface{

    private LoginViewInterface viewInterface;
    private Validations validations;
    private AppPreferenceInterface preferenceInterface;
    private MessageFactory messageFactory;
    private Context context;
    private ApiServices apiServices;

    public LoginLogicImpl (Context context,LoginViewInterface viewInterface,AppPreferenceInterface preferenceInterface,
                           ApiServices apiServices,MessageFactory messageFactory,Validations validations){
        this.context = context;
        this.viewInterface = viewInterface;
        this.preferenceInterface = preferenceInterface;
        this.validations = validations;
        this.apiServices = apiServices;
        this.messageFactory = messageFactory;
    }

    private boolean validateUser(String id, String password){
        if(validations.checkForValidString(id)){
            if(validations.checkForValidString(password)){
                return true;
            } else {
                viewInterface.invalidPassword(messageFactory.blankPassword());
                return false;
            }

        } else{
            viewInterface.invalidUserId((messageFactory.blankUserId()));
            return false;
        }
    }

    @Override
    public void doUserLogin(String userId, String password) {
        if(validateUser(userId,password)){
            doLogin(userId,password);
        }
    }

    @Override
    public void initUserData() {
        String userId = preferenceInterface.getStringValue(AppPreferenceImpl.User.TAG_USER_ID.name());
        String userPwd = preferenceInterface.getStringValue(AppPreferenceImpl.User.TAG_USER_PASSWORD.name());
        if(!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(userPwd)){
            viewInterface.initUserData(userId,userPwd);
        }
    }

    private void doLogin(final String onLineId, final String pwd) {

        viewInterface.showProgress();

        Call<ResponseBody> apiCall = apiServices.doUserAuthentication(onLineId, pwd);
        apiCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Gson gson = new Gson();
                AuthenticateUserResponse userResult = null;
                try {

                    String raw = response.body().string();
                    raw = raw.substring(1, raw.lastIndexOf('"'));
                    raw = raw.replaceAll("\\\\", "");
                    Log.d("RESPONSE ", raw);
                    userResult = gson.fromJson(raw, AuthenticateUserResponse.class);
                    if (userResult != null) {
                        if (userResult.getResponseStatus().get(0).getStatus().equalsIgnoreCase("Success")) {

                            JsonElement element = gson.toJsonTree(userResult.getMenus(), new TypeToken<List<Menus>>() {
                            }.getType());
                            JsonArray jsonArray = element.getAsJsonArray();
                            String val = jsonArray.toString();

                            preferenceInterface.saveStringValue(AppPreferenceImpl.MenuStorage.TAG_MENU.name(),val);
                            preferenceInterface.saveStringValue(AppPreferenceImpl.User.TAG_USER_ID.name(),onLineId);
                            preferenceInterface.saveStringValue(AppPreferenceImpl.User.TAG_USER_PASSWORD.name(),pwd);

                            getTransactionTypes();
                            getMerchantList();
                            viewInterface.successfullyLoggedIn();
                        } else {
                            viewInterface.errorLoggedIn(userResult.getResponseStatus().get(0).getResponseMessage());
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                viewInterface.errorLoggedIn(messageFactory.somethingWentWrongError());

            }
        });
    }



    private void getTransactionTypes() {

        Call<ResponseBody> apiCall = apiServices.getTransactionType();
        apiCall.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Gson gson = new Gson();
                TransactionTypeResponse transactionTypeResponse = null;
                try {
                    String raw = response.body().string();
                    raw = raw.substring(1, raw.lastIndexOf('"'));
                    raw = raw.replaceAll("\\\\", "");
                    Log.d("RESPONSE ", raw);

                    transactionTypeResponse = gson.fromJson(raw, TransactionTypeResponse.class);
                    if (transactionTypeResponse.getTransactionType() != null) {
                        insertTransactionTypeIntoDB(transactionTypeResponse.getTransactionType());
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                viewInterface.errorLoggedIn(messageFactory.somethingWentWrongError());
            }
        });
    }



    private void getMerchantList() {

        Call<ResponseBody> apiCall = apiServices.getMerchantList("1000108875");
        apiCall.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Gson gson = new Gson();
                MerchantResult merchantResult = null;
                try {
                    String raw = response.body().string();
                    raw = raw.substring(1, raw.lastIndexOf('"'));
                    raw = raw.replaceAll("\\\\", "");
                    Log.d("RESPONSE ", raw);

                    merchantResult = gson.fromJson(raw, MerchantResult.class);
                    if (merchantResult.getMerchant() != null) {
                        insertMerchantsIntoDB(merchantResult.getMerchant());
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                viewInterface.errorLoggedIn(messageFactory.somethingWentWrongError());
            }
        });
    }

    private void insertMenuIntoDB(List<Menus> menusList) {
        MenuDbHandler dbHandler = new MenuDbHandler(context);
        dbHandler.insertOrUpdateMenu(menusList);
    }

    private void insertTransactionTypeIntoDB(List<TransactionType> transactionTypes) {
        TransactionTypeDbHandler dbHandler = new TransactionTypeDbHandler(context);
        dbHandler.insertOrUpdateTransactionType(transactionTypes);
    }

    private void insertMerchantsIntoDB(List<Merchant> merchantList) {
        MerchantDbHandler dbHandler = new MerchantDbHandler(context);
        dbHandler.insertOrUpdateMerchant(merchantList);
    }


}
