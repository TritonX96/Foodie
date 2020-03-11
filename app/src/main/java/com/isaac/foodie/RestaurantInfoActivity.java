package com.isaac.foodie;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class RestaurantInfoActivity extends AppCompatActivity {

    TextView iRestaurantName, iRestaurantCategory, iRestaurantAddress, iRestaurantLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        //Action bar
        ActionBar actionBar = getSupportActionBar();
        //Actionbar title
        actionBar.setTitle("Restaurant Detail");
        //set back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        iRestaurantName = findViewById(R.id.restaurantName);
        iRestaurantAddress = findViewById(R.id.restaurantAddress);
        iRestaurantCategory = findViewById(R.id.restaurantCategory);
        iRestaurantLocation = findViewById(R.id.restaurantLocation);


    }

    //handle onBackPressed to previous activity


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();;
        return true;
    }
}
