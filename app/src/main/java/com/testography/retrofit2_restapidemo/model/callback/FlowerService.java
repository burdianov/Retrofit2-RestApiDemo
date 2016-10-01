package com.testography.retrofit2_restapidemo.model.callback;

import com.testography.retrofit2_restapidemo.model.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlowerService {

    @GET("/feeds/flowers.json")
    Call<List<Flower>> getAllFlowers();
}
