package Adapter;

import android.content.Context;
import android.widget.Toast;
import API.AuthApi;
import API.RetrofitClient;
import Domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthAdapter {
    private final AuthApi authApi;

    // Constructor
    public AuthAdapter() {
        authApi = RetrofitClient.getClient().create(AuthApi.class);
    }

    // Đăng nhập
    public void loginUser(User user, Context context, AuthListener listener) {
        Call<User> call = authApi.login(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                } else {
                    Toast.makeText(context, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                    listener.onFailure(new Exception("Login failed with error code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                listener.onFailure(t);
            }
        });
    }

    // Đăng ký
    public void registerUser(User user, Context context, AuthListener listener) {
        Call<User> call = authApi.signup(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                } else {
                    Toast.makeText(context, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                    listener.onFailure(new Exception("Registration failed with error code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                listener.onFailure(t);
            }
        });
    }

    public interface AuthListener {
        void onSuccess();
        void onFailure(Throwable e);
    }
}
