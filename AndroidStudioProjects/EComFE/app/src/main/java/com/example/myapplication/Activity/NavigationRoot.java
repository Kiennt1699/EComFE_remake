package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class NavigationRoot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_root);
        onClickSetUp();
    }
    protected void onClickSetUp(){
        ImageView navMenu = findViewById(R.id.navMenu);
        ImageView navWishlist = findViewById(R.id.navWishlist);
        navMenu.setSelected(true);
        navMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the selected state
                findViewById(R.id.productListing).setVisibility(View.VISIBLE);
                findViewById(R.id.wishlist).setVisibility(View.INVISIBLE);
                navMenu.setSelected(true);
                navWishlist.setSelected(false);
            }
        });
        navWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.productListing).setVisibility(View.INVISIBLE);
                findViewById(R.id.wishlist).setVisibility(View.VISIBLE);
                navMenu.setSelected(false);
                navWishlist.setSelected(true);
            }
        });
    }
}