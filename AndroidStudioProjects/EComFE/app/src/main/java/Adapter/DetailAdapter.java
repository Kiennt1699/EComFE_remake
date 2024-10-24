package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import Domain.Products;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ProductViewHolder> {

    private Context context;
    private List<Products> productsList;


    // Constructor
    public DetailAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_page, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Products product = productsList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    // ViewHolder class
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTxt;
        private TextView priceTxt;
        private ImageView imageView;
        private TextView detailTxt;
        private RatingBar ratingBar; // Add RatingBar

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            imageView = itemView.findViewById(R.id.imageView);
            detailTxt = itemView.findViewById(R.id.detailTxt);
            ratingBar = itemView.findViewById(R.id.ratingBar); // Initialize RatingBar
        }

        public void bind(Products product) {
            titleTxt.setText(product.getProductName());
            priceTxt.setText(String.format("$%.2f", product.getPrice()));
            detailTxt.setText(product.getDescription()); // Set product description

            // Set the rating
            ratingBar.setRating(product.getRating()); // Assuming getRating() returns a float rating

            Glide.with(itemView.getContext()).load(product.getImageUrl()).into(imageView);


        }
    }



}
