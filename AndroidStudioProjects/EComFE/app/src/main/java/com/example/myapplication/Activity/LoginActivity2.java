package com.example.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import com.example.myapplication.R;

import API.AuthApi;
import API.RetrofitClient;
import Domain.LoginRequest;
import Domain.LoginResponse;
import Domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity2 extends AppCompatActivity {

    private EditText userEdit, passEdit;
    private Button loginBtn;
    private TextView forgotPassword, signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login2);

        userEdit = findViewById(R.id.userEdit);
        passEdit = findViewById(R.id.passEdit);
        loginBtn = findViewById(R.id.signupBtn);
        forgotPassword = findViewById(R.id.textView10);
        signUpText = findViewById(R.id.SignBtn);


        loginBtn.setOnClickListener(v -> login());


        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity2.this, SignupActivity2.class);
            startActivity(intent);
        });


        forgotPassword.setOnClickListener(v -> {

            Toast.makeText(LoginActivity2.this, "Forgot Password clicked", Toast.LENGTH_SHORT).show();
        });
    }

    private void login() {
        String email = userEdit.getText().toString().trim();
        String password = passEdit.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
        } else {
            checklogin(email,password);
        }
    }

    private void checklogin(String email, String password)
    {
        Retrofit retrofit = RetrofitClient.getClient();
        AuthApi endpoint = retrofit.create(AuthApi.class);
        Context rootContext = this;
        Call<LoginResponse> call = endpoint.login(new LoginRequest(email, password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(rootContext, "Login successful", Toast.LENGTH_SHORT).show();
                    User.setCurrentUser(response.body().getUser());
                    Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (response.code() == 401) {
                    Toast.makeText(rootContext, "Email or password is wrong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(rootContext, "Login failed due to server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(rootContext, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
