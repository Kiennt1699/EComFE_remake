package API;

import Domain.CheckoutRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import retrofit2.http.Query;


public interface OrderApi {
    @POST("api/Orders/checkout")
    Call<Void> checkout(@Query("userid") String userId, @Body CheckoutRequest checkoutRequest);
}
