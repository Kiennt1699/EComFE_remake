package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import Domain.WishlistItem;
import Domain.WishlistRequest;
import LayoutObject.WishlistCard;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ProductCard> {
    ArrayList<WishlistCard> items;
    Context context;
    OnClickListener clickListener;

    public WishlistAdapter(ArrayList<WishlistCard> items, OnClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public WishlistAdapter.ProductCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.product_card, parent, false);
        return new ProductCard(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.ProductCard holder, int position) {
        WishlistCard viewObject = items.get(position);
        WishlistItem product = viewObject.getItem();
        // Bind data to the view elements
        holder.titleMenuTxt.setText(product.getProduct().getProductName());
        holder.priceTxt.setText(String.format("$%s", product.getProduct().getPrice())); // Assuming getPrice() is available in your Products model
        holder.descTxt.setText(String.valueOf(product.getProduct().getDescription())); // Assuming getRating() is available
        holder.heart.setSelected(true);
        // Use Glide to load images
        Glide.with(context).load(product.getProduct().getImageUrl()).into(holder.pic);
        holder.itemView.setOnClickListener(v -> clickListener.onProductClick(viewObject,position));
        holder.itemView.findViewById(R.id.loveBtn)
                .setOnClickListener(v -> clickListener.onLovedClick(viewObject,position));
        holder.itemView.findViewById(R.id.addToCartBtn)
                .setOnClickListener(v -> clickListener.onAddToCartClick(viewObject,position));

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductCard extends RecyclerView.ViewHolder {
        TextView titleMenuTxt, priceTxt, descTxt;
        ImageView pic;
        ImageView heart;

        public ProductCard(@NonNull View itemView) {
            super(itemView);
            titleMenuTxt = itemView.findViewById(R.id.titleMenuTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            descTxt = itemView.findViewById(R.id.descTxt);
            pic = itemView.findViewById(R.id.pic);
            heart = itemView.findViewById(R.id.loveBtn);
        }
    }

    public interface OnClickListener {
        void onProductClick(WishlistCard item, int index);
        void onLovedClick(WishlistCard item, int index);
        void onAddToCartClick(WishlistCard item, int index);
    }
}