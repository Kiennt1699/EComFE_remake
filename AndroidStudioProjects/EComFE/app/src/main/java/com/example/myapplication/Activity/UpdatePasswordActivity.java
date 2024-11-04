package com.example.myapplication.Activity;

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
import Domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdatePasswordActivity extends AppCompatActivity {

    private EditText newPasswordEdit, confirmNewPasswordEdit;
    private Button confirmUpdatePasswordBtn;
    private TextView passwordUpdateMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        // Initialize UI components
        newPasswordEdit = findViewById(R.id.newPasswordEdit);
        confirmNewPasswordEdit = findViewById(R.id.confirmNewPasswordEdit);
        confirmUpdatePasswordBtn = findViewById(R.id.confirmUpdatePasswordBtn);
        passwordUpdateMessage = findViewById(R.id.passwordUpdateMessage);

        // Set up button click listener
        confirmUpdatePasswordBtn.setOnClickListener(v -> updatePassword());
    }

    private void updatePassword() {
        // Get user input from the EditText fields
        String newPassword = newPasswordEdit.getText().toString().trim();
        String confirmPassword = confirmNewPasswordEdit.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "New Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Proceed to update password
        performUpdatePassword(newPassword);
    }

    private void performUpdatePassword(String newPassword) {
        Retrofit retrofit = RetrofitClient.getClient();
        AuthApi authApi = retrofit.create(AuthApi.class);

        User currentUser = User.getCurrentUser(); // Get the currently logged-in user

        // Create an updated User object
        User updatedUser = new User(currentUser.getName(), currentUser.getEmail(), newPassword, currentUser.getAddress(), currentUser.getPhoneNumber());

        // Call the API to update the password
        Call<User> call = authApi.update(updatedUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    passwordUpdateMessage.setText("Password updated successfully.");
                    Toast.makeText(UpdatePasswordActivity.this, "Password updated successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    passwordUpdateMessage.setText("Failed to update password. Please try again.");
                    Toast.makeText(UpdatePasswordActivity.this, "Failed to update password. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                passwordUpdateMessage.setText("Error: " + t.getMessage());
                Toast.makeText(UpdatePasswordActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
