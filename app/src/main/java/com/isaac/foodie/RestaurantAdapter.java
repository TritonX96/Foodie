package com.isaac.foodie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<RestaurantDetails> mRestaurantDetailsArrayList;
    private OnRestaurantListener mOnRestaurantListener;



    public RestaurantAdapter(Context c, ArrayList<RestaurantDetails> r){
        mContext = c;
        mRestaurantDetailsArrayList=r;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout = LayoutInflater.from(mContext).inflate(R.layout.restaurant_list,viewGroup,false);
        return new RestaurantViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //bind data here
        RestaurantDetails currentDetails = mRestaurantDetailsArrayList.get(position); //check again later
        restaurantViewHolder.restaurant_Name.setText(mRestaurantDetailsArrayList.get(position).getName());
        //restaurantViewHolder.restaurant_Address.setText(mRestaurantDetailsArrayList.get(position).getAddress());
        restaurantViewHolder.restaurant_Location.setText(mRestaurantDetailsArrayList.get(position).getLocation());
        restaurantViewHolder.restaurant_Category.setText(mRestaurantDetailsArrayList.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return mRestaurantDetailsArrayList.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView restaurant_Longitude,restaurant_Latitude;
        TextView restaurant_Name,restaurant_Address,restaurant_Category,restaurant_Location;

        OnRestaurantListener onRestaurantListener;

        public RestaurantViewHolder(@NonNull View itemView){
            super(itemView);
            restaurant_Name = itemView.findViewById(R.id.restaurant_Name);
            //restaurant_Address = itemView.findViewById(R.id.restaurant_Address);
            restaurant_Category = itemView.findViewById(R.id.restaurant_Category);
            restaurant_Location = itemView.findViewById(R.id.restaurant_Location);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: clicked on ");

//            if(mOnRestaurantListener!=null){
//                int position = getAdapterPosition();
//                if(position!=RecyclerView.NO_POSITION) {
//                    onRestaurantListener.onRestaurantClick(getAdapterPosition());
//                }
//            }

            Intent intent = new Intent(mContext, RestaurantInfoActivity.class);
            mContext.startActivity(intent);
        }

    }

    public interface OnRestaurantListener{
        void onRestaurantClick(int position);
    }

    public void setOnRestaurantListener(OnRestaurantListener listener){
        mOnRestaurantListener = listener;
    }


}