package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.SaleAdapter;
import API.ProductApi;
import API.RetrofitClient;
import API.CategoryApi;
import Adapter.CategoryAdapter;
import Domain.Category;
import Domain.Products;
import Domain.User;
import LayoutObject.Wishlist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends NavigationRoot {

    RecyclerView saleView;
    SaleAdapter saleAdapter;
    ArrayList<Products> productList = new ArrayList<>();
    ProgressBar progressBarProduct;

    RecyclerView  categoryView;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categoryList = new ArrayList<>();
    ProgressBar progressBarCategory;
    UserSettingsActivity userSettingsActivity;

    Wishlist wishlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Initialize RecyclerView
        saleView = findViewById(R.id.saleView);
        saleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        wishlist = new Wishlist(findViewById(R.id.productList), this);
        userSettingsActivity = new UserSettingsActivity(this);

        // Initialize the adapter with an empty list
        saleAdapter = new SaleAdapter(productList, this::onProductClick);
        saleView.setAdapter(saleAdapter); // Attach the adapter immediately
        progressBarProduct = findViewById(R.id.progressBarSale);

        // Initialize RecyclerView for Categories
        categoryView = findViewById(R.id.categoryView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        categoryView.setLayoutManager(gridLayoutManager);
        categoryAdapter = new CategoryAdapter(categoryList, this::onCategoryClick);
        categoryView.setAdapter(categoryAdapter);
        progressBarCategory = findViewById(R.id.progressBarCategory);
        findViewById(R.id.viewAllTxt).setOnClickListener(v -> onViewAllClick());

        ImageView cartIcon = findViewById(R.id.CartBtn );
        cartIcon.setOnClickListener(v -> onCartClick());
        // Fetch data from the API
        fetchProductData();
        fetchCategoryData();
        //Loading bar remove

    }
    private void fetchProductData() {
        Retrofit retrofit = RetrofitClient.getClient();
        ProductApi productApi = retrofit.create(ProductApi.class);

        Call<List<Products>> call = productApi.getProducts(User.getCurrentUser().getUserId());
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear(); // Clear the list first (if needed)
                    productList.addAll(response.body());
                    progressBarProduct.setVisibility(View.GONE);
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
    private void fetchCategoryData() {

        Retrofit retrofit = RetrofitClient.getClient();
        CategoryApi categoryApi = retrofit.create(CategoryApi.class);

        Call<List<Category>> call = categoryApi.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList.clear();
                    categoryList.addAll(response.body());
                    categoryAdapter.notifyDataSetChanged();
                    progressBarCategory.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void onProductClick(Products product) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("product", product); // Pass the selected product
        launcher.launch((intent));
    }

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> recreate());

    private void onCategoryClick(Category category) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra("category", category); // Pass the clicked category
        launcher.launch((intent));
    }

    private void onViewAllClick()
    {
        Intent intent = new Intent(MainActivity.this, ProductListingActivity.class);
        launcher.launch((intent));
    }

    private void onCartClick() {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        // Add any extra data if needed
        launcher.launch(intent);
    }

}