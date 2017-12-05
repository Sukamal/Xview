package com.everi.xview.screens.activity.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.everi.xview.R;
import com.everi.xview.XviewApplication;
import com.everi.xview.di.modules.LoginDIModule;
import com.everi.xview.screens.activity.BaseActivity;
import com.everi.xview.screens.activity.main.MainActivity;
import com.everi.xview.screens.activity.settings.SettingsActivity;
import com.everi.xview.util.AppDialog;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RSAdmin on 06-03-2017.
 */

public class LoginActivity extends BaseActivity implements LoginViewInterface{

    @Inject
    LoginLogicInterface logicInterface;

    /*@Inject
    AppPreferenceInterface appPreferenceInterface;
    @Inject
    ApiServices apiServices;
    @Inject
    MessageFactory messageFactory;
    @Inject
    Validations validations;*/

    @Bind(R.id.et_online_id)
    EditText etOnlineId;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    Dialog mDialog;

//    private LoginLogicImpl loginLogic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeUiComponents();
    }

    private void initializeUiComponents() {
        ButterKnife.bind(this);
        ((XviewApplication) getApplication()).getComponent().getLoginComponent(new LoginDIModule(LoginActivity.this,this)).inject(this);

//        ((XviewApplication) getApplication()).getComponent().inject(this);
//        loginLogic = new LoginLogicImpl(this,this,appPreferenceInterface,apiServices,messageFactory,validations);
//        loginLogic.initUserData();
    }

    @OnClick(R.id.btn_login)
    void onLoginBtnClick() {
//        loginLogic.doUserLogin(etOnlineId.getText().toString().trim(),etPassword.getText().toString().trim());
        logicInterface.doUserLogin(etOnlineId.getText().toString().trim(),etPassword.getText().toString().trim());
    }

    @OnClick(R.id.tv_forgot_password)
    void onForgotpasswordClick() {

    }

    @OnClick(R.id.iv_settings)
    void onSettingsClick(){

        startActivity(new Intent(LoginActivity.this, SettingsActivity.class));
    }

    @Override
    public void initUserData(String userId, String pwd) {
        etOnlineId.setText(userId);
        etPassword.setText(pwd);
    }

    @Override
    public void invalidUserId(String message) {
        etOnlineId.requestFocus();
        etOnlineId.setError(message);
    }

    @Override
    public void invalidPassword(String message) {
        etPassword.requestFocus();
        etPassword.setError(message);
    }

    @Override
    public void successfullyLoggedIn() {
        hideProgress();
        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void errorLoggedIn(String message) {
        hideProgress();
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProgress() {

        mDialog = AppDialog.showProgressDialog(LoginActivity.this);
    }

    @Override
    public void hideProgress() {
        if(mDialog != null){
            mDialog.dismiss();
        }

    }


   /* private void doLogin(String onLineId, String pwd) {

        final RetrofitSetup retrofitSetup = new RetrofitSetup();
        ApiServices apiServices = retrofitSetup.getService();

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

                            AppPreference appPreference = new AppPreference(LoginActivity.this);
                            appPreference.saveStringValue(AppPreference.MenuStorage.TAG_MENU.name(), val);
                            getTransactionTypes();
                            getMerchantList();
                            finish();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, userResult.getResponseStatus().get(0).getResponseMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erroorr", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void insertMenuIntoDB(List<Menus> menusList) {
        MenuDbHandler dbHandler = new MenuDbHandler(LoginActivity.this);
        dbHandler.insertOrUpdateMenu(menusList);
    }

    private void getTransactionTypes() {
        final RetrofitSetup retrofitSetup = new RetrofitSetup();
        ApiServices apiServices = retrofitSetup.getService();

        Call<ResponseBody> apiCall = apiServices.getTransactionType();
        apiCall.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Gson gson = new Gson();
                TransactionTypeResponse transactionTypeResult = null;
                try {
                    String raw = response.body().string();
                    raw = raw.substring(1, raw.lastIndexOf('"'));
                    raw = raw.replaceAll("\\\\", "");
                    Log.d("RESPONSE ", raw);

                    transactionTypeResult = gson.fromJson(raw, TransactionTypeResponse.class);
                    if (transactionTypeResult.getTransactionType() != null) {
                        insertTransactionTypeIntoDB(transactionTypeResult.getTransactionType());
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void insertTransactionTypeIntoDB(List<TransactionType> transactionTypes) {
        TransactionTypeDbHandler dbHandler = new TransactionTypeDbHandler(LoginActivity.this);
        dbHandler.insertOrUpdateTransactionType(transactionTypes);
    }

    private void getMerchantList() {
        final RetrofitSetup retrofitSetup = new RetrofitSetup();
        ApiServices apiServices = retrofitSetup.getService();

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

            }
        });
    }

    private void insertMerchantsIntoDB(List<Merchant> merchantList) {
        MerchantDbHandler dbHandler = new MerchantDbHandler(LoginActivity.this);
        dbHandler.insertOrUpdateMerchant(merchantList);
    }*/


}
