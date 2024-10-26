package com.example.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class SignupActivity2 extends AppCompatActivity {

    private EditText nameEdit, emailEdit, addressEdit, phoneNumberEdit, passwordEdit, confirmPasswordEdit;
    private Button signupBtn;
    private TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        // Initialize fields
        nameEdit = findViewById(R.id.nameEdit);
        emailEdit = findViewById(R.id.userEdit);
        addressEdit = findViewById(R.id.addressEdit);
        phoneNumberEdit = findViewById(R.id.phoneNumberEdit);
        passwordEdit = findViewById(R.id.passEdit);
        confirmPasswordEdit = findViewById(R.id.confirmPasswordEdit);
        signupBtn = findViewById(R.id.signupBtn);
        loginText = findViewById(R.id.SignBtn);

        // Sign up when button is clicked
        signupBtn.setOnClickListener(v -> signup());

        // Switch to login page
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity2.this, LoginActivity2.class);
            startActivity(intent);
            finish();
        });
    }

    private void signup() {
        String name = nameEdit.getText().toString().trim();
        String email = emailEdit.getText().toString().trim();
        String address = addressEdit.getText().toString().trim();
        String phoneNumber = phoneNumberEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String confirmPassword = confirmPasswordEdit.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {
            // Signup logic (send data to your API, for example)
            checkSignup(new User(name,email,password, address, phoneNumber));
        }
    }

    private void checkSignup(User user)
    {
        Retrofit retrofit = RetrofitClient.getClient();
        AuthApi endpoint = retrofit.create(AuthApi.class);
        Context rootContext = this;
        Call<User> call = endpoint.signup(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(rootContext, "Signup successful", Toast.LENGTH_SHORT).show();
                    // Navigate to the login page after successful signup
                    Intent intent = new Intent(SignupActivity2.this, LoginActivity2.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(rootContext, "Login failed due to server:" + response.message(), Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(rootContext, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
