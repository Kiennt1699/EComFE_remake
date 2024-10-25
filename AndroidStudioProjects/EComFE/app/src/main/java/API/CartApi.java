package API;

import Domain.AddToCartRequest;
import Domain.CartItem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface CartApi {
    @GET("api/carts/GetCartItems") // Define the correct endpoint for fetching cart data.
    Call<List<CartItem>> getCartItems();
    @DELETE("api/Carts/DeleteCartItem/{cartItemId}")
    Call<Void> deleteCartItem(@Path("cartItemId") String cartItemId);
    @POST("api/carts/AddCartItem")
    Call<Void> addToCart(@Body AddToCartRequest request);
}
