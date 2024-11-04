package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import API.CartApi;
import API.OrderApi;
import API.RetrofitClient;
import Domain.CheckoutRequest;
import Domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentActivity extends AppCompatActivity {
    Retrofit retrofit = RetrofitClient.getClient();
    CartApi cartApi = retrofit.create(CartApi.class);
    OrderApi orderApi = retrofit.create(OrderApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton paymentBack = findViewById(R.id.paymentBackButton);
        paymentBack.setOnClickListener(v -> finish());


        LinearLayout myLinearLayout = findViewById(R.id.creditCard);
        myLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout(0);
            }
        });

        LinearLayout paypalMethod = findViewById(R.id.paypalMethod);
        paypalMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout(1);
            }
        });

        LinearLayout bankTransfer = findViewById(R.id.bankTransfer);
        bankTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout(2);
            }
        });

        LinearLayout codMethod = findViewById(R.id.codMethod);
        codMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout(3);
            }
        });


    }

    private void checkout(int paymentMethod){
        String userId = User.getCurrentUser().getUserId(); ;
        String address = User.getCurrentUser().getAddress();

        CheckoutRequest checkoutRequest = new CheckoutRequest(address, paymentMethod);

        orderApi.checkout(userId, checkoutRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(PaymentActivity.this, "Checkout failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PaymentActivity.this, "Checkout failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}