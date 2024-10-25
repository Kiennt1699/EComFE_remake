package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.example.myapplication.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Activity.MainActivity;

public class SignupActivity2 extends AppCompatActivity {

    private EditText emailEdit, passwordEdit, confirmPasswordEdit;
    private Button signupBtn;
    private TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        emailEdit = findViewById(R.id.userEdit);
        passwordEdit = findViewById(R.id.passEdit);
        signupBtn = findViewById(R.id.signupBtn);
        loginText = findViewById(R.id.textView10);

        // Đăng ký khi nhấn nút
        signupBtn.setOnClickListener(v -> signup());

        // Chuyển sang trang đăng nhập
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity2.this, LoginActivity2.class);
            startActivity(intent);
            finish();
        });
    }

    private void signup() {
        String email = emailEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String confirmPassword = confirmPasswordEdit.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {
            // Logic đăng ký
            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
            // Chuyển hướng vào trang chính (MainActivity) khi đăng ký thành công
            Intent intent = new Intent(SignupActivity2.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
