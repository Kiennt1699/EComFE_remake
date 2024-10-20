package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import API.WishlistApi;
import Adapter.SaleAdapter;
import API.ProductApi;
import API.RetrofitClient;
import Adapter.WishlistAdapter;
import Domain.Products;
import Domain.WishlistItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends NavigationRoot {

    RecyclerView saleView;
    RecyclerView wishlist;
    SaleAdapter saleAdapter;
    WishlistAdapter wishlistAdapter;
    ArrayList<Products> productList = new ArrayList<>();
    ArrayList<WishlistItem> wishlistItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize RecyclerView
        saleView = findViewById(R.id.saleView);
        saleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        wishlist = findViewById(R.id.productList);
        wishlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        // Initialize the adapter with an empty list
        saleAdapter = new SaleAdapter(productList, this::onProductClick);
        wishlistAdapter = new WishlistAdapter(wishlistItems);
        saleView.setAdapter(saleAdapter); // Attach the adapter immediately
        wishlist.setAdapter(wishlistAdapter);

        // Fetch data from the API
        fetchProductData();
        fetchWishlistData();
    }
    private void fetchProductData() {
        Retrofit retrofit = RetrofitClient.getClient();
        ProductApi productApi = retrofit.create(ProductApi.class);

        Call<List<Products>> call = productApi.getProducts();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear(); // Clear the list first (if needed)
                    productList.addAll(response.body());

                    // Notify the adapter that the data set has changed
                    saleAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onProductClick(Products product) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("product", product); // Pass the selected product
        startActivity(intent);
    }
    private void fetchWishlistData(){
        Retrofit retrofit = RetrofitClient.getClient();
        WishlistApi wishlistApi = retrofit.create(WishlistApi.class);

        Call<List<WishlistItem>> call = wishlistApi.getWishlistItems();
        call.enqueue(new Callback<List<WishlistItem>>() {
            @Override
            public void onResponse(Call<List<WishlistItem>> call, Response<List<WishlistItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Add the fetched products to the product list
                    wishlistItems.clear(); // Clear the list first (if needed)
                    wishlistItems.addAll(response.body());

                    // Notify the adapter that the data set has changed
                    wishlistAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load products in wishlist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<WishlistItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}