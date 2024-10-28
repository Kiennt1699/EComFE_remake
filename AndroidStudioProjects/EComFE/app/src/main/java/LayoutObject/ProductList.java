package LayoutObject;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.DetailActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import API.ProductApi;
import API.RetrofitClient;
import API.WishlistApi;
import Adapter.ProductlistAdapter;
import Domain.Products;
import Domain.User;
import Domain.WishlistItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductList {
    private final RecyclerView bindedView;
    private final ProductlistAdapter adapter;
    private final ArrayList<ProductCard> items;
    private final AppCompatActivity context;
    private final ProductApi endpoint;

    private ActivityResultLauncher<Intent> launcher;

    public ProductList(RecyclerView view, AppCompatActivity context) {
        bindedView = view;
        bindedView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        items = new ArrayList<>();
        ProductlistAdapter.OnClickListener listener = new ProductlistAdapter.OnClickListener() {
            @Override
            public void onProductClick(View view, ProductCard item, int index) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("product", item.getItem()); // Pass the selected product
                launcher.launch(intent);
            }

            @Override
            public void onLovedClick(View view, ProductCard item, int index) {
                if(view.isActivated()) {
                    LoveButton.removeFromWishlist(context, User.getCurrentUser().getUserId(),item.getItem().getProductId());
                    item.getItem().setWishlisted(false);
                } else {
                    LoveButton.addToWishlist(context, User.getCurrentUser().getUserId(),item.getItem().getProductId());
                    item.getItem().setWishlisted(true);
                }
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onAddToCartClick(View view, ProductCard item, int index) {

            }
        };
        adapter = new ProductlistAdapter(items,listener);
        this.context = context;
        bindedView.setAdapter(adapter);
        Retrofit retrofit = RetrofitClient.getClient();
        endpoint = retrofit.create(ProductApi.class);
        launcher =  context.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> context.recreate());
        fetchProductlistData();

    }

    public void fetchProductlistData(){
        Call<List<Products>> call = endpoint.getProducts(User.getCurrentUser().getUserId());
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                context.findViewById(R.id.progressBarWishlist).setVisibility(RecyclerView.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    // Add the fetched products to the product list
                    items.clear(); // Clear the list first (if needed)
                    for(int i = 0; i< response.body().size();++i)
                    {
                        final int index = i;
                        Products item = response.body().get(i);
                        LoveButton button = bindButton(item, bindedView,context,adapter, index);
                        items.add(new ProductCard(item,button));
                    }

                    // Notify the adapter that the data set has changed
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Failed to load products in wishlist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    static LoveButton bindButton(Products item, View bindedView, AppCompatActivity context, ProductlistAdapter adapter, int index)
    {
        return new LoveButton(
                item.getProductId(),
                bindedView.findViewById(R.id.loveBtn),
                context,
                new LoveButton.OnWishlistToggleListener() {
                    @Override
                    public void onAdd() {

                    }

                    @Override
                    public void onRemove() {

                    }
                });

    }
}
