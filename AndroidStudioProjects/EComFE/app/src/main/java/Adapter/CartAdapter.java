package Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import Domain.CartItem;
import kotlin.jvm.PurelyImplements;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;

import com.example.myapplication.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    Context context;
    private List<CartItem> selectedItems = new ArrayList<>();


    public CartAdapter(List<CartItem> cartItems) {
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
        holder.quantity.setText("Quantity: " + cartItem.getQuantity());
        holder.price.setText("Price: $" + cartItem.getProduct().getPrice());
        Log.d("Image URL", cartItem.getProduct().getImageUrl());

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

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
            productImage = itemView.findViewById(R.id.productImage);
            selectCheckbox = itemView.findViewById(R.id.selectCheckbox);

        }
    }

    public double calculateTotalPrice(List<CartItem> cartItems) {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return Math.round(totalPrice * 100.0) / 100.0;
    }

}
