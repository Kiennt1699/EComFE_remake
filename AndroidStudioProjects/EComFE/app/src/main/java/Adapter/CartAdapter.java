package Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import API.CartApi;
import API.RetrofitClient;
import Domain.AddToCartRequest;
import Domain.CartItem;
import Domain.Products;
import Domain.User;
import kotlin.jvm.PurelyImplements;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;

import com.example.myapplication.Activity.CartActivity;
import com.example.myapplication.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    Context context;
    private List<CartItem> selectedItems = new ArrayList<>();


    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        // Set the product details
        holder.productName.setText(cartItem.getProduct().getName());
        holder.quantity.setText("Quantity: "+ cartItem.getQuantity());
        holder.price.setText("Price: $" + cartItem.getProduct().getPrice());
        holder.desc.setText("" + cartItem.getProduct().getDescription());
        // Load the product image using Glide
        Glide.with(context)
                .load(cartItem.getProduct().getImageUrl())
                .placeholder(R.drawable.cat_6_background) // Optional placeholder image
                .error(R.drawable.cat_3_background) // Optional error image
                .into(holder.productImage);

        holder.selectCheckbox.setOnCheckedChangeListener(null); // Đặt listener về null để tránh trigger
        holder.selectCheckbox.setChecked(cartItem.isChecked()); // Thiết lập trạng thái checkbox từ cartItem

        holder.selectCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedItems.add(cartItem);
                cartItem.setChecked(true); // Cập nhật trạng thái trong CartItem
            } else {
                selectedItems.remove(cartItem);
                cartItem.setChecked(false); // Cập nhật trạng thái trong CartItem
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size(); // Return the size of the cartItems list
    }

    public List<CartItem> getSelectedItems() {
        return selectedItems;
    }
    // ViewHolder class to hold the views for each item
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, quantity, price;
        ImageView productImage;
        CheckBox selectCheckbox;
        ImageView backBtn;
        TextView totalPriceTextView;
        TextView desc;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
            productImage = itemView.findViewById(R.id.productImage);
            selectCheckbox = itemView.findViewById(R.id.selectCheckbox);
            backBtn = itemView.findViewById(R.id.backBtn);
            totalPriceTextView = itemView.findViewById(R.id.totalPriceTextView);
            desc = itemView.findViewById(R.id.descTxt);
        }
    }

    public double calculateTotalPrice(List<CartItem> cartItems) {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return Math.round(totalPrice * 100.0) / 100.0;
    }
    public void addToCart(Products product, String quantityString) {
        int quantity = 0 ; // Giá trị mặc định nếu người dùng không nhập

        // Kiểm tra nếu người dùng đã nhập số lượng
        if (quantityString.matches("\\d+")) {  // Kiểm tra chuỗi chỉ chứa các ký tự số
            quantity = Integer.parseInt(quantityString);
            if (quantity > 0) {
            } else {
                Toast.makeText(context, "Please enter a valid quantity greater than 0", Toast.LENGTH_SHORT).show();
            return;
            }
        } else {
            Toast.makeText(context, "Please enter a numeric quantity", Toast.LENGTH_SHORT).show();
        return;
        }
        AddToCartRequest request = new AddToCartRequest(
                product.getProductId(),
                quantity, // Số lượng mặc định là 1
                product.getPrice()
        );
        // Gọi API để thêm vào giỏ hàng
        Retrofit retrofit = RetrofitClient.getClient();
        CartApi cartApi = retrofit.create(CartApi.class);
        // Gọi phương thức thêm vào giỏ hàng
        Call<Void> call = cartApi.addToCart(User.getCurrentUser().getUserId(), request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Added to cart!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
