package com.example.myapplication.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import Adapter.DetailAdapter;
import Domain.Products;

public class DetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView titleTxt;
    private TextView priceTxt;
    private TextView detailTxt;
    private RatingBar ratingBar;
    private EditText quantityEditText;

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
        Button addBtn = findViewById(R.id.addBtn);
        quantityEditText = findViewById(R.id.numTxt);


        // Fetch product details from the intent
        Products selectedProduct = getIntent().getParcelableExtra("product"); // Use Parcelable
        if (selectedProduct != null) {
            displayProductDetails(selectedProduct);
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
        backBtn.setOnClickListener(v -> finish());
        addBtn.setOnClickListener(v -> {
            quantityEditText = findViewById(R.id.numTxt);
            String quantityString = quantityEditText.getText().toString();
            if (selectedProduct != null) {
                DetailAdapter detailAdapter = new DetailAdapter(this, null); // Tạo adapter
                detailAdapter.addToCart(selectedProduct,quantityString); // Gọi hàm addToCart từ adapter
            } else {
                Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
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