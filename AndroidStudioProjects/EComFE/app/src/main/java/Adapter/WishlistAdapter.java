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

import Domain.Products;
import Domain.WishlistItem;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ProductCard> {
    ArrayList<WishlistItem> items;
    Context context;

    public WishlistAdapter(ArrayList<WishlistItem> items){
        this.items = items;
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
        WishlistItem product = items.get(position);
        // Bind data to the view elements
        holder.titleMenuTxt.setText(product.getProduct().getProductName());
        holder.priceTxt.setText(String.format("$%s", product.getProduct().getPrice())); // Assuming getPrice() is available in your Products model
        holder.descTxt.setText(String.valueOf(product.getProduct().getDescription())); // Assuming getRating() is available

        // Use Glide to load images
        Glide.with(context).load(product.getProduct().getImageUrl()).into(holder.pic); // Assuming getImageUrl() provides the image URL
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductCard extends RecyclerView.ViewHolder {
        TextView titleMenuTxt, priceTxt, descTxt;
        ImageView pic;

        public ProductCard(@NonNull View itemView) {
            super(itemView);
            titleMenuTxt = itemView.findViewById(R.id.titleMenuTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            descTxt = itemView.findViewById(R.id.descTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}