package com.isaac.foodie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.ArrayList;



public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    Context mContext;
    public ArrayList<RestaurantDetails> mRestaurant;
    //List<RestaurantDetails> mData;
    private OnRestaurantListener mOnRestaurantListener;
    private static final String TAG = "MyActivity";


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

        final String post_key =  String.valueOf(mRestaurant.get(position));

        //bind data here

        restaurantViewHolder.restaurant_Name.setText(mRestaurant.get(position).getName());
        //restaurantViewHolder.restaurant_Address.setText(mRestaurant.get(position).getAddress());
        restaurantViewHolder.restaurant_Location.setText(mRestaurant.get(position).getLocation());
        restaurantViewHolder.restaurant_Category.setText(mRestaurant.get(position).getCategory());

        restaurantViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println(post_key);
//                Toast.makeText(mContext, post_key,Toast.LENGTH_LONG).show();
//                Log.d(TAG,"onClick" + post_key );

                Intent intent = new Intent(mContext,RestaurantInfoActivity.class);
                intent.putExtra("post_id",post_key);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() { return mRestaurant.size(); }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView restaurant_Name,restaurant_Address,restaurant_Category,restaurant_Location;
        OnRestaurantListener onRestaurantListener;
        View mView;


        public RestaurantViewHolder(@NonNull View itemView){
            super(itemView);

            mView =itemView;



            restaurant_Name = itemView.findViewById(R.id.restaurant_Name);
            //restaurant_Address = itemView.findViewById(R.id.restaurant_Address);
            restaurant_Category = itemView.findViewById(R.id.restaurant_Category);
            restaurant_Location = itemView.findViewById(R.id.restaurant_Location);
            this.onRestaurantListener= onRestaurantListener;

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(mContext, "You have clicked", Toast.LENGTH_SHORT).show();

        }
    }

    public interface OnRestaurantListener{
        void onRestaurantClick(int position);
    }


}