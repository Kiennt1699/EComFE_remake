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
import java.util.function.Consumer;

import Domain.Products;
import LayoutObject.ProductCard;

public class ProductlistAdapter extends RecyclerView.Adapter<ProductlistAdapter.ProductCardS> {
    ArrayList<LayoutObject.ProductCard> items;
    Context context;
    OnClickListener clickListener;

    public ProductlistAdapter(ArrayList<LayoutObject.ProductCard> items, OnClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProductCardS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.product_card, parent, false);
        return new ProductCardS(inflate,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardS holder, int position) {
        LayoutObject.ProductCard viewObject = items.get(position);
        Products product = viewObject.getItem();
        // Bind data to the view elements
        holder.titleMenuTxt.setText(product.getProductName());
        holder.priceTxt.setText(String.format("$%s", product.getPrice())); // Assuming getPrice() is available in your Products model
        holder.descTxt.setText(String.valueOf(product.getDescription())); // Assuming getRating() is available
        holder.heartbtn.setActivated(product.isWishlisted());
        // Use Glide to load images
        Glide.with(context).load(product.getImageUrl()).into(holder.pic);

        holder.heartbtn.setOnClickListener(v -> clickListener.onLovedClick(v, viewObject, position));
        holder.addbtn.setOnClickListener(v -> clickListener.onAddToCartClick(v, viewObject, position));
        holder.itemView.setOnClickListener(v -> clickListener.onProductClick(v, viewObject, position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductCardS extends RecyclerView.ViewHolder {
        View itemView;
        TextView titleMenuTxt, priceTxt, descTxt;
        ImageView pic;
        ImageView heartbtn;
        TextView addbtn;

        public ProductCardS(@NonNull View itemView, OnClickListener listener) {
            super(itemView);
            this.itemView = itemView;
            titleMenuTxt = itemView.findViewById(R.id.titleMenuTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            descTxt = itemView.findViewById(R.id.descTxt);
            pic = itemView.findViewById(R.id.pic);
            heartbtn = itemView.findViewById(R.id.loveBtn);
            addbtn = itemView.findViewById(R.id.addToCartBtn);
        }
    }

    public interface OnClickListener {
        void onProductClick(View view, ProductCard item, int pos);
        void onLovedClick(View view, ProductCard item, int pos);
        void onAddToCartClick(View view, ProductCard item, int pos);
    }
}