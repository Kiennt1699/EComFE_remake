package com.example.myapplication.Activity;

import android.os.Bundle;

import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.CartAdapter; // Giả sử bạn đã tạo CartAdapter
import API.CartApi; // Import CartApi
import API.RetrofitClient; // Import RetrofitClient
import Domain.CartItem; // Giả sử bạn đã định nghĩa CartItem
import Domain.Products;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity {

    RecyclerView cartView;
    CartAdapter cartAdapter;
    ArrayList<CartItem> cartItemList = new ArrayList<>(); // Danh sách item giỏ hàng
    TextView totalPriceTextView;
    double totalPrice ;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page); // Đảm bảo layout đúng

        // Initialize RecyclerView
        cartView = findViewById(R.id.cartRecyclerView); // Đảm bảo ID đúng
        cartView.setLayoutManager(new LinearLayoutManager(this)); // Sử dụng layout manager dọc
        // Initialize the adapter with an empty list
        cartAdapter = new CartAdapter(cartItemList);
        cartView.setAdapter(cartAdapter); // Gán adapter cho RecyclerView
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        // Fetch data from the API
        deleteButton = findViewById(R.id.deleteButton);
        fetchCartData();

        deleteButton.setOnClickListener(v -> deleteSelectedItems());

    }
    private void deleteCartItem(String cartItemId) {
        Retrofit retrofit = RetrofitClient.getClient();
        CartApi cartApi = retrofit.create(CartApi.class);

        Call<Void> call = cartApi.deleteCartItem(cartItemId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CartActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CartActivity.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void deleteSelectedItems() {
        List<CartItem> selectedItems = cartAdapter.getSelectedItems();

        if (!selectedItems.isEmpty()) {
            // Xóa các item đã chọn khỏi cartItemList
            cartItemList.removeAll(selectedItems);
            for (CartItem item : selectedItems) {
                deleteCartItem(item.getProduct().getProductId() );  // Gọi hàm xóa từng mục
            }
            // Thông báo cho adapter rằng dữ liệu đã thay đổi
            cartAdapter.notifyDataSetChanged();
            // Cập nhật lại tổng giá
            totalPrice = cartAdapter.calculateTotalPrice(cartItemList);
            totalPriceTextView.setText("Total: $ " + totalPrice);
        } else {
            Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show();
        }
    }
    private void fetchCartData() {

        // Pass the token to Retrofit
        Retrofit retrofit = RetrofitClient.getClient();
        CartApi cartApi = retrofit.create(CartApi.class);

        Call<List<CartItem>> call = cartApi.getCartItems();
        call.enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Thêm các item đã lấy được vào danh sách giỏ hàng
//                    cartItemList.clear(); // Xóa danh sách cũ nếu cần
                    cartItemList.addAll(response.body());

                    // Thông báo cho adapter rằng dữ liệu đã thay đổi
                    cartAdapter.notifyDataSetChanged();
                    totalPrice = cartAdapter.calculateTotalPrice(cartItemList);
                    totalPriceTextView.setText("Total: $ " + totalPrice);
                } else {
                    Toast.makeText(CartActivity.this, "Failed to load cart items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
