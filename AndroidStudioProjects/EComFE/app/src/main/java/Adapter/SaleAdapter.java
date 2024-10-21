package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.DetailActivity;
import com.example.myapplication.R;
import java.util.ArrayList;

import Domain.Products;
public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.viewholder> {
    ArrayList<Products> items;
    Context context;
    OnProductClickListener onProductClickListener; // Add click listener interface

    public SaleAdapter(ArrayList<Products> items, OnProductClickListener onProductClickListener) {
        this.items = items;
        this.onProductClickListener = onProductClickListener;
    }

    @NonNull
    @Override
    public SaleAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_holder, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleAdapter.viewholder holder, int position) {
        Products product = items.get(position);

        // Bind data to the view elements
        holder.titleMenuTxt.setText(product.getProductName());
        holder.priceTxt.setText("$" + product.getPrice());
        holder.starTxt.setText(String.valueOf(product.getRating()));

        // Use Glide to load images
        Glide.with(context).load(product.getImageUrl()).into(holder.pic);

        // Set click listener to open DetailActivity with selected product's details
        holder.itemView.setOnClickListener(v -> onProductClickListener.onProductClick(product));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView titleMenuTxt, priceTxt, starTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleMenuTxt = itemView.findViewById(R.id.titleMenuTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            starTxt = itemView.findViewById(R.id.starTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }

    // Interface for click listener
    public interface OnProductClickListener {
        void onProductClick(Products product);
    }
}