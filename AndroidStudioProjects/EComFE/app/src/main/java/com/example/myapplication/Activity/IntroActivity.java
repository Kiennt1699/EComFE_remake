package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

        // Automatically navigate to MainActivity after 3 seconds
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Navigate to MainActivity after 3 seconds
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close IntroActivity so it's not in the back stack
            }
        }, 1500); // 3000 milliseconds delay (3 seconds)
    }

    private void setVariable() {
            binding.loginBtn.setOnClickListener(view -> {
                if(mAuth.getCurrentUser() != null) {
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(IntroActivity.this, LoginActivity2.class));
                }
            });
        binding.loginBtn.setOnClickListener( v -> startActivity(new Intent(IntroActivity.this, SignupActivity2.class)));
    }
}