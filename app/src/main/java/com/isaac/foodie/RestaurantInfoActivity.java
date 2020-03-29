package com.isaac.foodie;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;


import com.firebase.ui.database.FirebaseRecyclerOptions;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);
        Log.d(TAG, "onCreate: started.");

        FirebaseRecyclerOptions<RestaurantDetails> options =
                new FirebaseRecyclerOptions.Builder<RestaurantDetails>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant"), RestaurantDetails.class)
                        .build();

        //Toast.makeText(RestaurantInfoActivity.this, post_key,Toast.LENGTH_LONG).show();
        //Log.d(TAG,"onClick" + post_key );



        mReference = FirebaseDatabase.getInstance().getReference().child("Restaurant");

        final String post_key = getIntent().getExtras().get("post_key").toString();

        iRestaurantName = (TextView) findViewById(R.id.restaurantName);
        iRestaurantAddress = (TextView) findViewById(R.id.restaurantAddress);
        iRestaurantCategory = (TextView) findViewById(R.id.restaurantCategory);
        iRestaurantLocation = (TextView) findViewById(R.id.restaurantLocation);

        mReference.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                RestaurantDetails details = dataSnapshot.getValue(RestaurantDetails.class);
                if(dataSnapshot.exists()){
                    String nameRestaurant = dataSnapshot.child("name").getValue().toString();
                    String locationRestaurant = dataSnapshot.child("location").getValue().toString();
                    String addressRestaurant = dataSnapshot.child("address").getValue().toString();
                    String categoryRestaurant = dataSnapshot.child("category").getValue().toString();
//                    String locationRestaurant = dataSnapshot.child("location").getValue().toString();
//                    String addressRestaurant = dataSnapshot.child("address").getValue().toString();
//                    String categoryRestaurant = dataSnapshot.child("category").getValue().toString();

                    iRestaurantName.setText(nameRestaurant);
                    iRestaurantLocation.setText(locationRestaurant);
                    iRestaurantAddress.setText(addressRestaurant);
                    iRestaurantCategory.setText(categoryRestaurant);


                }


//                String nameRestaurant = dataSnapshot.getValue();
//                RestaurantDetails locationRestaurant = dataSnapshot.child("location").getValue(RestaurantDetails.class);
//                RestaurantDetails addressRestaurant = dataSnapshot.child("address").getValue(RestaurantDetails.class);
//                RestaurantDetails categoryRestaurant = dataSnapshot.child("category").getValue(RestaurantDetails.class);


//                iRestaurantName.setText(details.getName());
//                iRestaurantAddress.setText(details.getAddress());
//                iRestaurantCategory.setText(details.getCategory());
//                iRestaurantLocation.setText(details.getLocation());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

//    private void RetrieveRestaurantInfo() {
//
//        mReference.child(post_key).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                dataSnapshot.getKey();
//
//                RestaurantDetails nameRestaurant = dataSnapshot.getValue(RestaurantDetails.class);
//                RestaurantDetails locationRestaurant = dataSnapshot.getValue(RestaurantDetails.class);
//                RestaurantDetails addressRestaurant = dataSnapshot.getValue(RestaurantDetails.class);
//                RestaurantDetails categoryRestaurant = dataSnapshot.getValue(RestaurantDetails.class);
//
//                iRestaurantName.setText(nameRestaurant);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }


    //handle onBackPressed to previous activity
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
