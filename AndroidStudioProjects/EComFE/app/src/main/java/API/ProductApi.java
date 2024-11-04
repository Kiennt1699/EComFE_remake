package API;

import Domain.Products;
import Domain.WishlistItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ProductApi {

    @GET("api/products") // Assuming your C# API endpoint is /api/products
    Call<List<Products>> getProducts(@Query("userId")String userId,@Query("categoryIds")String category);  // This will return a list of Product objects
}