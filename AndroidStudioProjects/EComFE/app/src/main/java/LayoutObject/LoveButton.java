package LayoutObject;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import API.RetrofitClient;
import API.WishlistApi;
import Domain.WishlistItem;
import Domain.WishlistRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoveButton {
    private View button;
    private final AppCompatActivity context;
    private final WishlistApi endpoint;
    private final OnWishlistToggleListener listener;

    public LoveButton(String productId,View bindview, AppCompatActivity context, OnWishlistToggleListener listener)
    {
        button = bindview;
        this.context = context;
        Retrofit retrofit = RetrofitClient.getClient();
        endpoint = retrofit.create(WishlistApi.class);
        this.listener = listener;
        this.productId = productId;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    private String productId;

    public void addToWishlist(String userId)
    {
        WishlistRequest request = new WishlistRequest(userId,productId);
        Call<WishlistItem> call = endpoint.addToWishList(request);
        call.enqueue(new Callback<WishlistItem>() {
            @Override
            public void onResponse(Call<WishlistItem> call, Response<WishlistItem> response) {
                Log.d("LOVE_BUTTON",response.message());
                if (response.isSuccessful()) {
                    listener.onAdd();
                    Toast.makeText(context, "Added to wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to connect to server", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<WishlistItem> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void removeFromWishlist(String userid){
        Call<Void> call = endpoint.removeFromWishList(userid,productId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onRemove();
                    Toast.makeText(context, "Removed from wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to connect to server", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface OnWishlistToggleListener
    {
        void onAdd();
        void onRemove();
    }
}
