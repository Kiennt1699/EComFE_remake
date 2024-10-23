package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;

import Domain.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Category> items;
    Context context;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    public CategoryAdapter(ArrayList<Category> items, OnCategoryClickListener listener) {
        this.items = items;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getName());

        // Set the background image for the category based on position
        switch (position) {
            case 0:
                holder.pic.setBackgroundResource(R.drawable.cat_1_background);
                break;
            case 1:
                holder.pic.setBackgroundResource(R.drawable.cat_2_background);
                break;
            case 2:
                holder.pic.setBackgroundResource(R.drawable.cat_3_background);
                break;
            case 3:
                holder.pic.setBackgroundResource(R.drawable.cat_4_background);
                break;
            case 4:
                holder.pic.setBackgroundResource(R.drawable.cat_5_background);
                break;
            case 5:
                holder.pic.setBackgroundResource(R.drawable.cat_6_background);
                break;
            case 6:
                holder.pic.setBackgroundResource(R.drawable.cat_7_background);
                break;
            case 7:
                holder.pic.setBackgroundResource(R.drawable.cat_8_background);
                break;
        }

        // Load the category image URL using Glide
        String imageUrl = items.get(position).getImageUrl();
        Glide.with(context)
                .load(imageUrl) // Load the image from URL
                .into(holder.pic); // Set the loaded image into the ImageView
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCategoryClick(items.get(position)); // Pass the clicked category
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size(); // Return the number of categories
    }

    // ViewHolder class to hold the views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt; // TextView for category name
        ImageView pic;     // ImageView for category picture

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat); // Ensure correct ID reference
        }
    }
}
