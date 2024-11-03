package API;

import java.util.List;

import Domain.LoginRequest;
import Domain.LoginResponse;
import Domain.Order;
import Domain.Products;
import Domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderApi {

    @GET("api/Orders/GetInforOrders") // Assuming your C# API endpoint is /api/products
    Call<List<Order>> getOrdersByUserId(@Query("userId")String userId);

}
