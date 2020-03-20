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
    private Toolbar toolbar;
    private static final String TAG = RestaurantInfoActivity.class.getSimpleName();
    DatabaseReference mReference;
    RestaurantAdapter mRestaurantAdapter;
    ArrayList<RestaurantDetails> mRestaurantDetailsArrayList;



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
        //iRestaurantAddress = findViewById(R.id.restaurant_Address);

        //Get Intent
        Intent i = this.getIntent();

        //Receive Data
        String name = i.getExtras().getString("NAME_KEY");
        String category = i.getExtras().getString("CATEGORY_KEY");
        String location = i.getExtras().getString("LOCATION_KEY");

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
