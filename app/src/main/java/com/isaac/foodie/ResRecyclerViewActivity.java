package com.isaac.foodie;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Log;

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


public class ResRecyclerViewActivity extends AppCompatActivity implements RestaurantAdapter.OnRestaurantListener {

    private static final String TAG = ResRecyclerViewActivity.class.getSimpleName();
    DatabaseReference mReference;
    RecyclerView restaurantRecyclerView;
    RestaurantAdapter mRestaurantAdapter;
    //List <RestaurantDetails> mRestaurant;
    ArrayList<RestaurantDetails> mRestaurantDetailsArrayList;
    //String url = intent.getExtras().getString("intent");

    private void openInfoActivity(String[] info){
        Intent intent = new Intent(this, RestaurantInfoActivity.class);
        intent.putExtra("NAME_KEY",info[0]);
        intent.putExtra("CATEGORY_KEY",info[1]);
        intent.putExtra("LOCATION_KEY",info[2]);
        startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantlist);
        Log.d(TAG, "onCreate: started.");

        //ini view
        restaurantRecyclerView = findViewById(R.id.restaurant_rv);
        mRestaurantDetailsArrayList = new ArrayList<>();
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRestaurantAdapter = new RestaurantAdapter (ResRecyclerViewActivity.this,mRestaurantDetailsArrayList);
        restaurantRecyclerView.setAdapter(mRestaurantAdapter);
        //mRestaurantAdapter.setOnRestaurantListener(ResRecyclerViewActivity.this); saveButton but KIV


        mReference=FirebaseDatabase.getInstance().getReference().child("Restaurant");
        //https://stackoverflow.com/questions/47367801/is-it-better-to-pass-more-information-in-an-intent-to-an-activity-or-call-the-da
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
                Toast.makeText(ResRecyclerViewActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        }));


    }
    @Override
    public void onRestaurantClick(int position) {
        RestaurantDetails clickedRestaurant = mRestaurantDetailsArrayList.get(position);
        String [] restaurantData = {clickedRestaurant.getName(),clickedRestaurant.getCategory(),clickedRestaurant.getLocation()};
        openInfoActivity(restaurantData);
        Toast. makeText(getApplicationContext(),"This is clicked",Toast. LENGTH_SHORT).show();
        Log.d(TAG,"This is clicked");
    }
}
