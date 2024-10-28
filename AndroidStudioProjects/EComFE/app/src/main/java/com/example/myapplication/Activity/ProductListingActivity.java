package com.example.myapplication.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

import LayoutObject.ProductList;

public class ProductListingActivity extends NavigationRoot {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        TextView title = findViewById(R.id.backbar_title);
        title.setText("Product Listing");
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());
        ProductList list = new ProductList(findViewById(R.id.productList),this);
    }
}
