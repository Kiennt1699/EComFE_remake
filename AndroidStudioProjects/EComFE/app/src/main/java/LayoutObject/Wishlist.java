package LayoutObject;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import API.RetrofitClient;
import API.WishlistApi;
import Adapter.WishlistAdapter;
import Domain.WishlistItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Wishlist {
    private final RecyclerView bindedView;
    private final WishlistAdapter adapter;
    private final ArrayList<WishlistItem> items;
    private final AppCompatActivity context;
    private final WishlistApi endpoint;

    public Wishlist(RecyclerView view, AppCompatActivity context) {
        bindedView = view;
        bindedView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        items = new ArrayList<>();
        adapter = new WishlistAdapter(items);
        this.context = context;
        bindedView.setAdapter(adapter);
        Retrofit retrofit = RetrofitClient.getClient();
        endpoint = retrofit.create(WishlistApi.class);
        fetchWishlistData();
        context.findViewById(R.id.progressBarWishlist).setVisibility(RecyclerView.GONE);
    }

    public void fetchWishlistData(){
        Call<List<WishlistItem>> call = endpoint.getWishlistItems();
        call.enqueue(new Callback<List<WishlistItem>>() {
            @Override
            public void onResponse(Call<List<WishlistItem>> call, Response<List<WishlistItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Add the fetched products to the product list
                    items.clear(); // Clear the list first (if needed)
                    items.addAll(response.body());

                    // Notify the adapter that the data set has changed
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Failed to load products in wishlist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<WishlistItem>> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
