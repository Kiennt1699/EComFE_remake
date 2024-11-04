package com.example.myapplication.Activity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;

import androidx.activity.EdgeToEdge;

import Domain.Category;
import LayoutObject.ProductList;
import LayoutObject.Wishlist;

public class CategoryActivity extends NavigationRoot {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());
        Category cat = getIntent().getParcelableExtra("category");
        ProductList list = new ProductList(findViewById(R.id.productList),this, cat.getCategoryId());
    }
}
