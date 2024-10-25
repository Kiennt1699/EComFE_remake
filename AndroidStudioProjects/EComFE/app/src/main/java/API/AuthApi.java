package API;

import Domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("api/Auth/login")
    Call<User> login(@Body User user);

    @POST("api/Auth/register")
    Call<User> signup(@Body User user);
}
