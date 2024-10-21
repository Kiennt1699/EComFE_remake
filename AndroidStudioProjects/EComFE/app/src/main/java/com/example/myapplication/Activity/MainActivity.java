package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.SaleAdapter;
import API.ProductApi;
import API.RetrofitClient;
import Domain.Products;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends NavigationRoot {

    RecyclerView saleView;
    SaleAdapter saleAdapter;
    ArrayList<Products> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize RecyclerView
        saleView = findViewById(R.id.saleView);
        saleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Initialize the adapter with an empty list
        saleAdapter = new SaleAdapter(productList, this::onProductClick);
        saleView.setAdapter(saleAdapter); // Attach the adapter immediately

        // Fetch data from the API
        fetchProductData();
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
}