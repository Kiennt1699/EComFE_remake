package com.example.myapplication.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

import API.RetrofitClient;
import API.WishlistApi;
import Adapter.CartAdapter;
import Domain.CartItem;
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
    private TextView numTxt;
    private int quantity = 1;
    private Products selectedProduct;

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
        numTxt = findViewById(R.id.numTxt);
        ImageView backBtn = findViewById(R.id.backBtn);
        ImageView loveBtn = findViewById(R.id.loveBtn);
        Button addBtn = findViewById(R.id.addBtn);
        TextView plusBtn = findViewById(R.id.plusBtn);
        TextView minusBtn = findViewById(R.id.minusBtn);

        List<CartItem> cartItems = new ArrayList<>();
        CartAdapter adapter = new CartAdapter(this, cartItems);

        // Fetch product details from the intent
        selectedProduct = getIntent().getParcelableExtra("product");

        if (selectedProduct != null) {
            displayProductDetails(selectedProduct);
            loveBtn.setActivated(selectedProduct.isWishlisted());
        } else {
            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set up OnBackPressedCallback
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish(); // Close the DetailActivity
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        // Set up back button click listener
        backBtn.setOnClickListener(v -> finish()); // Close the DetailActivity

        loveBtn.setOnClickListener(v -> {
            if (!v.isActivated()) {
                LoveButton.addToWishlist(this, User.getCurrentUser().getUserId(), selectedProduct.getProductId());
                v.setActivated(true);
            } else {
                LoveButton.removeFromWishlist(this, User.getCurrentUser().getUserId(), selectedProduct.getProductId());
                v.setActivated(false);
            }
        });

        plusBtn.setOnClickListener(view -> {
            quantity++;
            updateQuantityDisplay();
        });

        // Minus button to decrease quantity, ensuring it doesn't go below 1
        minusBtn.setOnClickListener(view -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityDisplay();
            }
        });

        addBtn.setOnClickListener(v -> {
            String quantityString = numTxt.getText().toString();
            if (!quantityString.isEmpty()) {
                Log.d("DetailActivity", "Adding product to cart: " + selectedProduct.toString());
                Log.d("DetailActivity", "Quantity: " + quantityString);
                adapter.addToCart(selectedProduct, quantityString);
                Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Quantity cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateQuantityDisplay() {
        numTxt.setText(String.valueOf(quantity));
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = quantity * selectedProduct.getPrice();
        TextView totalTxt = findViewById(R.id.totalTxt);
        totalTxt.setText(String.format("$%.2f", totalPrice));
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
