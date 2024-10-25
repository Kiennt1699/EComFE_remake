package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.myapplication.databinding.ActivityLoginBinding;

import Adapter.AuthAdapter;
import Domain.User;


public class LoginActivity2 extends BaseActivity {
    ActivityLoginBinding binding;
    private AuthAdapter authAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authAdapter = new AuthAdapter();
        setVariable();

    }

    private void setVariable() {
        binding.loginBtn.setOnClickListener(view -> {
            String email = binding.userEdit.getText().toString();
            String password = binding.passEdit.getText().toString();
            if (password.length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(email, password);
            authAdapter.registerUser(user, this, new AuthAdapter.AuthListener() {
                @Override
                public void onSuccess() {
                    startActivity(new Intent(LoginActivity2.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(LoginActivity2.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}