package com.isaac.foodie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;



public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    Context mContext;
    ArrayList<RestaurantDetails> mRestaurant;
    //List<RestaurantDetails> mData;
    private OnRestaurantListener mOnRestaurantListener;

//    public RestaurantAdapter(Context context, List<RestaurantDetails> data) {
//        mContext = context;
//        mData = data;
//    }

    public RestaurantAdapter(Context c, ArrayList<RestaurantDetails> r){
        mContext = c;
        mRestaurant=r;

    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.restaurant_list,viewGroup,false);


        return new RestaurantViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int position) {
        //bind data here

        restaurantViewHolder.restaurant_Name.setText(mRestaurant.get(position).getName());
        //restaurantViewHolder.restaurant_Address.setText(mRestaurant.get(position).getAddress());
        restaurantViewHolder.restaurant_Location.setText(mRestaurant.get(position).getLocation());
        restaurantViewHolder.restaurant_Category.setText(mRestaurant.get(position).getCategory());


    }

    @Override
    public int getItemCount() { return mRestaurant.size(); }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView restaurant_Name,restaurant_Address,restaurant_Category,restaurant_Location;
        OnRestaurantListener onRestaurantListener;

        public RestaurantViewHolder(@NonNull View itemView){
            super(itemView);

            restaurant_Name = itemView.findViewById(R.id.restaurant_Name);
            //restaurant_Address = itemView.findViewById(R.id.restaurant_Address);
            restaurant_Category = itemView.findViewById(R.id.restaurant_Category);
            restaurant_Location = itemView.findViewById(R.id.restaurant_Location);
            this.onRestaurantListener= onRestaurantListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onRestaurantListener.onRestaurantClick(getAdapterPosition());
        }
    }

    public interface OnRestaurantListener{
        void onRestaurantClick(int position);
    }


}