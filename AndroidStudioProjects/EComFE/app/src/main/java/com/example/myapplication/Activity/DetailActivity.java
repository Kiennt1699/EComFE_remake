package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import API.RetrofitClient;
import API.WishlistApi;
import Domain.Products;
import Domain.User;
import LayoutObject.LoveButton;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView titleTxt;
    private TextView priceTxt;
    private TextView detailTxt;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        // Initialize UI components
        productImage = findViewById(R.id.imageView);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        detailTxt = findViewById(R.id.detailTxt);
        ratingBar = findViewById(R.id.ratingBar);
        ImageView backBtn = findViewById(R.id.backBtn);
        ImageView loveBtn = findViewById(R.id.loveBtn);

        // Fetch product details from the intent
        Products selectedProduct = getIntent().getParcelableExtra("product"); // Use Parcelable
        if (selectedProduct != null) {
            displayProductDetails(selectedProduct);
            loveBtn.setActivated(selectedProduct.isWishlisted());
        } else {
            // Handle the case where product is not found (optional)
        }

        // Set up OnBackPressedCallback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button press here
                finish(); // Close the DetailActivity
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        // Set up back button click listener
        backBtn.setOnClickListener(v -> finish()); // Close the DetailActivity
        loveBtn.setOnClickListener(v -> {
            if (v.isActivated() == false){
                LoveButton.addToWishlist(this, User.getCurrentUser().getUserId(), selectedProduct.getProductId());
                v.setActivated(true);
            } else {
                LoveButton.removeFromWishlist(this, User.getCurrentUser().getUserId(), selectedProduct.getProductId());
                v.setActivated(false);
            }
        });
    }

    private void displayProductDetails(Products product) {
        titleTxt.setText(product.getProductName());
        priceTxt.setText(String.format("$%.2f", product.getPrice()));
        detailTxt.setText(product.getDescription());
        ratingBar.setRating(product.getRating());

        // Load the product image using Glide
        Glide.with(this)
                .load(product.getImageUrl())
                .into(productImage);
    }
}