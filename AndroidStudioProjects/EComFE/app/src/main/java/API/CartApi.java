package API;

import Domain.AddToCartRequest;
import Domain.CartItem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface CartApi {
    @GET("api/carts/GetCartItems") // Define the correct endpoint for fetching cart data.
    Call<List<CartItem>> getCartItems(@Query("userid") String userid);
    @DELETE("api/Carts/DeleteCartItem/{productId}")
    Call<Void> deleteCartItem( @Path("productId") String productId, @Query("userid") String userid);
    @POST("api/carts/AddCartItem")
    Call<Void> addToCart(@Query("userid") String userid,@Body AddToCartRequest request);
}
