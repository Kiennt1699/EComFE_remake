package API;

import Domain.WishlistItem;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface WishlistApi {
    @GET("api/wishlist")
    Call<List<WishlistItem>> getWishlistItems();
}
