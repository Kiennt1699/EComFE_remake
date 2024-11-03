package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Objects;

import Domain.Order;
import Domain.Product;
import Domain.PurchasedProduct;

public class PurchasedProductAdapter extends RecyclerView.Adapter<PurchasedProductAdapter.PurchasedProductViewHolder> {
    private Context context;
    ArrayList<Order> items;


    public PurchasedProductAdapter(ArrayList<Order> items ) {

        this.items = items;
    }

    @NonNull
    @Override
    public PurchasedProductAdapter.PurchasedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_purchase_history, parent, false);
        return new PurchasedProductAdapter.PurchasedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedProductAdapter.PurchasedProductViewHolder holder, int position) {
            Order purchasedProduct = items.get(position);
            holder.orderId.setText(purchasedProduct.getOrderId());
            holder.status.setText(purchasedProduct.getStatus());
            holder.status.setText(Objects.equals(purchasedProduct.getStatus(), "0") ? "Delivered" : purchasedProduct.getStatus());
            holder.orderedLocation.setText(String.valueOf(purchasedProduct.getShippingAddress()));
            holder.orderPayMethod.setText(purchasedProduct.getPaymentMethod());
            holder.reciveTime.setText("00:00:00");

        ProductAdapter productAdapter = new ProductAdapter(purchasedProduct.getItems());
        holder.productList.setAdapter(productAdapter);
        holder.productList.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PurchasedProductViewHolder extends RecyclerView.ViewHolder {
        private TextView orderId;
        private TextView status;
        private RecyclerView productList;;

        private TextView orderedLocation;
        private TextView orderPayMethod;
        private TextView reciveTime;
        public PurchasedProductViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            status = itemView.findViewById(R.id.orderStatus);
            productList = itemView.findViewById(R.id.ordered_product_list);
            orderedLocation = itemView.findViewById(R.id.orderedLocation);
            orderPayMethod = itemView.findViewById(R.id.orderPayMethod);
            reciveTime = itemView.findViewById(R.id.reciveTime);
        }

    }
    public interface OnPurchasedProductClickListener {
        void onPurchaseProductClick(PurchasedProduct product);
    }
}
