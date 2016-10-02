package com.testography.retrofit2_restapidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.testography.retrofit2_restapidemo.R;
import com.testography.retrofit2_restapidemo.model.Flower;
import com.testography.retrofit2_restapidemo.model.helper.Constants;

public class DetailActivity extends AppCompatActivity {

    private TextView mName, mId, mCategory, mInstruction;
    private ImageView mPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Flower flower = (Flower) intent.getSerializableExtra(Constants.REFERENCE.FLOWER);

        configViews();

        mName.setText(flower.getName());
        mId.setText("" + flower.getProductId());
        mCategory.setText(flower.getCategory());
        mInstruction.setText(flower.getInstructions());

        Picasso.with(getApplicationContext())
                .load("http://services.hanselandpetal.com/photos/" + flower.getPhoto())
                .into(mPhoto);
    }

    private void configViews() {
        mName = (TextView) findViewById(R.id.flowerName);
        mId = (TextView) findViewById(R.id.flowerId);
        mCategory = (TextView) findViewById(R.id.flowerCategory);
        mInstruction = (TextView) findViewById(R.id.flowerInstruction);
        mPhoto = (ImageView) findViewById(R.id.flowerPhoto);
    }
}
