package com.isaac.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class MainActivity<item> extends AppCompatActivity  {

    private Toolbar toolbar;
    private Button searchButton;
    private Button locationButton;
    private Button databaseButton;
    private Button areaButton;
    private Button categoryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Search for nearby restaurants based on the radius maybe 1km or 2km
        locationButton = findViewById(R.id.location_button);
        locationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MapActivity();
            }
        });


        //This is to test out the recyclerview before making more advanced like sort and filter
        databaseButton = findViewById(R.id.database_button);
        databaseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),ResRecyclerViewActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        //Will print out a list of category and then click on the choice of category will print out based on the category
        categoryButton = findViewById(R.id.category_button);
        categoryButton.setOnClickListener(new View.OnClickListener(){
                               @Override
                               public void onClick(View v) {

                                   Intent intent = new Intent(v.getContext(),CategoryRecyclerView.class);
                                   v.getContext().startActivity(intent);
                               }
                           });

        //Will print out a list of area and then click on the choice of area will print out based on the area
        areaButton = findViewById(R.id.category_button);




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //Testing
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        }

        return true;
    }

    public void MapActivity(){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }



}
