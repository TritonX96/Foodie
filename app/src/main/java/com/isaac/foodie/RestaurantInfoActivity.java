package com.isaac.foodie;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.isaac.foodie.Model.RestaurantDetails;
import com.squareup.picasso.Picasso;


public class RestaurantInfoActivity extends AppCompatActivity{

    TextView iRestaurantName, iRestaurantCategory, iRestaurantAddress, iRestaurantLocation;
    ImageView iRestaurantImage;
    private Button coordinateButton;
    private static final String TAG = RestaurantInfoActivity.class.getSimpleName();
    DatabaseReference mReference;


    //Retrieving data from the recyclerview to info activity using the parent key from Firebase

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);
        Log.d(TAG, "onCreate: started.");

        FirebaseRecyclerOptions<RestaurantDetails> options =
                new FirebaseRecyclerOptions.Builder<RestaurantDetails>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Info"), RestaurantDetails.class)
                        .build();

        iRestaurantName = (TextView) findViewById(R.id.restaurantName);
        iRestaurantAddress = (TextView) findViewById(R.id.restaurantAddress);
        iRestaurantCategory = (TextView) findViewById(R.id.restaurantCategory);
        iRestaurantLocation = (TextView) findViewById(R.id.restaurantLocation);
        iRestaurantImage =  (ImageView) findViewById(R.id.restaurantImage);

        coordinateButton = (Button) findViewById(R.id.restaurantCoordinates);


        final String post_key = getIntent().getExtras().get("post_key").toString();


        mReference = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Info");
        mReference.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                //RestaurantDetails details = dataSnapshot.getValue(RestaurantDetails.class);
                if(dataSnapshot.exists()){
                    String nameRestaurant = dataSnapshot.child("name").getValue().toString();
                    String locationRestaurant = dataSnapshot.child("location").getValue().toString();
                    String addressRestaurant = dataSnapshot.child("address").getValue().toString();
                    String categoryRestaurant = dataSnapshot.child("category").getValue().toString();
                    String imageRestaurant = dataSnapshot.child("image").getValue().toString();


                    Picasso.get().load(imageRestaurant).into(iRestaurantImage);
                    iRestaurantName.setText(nameRestaurant);
                    iRestaurantLocation.setText(locationRestaurant);
                    iRestaurantAddress.setText(addressRestaurant);
                    iRestaurantCategory.setText(categoryRestaurant);


                    //When user clicked, this will bring to the map activity
                    coordinateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String lat = dataSnapshot.child("latitude").getValue().toString();
                            String lng = dataSnapshot.child("longitude").getValue().toString();

                            Intent mapIntent = new Intent(RestaurantInfoActivity.this, ClickMapActivity.class);
                            mapIntent.putExtra("latitude",lat);
                            mapIntent.putExtra("longitude",lng);
                            startActivity(mapIntent);
                        }
                    });
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
