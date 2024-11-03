package API;

import Domain.LoginRequest;
import Domain.LoginResponse;
import Domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("api/Auth/login")
    Call<LoginResponse> login(@Body LoginRequest user);

    @POST("api/Auth/register")
    Call<User> signup(@Body User user);

    @POST("api/Auth/update")
    Call<User> update(@Body User user);
}
