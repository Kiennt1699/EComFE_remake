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
            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
            // Navigate to the login page after successful signup
            Intent intent = new Intent(SignupActivity2.this, LoginActivity2.class);
            startActivity(intent);
            finish();
        }
    }
}
