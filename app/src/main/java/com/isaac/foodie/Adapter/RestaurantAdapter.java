package com.isaac.foodie.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.isaac.foodie.R;
import com.isaac.foodie.Model.RestaurantDetails;
import com.isaac.foodie.RestaurantInfoActivity;
import com.squareup.picasso.Picasso;


public class RestaurantAdapter extends FirebaseRecyclerAdapter<RestaurantDetails,RestaurantAdapter.RestaurantViewHolder> {


    public RestaurantAdapter(@NonNull FirebaseRecyclerOptions<RestaurantDetails> options) {
        super(options);
    }

    FirebaseRecyclerOptions<RestaurantDetails> options =
            new FirebaseRecyclerOptions.Builder<RestaurantDetails>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Info"), RestaurantDetails.class)
                    .build();



    @Override
    protected void onBindViewHolder(@NonNull RestaurantViewHolder holder, final int position, @NonNull final RestaurantDetails details) {

        holder.restaurant_Name.setText(details.getName());
        holder.restaurant_Category.setText(details.getCategory());
        holder.restaurant_Location.setText(details.getLocation());

        Picasso.get().load(details.getImage()).into(holder.restaurant_Image);


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String post_key = getRef(position).getKey();

                Intent intent = new Intent(view.getContext(), RestaurantInfoActivity.class);
                intent.putExtra("post_key", post_key);
                view.getContext().startActivity(intent);

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
        ImageView restaurant_Image;

        public RestaurantViewHolder(@NonNull View itemView){
            super(itemView);
            restaurant_Name = itemView.findViewById(R.id.restaurant_Name);
            restaurant_Category = itemView.findViewById(R.id.restaurant_Category);
            restaurant_Location = itemView.findViewById(R.id.restaurant_Location);
            restaurant_Image = itemView.findViewById(R.id.restaurant_Image);

        }
    }

}