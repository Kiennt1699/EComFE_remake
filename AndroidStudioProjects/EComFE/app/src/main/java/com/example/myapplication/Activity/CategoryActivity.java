package com.example.myapplication.Activity;

import android.os.Bundle;
import com.example.myapplication.R;

import androidx.activity.EdgeToEdge;

import LayoutObject.Wishlist;

public class CategoryActivity extends NavigationRoot {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        findViewById(R.id.backBtn).setOnClickListener(v -> finish());
        Wishlist list = new Wishlist(findViewById(R.id.productList),this);
    }
}
