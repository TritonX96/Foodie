package com.isaac.foodie;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.isaac.foodie.Adapter.RestaurantAdapter;
import com.isaac.foodie.Model.RestaurantDetails;


public class ResRecyclerViewActivity extends AppCompatActivity {


    private RecyclerView restaurantRecyclerView;
    private RestaurantAdapter adapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantlist);

        restaurantRecyclerView = findViewById(R.id.restaurant_rv);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RestaurantDetails> options =
                new FirebaseRecyclerOptions.Builder<RestaurantDetails>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Info"), RestaurantDetails.class)
                        .build();

        adapter = new RestaurantAdapter(options);
        restaurantRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

