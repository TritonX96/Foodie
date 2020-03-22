package com.isaac.foodie;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RestaurantInfoActivity extends AppCompatActivity{

    TextView iRestaurantName, iRestaurantCategory, iRestaurantAddress, iRestaurantLocation;
    private static final String TAG = RestaurantInfoActivity.class.getSimpleName();
    DatabaseReference mReference;
    RestaurantAdapter mRestaurantAdapter;
    ArrayList<RestaurantDetails> mRestaurantDetailsArrayList;
    RecyclerView restaurantInfoView;
    private String mPost_key = null;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);
        Log.d(TAG, "onCreate: started.");


        String post_key = getIntent().getExtras().getString("post_id");

        iRestaurantName = (TextView) findViewById(R.id.restaurantName);
        iRestaurantAddress = (TextView) findViewById(R.id.restaurantAddress);
        iRestaurantCategory = (TextView) findViewById(R.id.restaurantCategory);
        iRestaurantLocation = (TextView) findViewById(R.id.restaurantLocation);


        //Toast.makeText(RestaurantInfoActivity.this, post_key,Toast.LENGTH_LONG).show();
        //Log.d(TAG,"onClick" + post_key );

        mReference.child(mPost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String info_Name = (String) dataSnapshot.child("name").getValue();
                String info_Address = (String) dataSnapshot.child("address").getValue();
                String info_Category = (String) dataSnapshot.child("category").getValue();
                String info_Location = (String) dataSnapshot.child("location").getValue();

                iRestaurantName.setText(info_Name);
                iRestaurantAddress.setText(info_Address);
                iRestaurantCategory.setText(info_Category);
                iRestaurantLocation.setText(info_Location);

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    RestaurantDetails r = dataSnapshot1.getValue(RestaurantDetails.class);
                    mRestaurantDetailsArrayList.add(r);
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //handle onBackPressed to previous activity
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
