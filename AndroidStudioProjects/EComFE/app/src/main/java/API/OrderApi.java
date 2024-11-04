package API;

import java.util.List;

import Domain.CheckoutRequest;
import Domain.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Query;


public interface OrderApi {
    @POST("api/Orders/checkout")
    Call<Void> checkout(@Query("userid") String userId, @Body CheckoutRequest checkoutRequest);

    @GET("api/Orders/GetInforOrders") // Assuming your C# API endpoint is /api/products
    Call<List<Order>> getOrdersByUserId(@Query("userId")String userId);

}
