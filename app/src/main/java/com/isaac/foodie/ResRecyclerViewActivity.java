package com.isaac.foodie;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class ResRecyclerViewActivity extends AppCompatActivity {

    RecyclerView restaurantRecyclerView;
    RestaurantAdapter mRestaurantAdapter;
    List <RestaurantDetails> mData;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantlist);

        //ini view

        restaurantRecyclerView = findViewById(R.id.restaurant_rv);
        mData = new ArrayList<>();
        // fill list restaurant with data
        // testing purposes

        mData.add(new RestaurantDetails("Creamery Boutique Ice Creams KL","Sunway City","B-02-08, Sunway Geo Avenue Jalan Lagoon Selatan Sunway South Quay, Bandar Sunway, 47500 Subang Jaya, Selangor","Dessert","3.064696","101.610165"));
        mData.add(new RestaurantDetails("Okra Nyonya","Sunway City","B-02-09, Sunway Geo Avenue Jalan Lagoon Selatan Sunway South Quay, Bandar Sunway, 47500 Subang Jaya, Selangor","Dessert","3.064600","101.609989"));
        mData.add(new RestaurantDetails("Haidilao Hot Pot @Sunway Pyramid","Sunway City","G1.PT.02 Sunway Pyramid, 3, Jalan PJS 11/15, Bandar Sunway, 47500 Subang Jaya, Selangor","Hot Pot Restaurant","3.072201","101.608217"));
        mData.add(new RestaurantDetails("After Black","Sunway City","23, Jalan PJS 11/9, Bandar Sunway, 46150 Petaling Jaya, Selangor","Cafe","3.067979","101.603196"));


        mRestaurantAdapter = new RestaurantAdapter(this,mData);
        restaurantRecyclerView.setAdapter(mRestaurantAdapter);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
