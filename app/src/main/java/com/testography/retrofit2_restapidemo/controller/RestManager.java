package com.testography.retrofit2_restapidemo.controller;

import com.testography.retrofit2_restapidemo.model.callback.FlowerService;
import com.testography.retrofit2_restapidemo.model.helper.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestManager {

    private FlowerService mFlowerService;

    public FlowerService getFlowerService() {
        if (mFlowerService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mFlowerService = retrofit.create(FlowerService.class);
        }
        return mFlowerService;
    }

}
