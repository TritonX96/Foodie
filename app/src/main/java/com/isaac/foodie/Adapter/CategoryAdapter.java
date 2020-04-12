package com.isaac.foodie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.isaac.foodie.Model.CategoryModel;
import com.isaac.foodie.R;
import com.isaac.foodie.Model.RestaurantDetails;

public class CategoryAdapter extends FirebaseRecyclerAdapter<CategoryModel,CategoryAdapter.CategoryViewHolder> {

    public CategoryAdapter (@NonNull FirebaseRecyclerOptions<CategoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position, @NonNull CategoryModel model) {
        String post_key = getRef(position).getKey();

        holder.restaurant_CategoryList.setText(post_key);

    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_filter,parent, false);
        return new CategoryViewHolder(view);
    }

    class CategoryViewHolder  extends RecyclerView.ViewHolder{

        TextView  restaurant_CategoryList;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurant_CategoryList = itemView.findViewById(R.id.restaurant_Filter);
        }
    }
}
