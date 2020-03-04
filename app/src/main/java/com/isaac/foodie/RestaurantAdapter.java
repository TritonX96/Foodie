package com.isaac.foodie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    Context mContext;
    ArrayList<RestaurantDetails> mRestaurant;
    //List<RestaurantDetails> mData;

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
    public int getItemCount() {
        return mRestaurant.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        TextView restaurant_Longitude,restaurant_Latitude;
        TextView restaurant_Name,restaurant_Address,restaurant_Category,restaurant_Location;


        public RestaurantViewHolder(@NonNull View itemView){
            super(itemView);

            restaurant_Name = itemView.findViewById(R.id.restaurant_Name);
            //restaurant_Address = itemView.findViewById(R.id.restaurant_Address);
            restaurant_Category = itemView.findViewById(R.id.restaurant_Category);
            restaurant_Location = itemView.findViewById(R.id.restaurant_Location);

        }
    }


}
