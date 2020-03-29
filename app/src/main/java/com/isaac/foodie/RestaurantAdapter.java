package com.isaac.foodie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class RestaurantAdapter extends FirebaseRecyclerAdapter<RestaurantDetails,RestaurantAdapter.RestaurantViewHolder> {


    public RestaurantAdapter(@NonNull FirebaseRecyclerOptions<RestaurantDetails> options) {
        super(options);
    }

    FirebaseRecyclerOptions<RestaurantDetails> options =
            new FirebaseRecyclerOptions.Builder<RestaurantDetails>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant"), RestaurantDetails.class)
                    .build();



    @Override
    protected void onBindViewHolder(@NonNull RestaurantViewHolder holder, final int position, @NonNull final RestaurantDetails details) {

        holder.restaurant_Name.setText(details.getName());
        holder.restaurant_Category.setText(details.getCategory());
        holder.restaurant_Location.setText(details.getLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String post_key = getRef(position).getKey();

                Intent intent = new Intent(view.getContext(), RestaurantInfoActivity.class);
                intent.putExtra("post_key", post_key);
                view.getContext().startActivity(intent);
                
                //Toast.makeText(view.getContext(),"It has been clicked", Toast.LENGTH_LONG).show();
//                Toast.makeText(view.getContext(),"position" , + post_key).show();

            }
        });
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list,parent, false);
        return new RestaurantViewHolder(view);
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{

            TextView restaurant_Name, restaurant_Category,restaurant_Location;

            public RestaurantViewHolder(@NonNull View itemView){
                super(itemView);
                restaurant_Name = itemView.findViewById(R.id.restaurant_Name);
                restaurant_Category = itemView.findViewById(R.id.restaurant_Category);
                restaurant_Location = itemView.findViewById(R.id.restaurant_Location);
            }
        }

}