package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;

import Domain.Order;
import Domain.OrderItem;
import Domain.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ArrayList<OrderItem> items;

    public ProductAdapter() {
    }

    public ProductAdapter(ArrayList<OrderItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = items.get(position).getProduct();
        holder.item_productName.setText(product.getName());
        holder.item_productDesc.setText(product.getDescription());
        holder.item_productPrice.setText(String.valueOf(product.getPrice()));
        holder.item_productQuantity.setText(String.valueOf(items.get(position).getQuantity()));
        Glide.with(context).load(product.getImageUrl()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return (int) items.stream().count();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView item_productName;
        private TextView item_productDesc;
        private TextView item_productPrice;
        private TextView item_productQuantity;
        private ImageView image;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            item_productName = itemView.findViewById(R.id.item_productName);
            item_productDesc = itemView.findViewById(R.id.item_productDesc);
            item_productPrice = itemView.findViewById(R.id.item_productPrice);
            item_productQuantity = itemView.findViewById(R.id.item_productQuantity);
            image = itemView.findViewById(R.id.product_image);

        }
    }
}
