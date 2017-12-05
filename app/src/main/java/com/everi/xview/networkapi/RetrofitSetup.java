package com.everi.xview.networkapi;

import android.content.Context;

import com.everi.xview.AppConfigData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sukamal on 21/2/17.
 */

public class RetrofitSetup {

    private Retrofit mRetrofit;


    public ApiServices getService(Context context) {

        AppConfigData appConfig = AppConfigData.getInstance(context);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(appConfig.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return mRetrofit.create(ApiServices.class);
    }

    public Retrofit getRetrofit(){return mRetrofit;}
}
