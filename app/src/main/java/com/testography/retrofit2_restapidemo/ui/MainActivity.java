package com.testography.retrofit2_restapidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.testography.retrofit2_restapidemo.R;
import com.testography.retrofit2_restapidemo.controller.RestManager;
import com.testography.retrofit2_restapidemo.model.Flower;
import com.testography.retrofit2_restapidemo.model.adapter.FlowerAdapter;
import com.testography.retrofit2_restapidemo.model.helper.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FlowerAdapter.FlowerClickListener {

    RecyclerView mRecyclerView;
    private RestManager mManager;
    private FlowerAdapter mFlowerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configViews();
        mManager = new RestManager();

        Call<List<Flower>> listCall = mManager.getFlowerService().getAllFlowers();
        listCall.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (response.isSuccessful()) {
                    List<Flower> flowerList = response.body();

                    for (int i = 0; i < flowerList.size(); i++) {
                        Flower flower = flowerList.get(i);
                        mFlowerAdapter.addFlower(flower);
                    }
                } else {
                    int statusCode = response.code();
                    switch (statusCode) {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });
    }

    private void configViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager
                (getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mFlowerAdapter = new FlowerAdapter(this);
        mRecyclerView.setAdapter(mFlowerAdapter);
    }

    @Override
    public void onClick(int position) {
        Flower selectedFlower = mFlowerAdapter.getSelectedFlower(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constants.REFERENCE.FLOWER, selectedFlower);
        startActivity(intent);
    }
}
