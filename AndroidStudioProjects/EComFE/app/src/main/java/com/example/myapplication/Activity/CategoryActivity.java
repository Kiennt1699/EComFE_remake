package com.example.myapplication.Activity;

import android.os.Bundle;
import com.example.myapplication.R;

import androidx.activity.EdgeToEdge;

public class CategoryActivity extends NavigationRoot {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wishlist_page);
    }
}
