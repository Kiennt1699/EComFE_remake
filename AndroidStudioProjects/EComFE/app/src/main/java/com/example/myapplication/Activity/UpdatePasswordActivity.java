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

    private EditText currentPasswordEdit, newPasswordEdit, confirmNewPasswordEdit;
    private Button confirmUpdatePasswordBtn;
    private TextView passwordUpdateMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

//        currentPasswordEdit = findViewById(R.id.currentPasswordEdit);
        newPasswordEdit = findViewById(R.id.newPasswordEdit);
        confirmNewPasswordEdit = findViewById(R.id.confirmNewPasswordEdit);
        confirmUpdatePasswordBtn = findViewById(R.id.confirmUpdatePasswordBtn);
        passwordUpdateMessage = findViewById(R.id.passwordUpdateMessage);

        confirmUpdatePasswordBtn.setOnClickListener(v -> updatePassword());
    }

    private void updatePassword() {
        String currentPassword = currentPasswordEdit.getText().toString().trim();
        String newPassword = newPasswordEdit.getText().toString().trim();
        String confirmPassword = confirmNewPasswordEdit.getText().toString().trim();


        if (TextUtils.isEmpty(currentPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "New Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        performUpdatePassword(currentPassword, newPassword);
    }

    private void performUpdatePassword(String currentPassword, String newPassword) {
        Retrofit retrofit = RetrofitClient.getClient();
        AuthApi authApi = retrofit.create(AuthApi.class);

        User currentUser = User.getCurrentUser(); // Get the currently logged-in user

//        String storedPassword = User.getCurrentUser().getPassword();
//        if (storedPassword == null) {
//            passwordUpdateMessage.setText("Password not found for the current user.");
//            Toast.makeText(this, "Current password (" + currentUser.getPassword() + ") is incorrect.", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Current password (" + currentUser.getEmail() + ") is incorrect.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (!currentPassword.equals(storedPassword)) {
//            passwordUpdateMessage.setText("Current password is incorrect.");
//            return;
//        }
        // Prepare the updated user object
        User updatedUser = new User(currentUser.getName(), currentUser.getEmail(), newPassword, currentUser.getAddress(), currentUser.getPhoneNumber());

        Call<User> call = authApi.update(updatedUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful() && response.body() != null) {
                    passwordUpdateMessage.setText("Password updated successfully.");
                } else {
                    passwordUpdateMessage.setText("Failed to update password. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                passwordUpdateMessage.setText("Error: " + t.getMessage());
            }
        });
    }
}
