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
    //private OnRestaurantListener mOnRestaurantListener;

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
        RestaurantDetails mRestaurantDetails = mRestaurantDetailsArrayList.get(position); //check again later
        restaurantViewHolder.restaurant_Name.setText(mRestaurantDetails.getName());
        //restaurantViewHolder.restaurant_Address.setText(mRestaurantDetails.getAddress());
        restaurantViewHolder.restaurant_Location.setText(mRestaurantDetails.getLocation());
        restaurantViewHolder.restaurant_Category.setText(mRestaurantDetails.getCategory());

        restaurantViewHolder.setRestaurantClickListener(new OnRestaurantListener()
        {
            @Override
            public void onRestaurantClick(int position) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mRestaurantDetailsArrayList.size();
    }

    //Open InfOActivity https://www.youtube.com/watch?v=IXzp1BDLlFE
    private void openInfoActivity (String...info)
    {
        Intent i = new Intent(mContext, RestaurantInfoActivity.class);

        i.putExtra("NAME_KEY",info[0]);
        i.putExtra("CATEGORY_KEY",info[1]);
        i.putExtra("LOCATION_KEY",info[2]);

        mContext.startActivity(i);
    }

    //View Holder component
    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView restaurant_Name,restaurant_Address,restaurant_Category,restaurant_Location;
        OnRestaurantListener mOnRestaurantListener;

        public RestaurantViewHolder(@NonNull View itemView){
            super(itemView);
            restaurant_Name = itemView.findViewById(R.id.restaurant_Name);
            //restaurant_Address = itemView.findViewById(R.id.restaurant_Address);
            restaurant_Category = itemView.findViewById(R.id.restaurant_Category);
            restaurant_Location = itemView.findViewById(R.id.restaurant_Location);

            itemView.setOnClickListener(this);
        }

        public void setRestaurantClickListener(OnRestaurantListener  mOnRestaurantListener){
            this.mOnRestaurantListener=mOnRestaurantListener;
        }

        @Override
        public void onClick(View view){
            this.mOnRestaurantListener.onRestaurantClick(this.getLayoutPosition());
        }
    }

    //OnRestaurantClickListener
    public interface OnRestaurantListener{
        void onRestaurantClick(int position);
    }


}