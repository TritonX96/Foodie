package com.isaac.foodie;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ResRecyclerViewActivity extends AppCompatActivity {

    DatabaseReference mReference;
    RecyclerView restaurantRecyclerView;
    RestaurantAdapter mRestaurantAdapter;
    //List <RestaurantDetails> mRestaurant;
    ArrayList<RestaurantDetails> mRestaurantDetailsArrayList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantlist);

        //ini view

        restaurantRecyclerView = findViewById(R.id.restaurant_rv);
        mRestaurantDetailsArrayList = new ArrayList<>();


        //mRestaurantAdapter = new RestaurantAdapter(this,mRestaurant);
        //restaurantRecyclerView.setAdapter(mRestaurantAdapter);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mReference=FirebaseDatabase.getInstance().getReference().child("Restaurant");
        mReference.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    RestaurantDetails r = dataSnapshot1.getValue(RestaurantDetails.class);
                    mRestaurantDetailsArrayList.add(r);
                }
                mRestaurantAdapter = new RestaurantAdapter(ResRecyclerViewActivity.this,mRestaurantDetailsArrayList);
                restaurantRecyclerView.setAdapter(mRestaurantAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ResRecyclerViewActivity.this,"Error",Toast.LENGTH_SHORT);
            }
        }));
    }


}
