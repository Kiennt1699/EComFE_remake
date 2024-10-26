package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.R;
public class LoginActivity2 extends AppCompatActivity {

    private EditText userEdit, passEdit;
    private Button loginBtn;
    private TextView forgotPassword, signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            // Perform login logic here
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

            // Navigate to MainActivity upon successful login
            Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
