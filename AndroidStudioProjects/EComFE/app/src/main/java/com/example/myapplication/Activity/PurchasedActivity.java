package com.example.myapplication.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import API.AuthApi;
import API.OrderApi;
import API.ProductApi;
import API.RetrofitClient;
import Adapter.PurchasedProductAdapter;
import Domain.Order;
import Domain.Products;
import Domain.User;
import LayoutObject.LoveButton;
import LayoutObject.ProductCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PurchasedActivity extends AppCompatActivity {
    private Retrofit retrofit = RetrofitClient.getClient();
    private ArrayList <Order> items = new ArrayList<>();
    private final OrderApi endpoint = retrofit.create(OrderApi.class);;
    private Context rootContext = this;
    private PurchasedProductAdapter adapter = new PurchasedProductAdapter(items);
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_purchased);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton orderHistoryBackButton = findViewById(R.id.orderHistoryBackButton);
        orderHistoryBackButton.setOnClickListener(v -> finish());

        fetchOrders();
        recyclerView = findViewById(R.id.purchaseProductView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PurchasedProductAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    public void fetchOrders() {
        Call<List<Order>> call = endpoint.getOrdersByUserId(User.getCurrentUser().getUserId());
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("FetchOrders", "Response Code: " + response.code());
                    Log.e("FetchOrders", "Response Message: " + response.message());
                    items.clear(); // Clear the list first
                    items.addAll(response.body());
                    // Thêm tất cả các order vào danh sách
                    adapter.notifyDataSetChanged(); // Cập nhật giao diện
                } else {
                    Log.e("FetchOrders", "Response Code: " + response.code());
                    Log.e("FetchOrders", "Response Message: " + response.message());
                    Toast.makeText(rootContext, "Failed to load products in wishlist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(rootContext, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }); // Sửa lỗi thiếu dấu ngoặc đóng ở đây
    }

}