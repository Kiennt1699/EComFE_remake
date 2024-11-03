package com.example.myapplication.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import Domain.User;

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
        ImageView navUser =findViewById(R.id.navProfile);
        ImageView navQuestion = findViewById(R.id.navSupport);
        navMenu.setSelected(true);
        navMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the selected state
                findViewById(R.id.productListing).setVisibility(View.VISIBLE);
                findViewById(R.id.wishlist).setVisibility(View.INVISIBLE);
                findViewById(R.id.userList).setVisibility(View.INVISIBLE);
                findViewById(R.id.questionList).setVisibility(View.INVISIBLE);
                navMenu.setSelected(true);
                navWishlist.setSelected(false);
                navUser.setSelected(false);
                navQuestion.setSelected(false);
            }
        });
        navWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.productListing).setVisibility(View.INVISIBLE);
                findViewById(R.id.wishlist).setVisibility(View.VISIBLE);
                findViewById(R.id.userList).setVisibility(View.INVISIBLE);
                findViewById(R.id.questionList).setVisibility(View.INVISIBLE);
                navMenu.setSelected(false);
                navWishlist.setSelected(true);
                navUser.setSelected(false);
                navQuestion.setSelected(false);
            }
        });
        navUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.productListing).setVisibility(View.INVISIBLE);
                findViewById(R.id.wishlist).setVisibility(View.INVISIBLE);
                findViewById(R.id.userList).setVisibility(View.VISIBLE);
                findViewById(R.id.questionList).setVisibility(View.INVISIBLE);
                navMenu.setSelected(false);
                navWishlist.setSelected(false);
                navUser.setSelected(true);
                navQuestion.setSelected(false);
            }
        });
        navQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.productListing).setVisibility(View.INVISIBLE);
                findViewById(R.id.wishlist).setVisibility(View.INVISIBLE);
                findViewById(R.id.userList).setVisibility(View.INVISIBLE);
                findViewById(R.id.questionList).setVisibility(View.VISIBLE);
                navMenu.setSelected(false);
                navWishlist.setSelected(false);
                navUser.setSelected(false);
                navQuestion.setSelected(true);
            }
        });

        findViewById(R.id.logoutBtn).setOnClickListener(v -> logoutClick());
        TextView username = findViewById(R.id.username);
        username.setText(User.getCurrentUser().getName());
    }

    private void logoutClick() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User.setCurrentUser(null);
                        Intent intent = new Intent(NavigationRoot.this, LoginActivity2.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the 'No' action or just dismiss
                        dialog.dismiss();
                    }
                })
                .show();
    }
}