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


public class RestaurantInfoActivity extends AppCompatActivity{

    TextView iRestaurantName, iRestaurantCategory, iRestaurantAddress, iRestaurantLocation;
    private Toolbar toolbar;
    private static final String TAG = RestaurantInfoActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);
        Log.d(TAG, "onCreate: started.");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //Action bar
        //ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        //actionBar.setTitle("Restaurant Detail");
        //set back button in action bar
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);

        iRestaurantName = findViewById(R.id.restaurantName);
        iRestaurantCategory = findViewById(R.id.restaurantCategory);
        iRestaurantLocation = findViewById(R.id.restaurantLocation);
        //iRestaurantAddress = findViewById(R.id.restaurantAddress);

        //Get Intent
        Intent i = this.getIntent();

        //Receive Data
        String name = i.getExtras().getString("NAME_KEY");
        Log.d(TAG,"The name is " + name);
        String category = i.getExtras().getString("CATEGORY_KEY");
        //Log.i(category, "This is category");
        String location = i.getExtras().getString("LOCATION_KEY");
        //Log.i(location, "This is location");

        //Bind data
        iRestaurantName.setText(name);
        iRestaurantCategory.setText(category);
        iRestaurantLocation.setText(location);



    }


    //handle onBackPressed to previous activity
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
