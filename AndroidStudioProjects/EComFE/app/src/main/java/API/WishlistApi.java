package API;

import Domain.WishlistItem;
import Domain.WishlistRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

import java.util.List;

public interface WishlistApi {
    @GET("api/wishlist")
    Call<List<WishlistItem>> getWishlistItems();

    @POST("api/wishlist")
    Call<WishlistItem> addToWishList(@Body WishlistRequest request);

    @DELETE("api/wishlist/{userid}/{productid}")
    Call<Void> removeFromWishList(@Path("userid")String userid, @Path("productid") String productid);
}

