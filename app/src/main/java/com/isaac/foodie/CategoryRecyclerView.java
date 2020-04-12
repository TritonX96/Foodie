package com.isaac.foodie;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isaac.foodie.Adapter.CategoryAdapter;
import com.isaac.foodie.Model.CategoryModel;
import com.isaac.foodie.Model.RestaurantDetails;

import java.util.HashSet;
import java.util.Set;


public class CategoryRecyclerView extends AppCompatActivity {

      DatabaseReference categoryRef;
      RecyclerView categoryRecyclerView;
      CategoryAdapter adapter;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantlist);

        categoryRecyclerView = findViewById(R.id.restaurant_rv);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CategoryModel> options =
                new FirebaseRecyclerOptions.Builder<CategoryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Category"), CategoryModel.class)
                        .build();

        //categoryRef = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Category");

        adapter = new CategoryAdapter(options);
        categoryRecyclerView.setAdapter(adapter);

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
