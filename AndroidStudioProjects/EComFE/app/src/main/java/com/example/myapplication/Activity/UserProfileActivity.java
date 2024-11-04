package com.example.myapplication.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class UserProfileActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView userNameTextView, userEmailTextView;
    private EditText nameEdit, addressEdit, phoneNumberEdit, userEdit;
    private Button updateProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile); // Make sure this matches your XML layout file name

        // Initialize UI components
        profileImageView = findViewById(R.id.profileImageView);
        userNameTextView = findViewById(R.id.userNameTextView);
        userEmailTextView = findViewById(R.id.userEmailTextView);
        nameEdit = findViewById(R.id.nameEdit);
        addressEdit = findViewById(R.id.addressEdit);
        phoneNumberEdit = findViewById(R.id.phoneNumberEdit);
        userEdit = findViewById(R.id.userEdit);
        updateProfileBtn = findViewById(R.id.updateProfileBtn);

        // Load the current user data
        loadUserProfile();

        // Set up button click listener
        updateProfileBtn.setOnClickListener(v -> updateProfile());
     
    }

    private void loadUserProfile() {
        User currentUser = User.getCurrentUser(); // Get the currently logged-in user

        // Display current user data
        userNameTextView.setText(currentUser.getName());
        userEmailTextView.setText(currentUser.getEmail());
        nameEdit.setText(currentUser.getName());
        addressEdit.setText(currentUser.getAddress());
        phoneNumberEdit.setText(currentUser.getPhoneNumber());
        userEdit.setText(currentUser.getEmail()); // Display email address
    }

    private void updateProfile() {
        String name = nameEdit.getText().toString().trim();
        String address = addressEdit.getText().toString().trim();
        String phoneNumber = phoneNumberEdit.getText().toString().trim();
        String email = userEdit.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        performUpdateProfile(name, address, phoneNumber, email);
    }

    private void performUpdateProfile(String name, String address, String phoneNumber, String email) {
        Retrofit retrofit = RetrofitClient.getClient();
        AuthApi authApi = retrofit.create(AuthApi.class);

        User currentUser = User.getCurrentUser(); // Get the currently logged-in user

        // Create an updated User object
        User updatedUser = new User(name, email, currentUser.getPassword(), address, phoneNumber); // Keep the existing password

        // Call the API to update the user profile
        Call<User> call = authApi.update(updatedUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User newUserData = response.body(); // Get the updated user data from the response

                    // Update the displayed user information with the new data
                    userNameTextView.setText(newUserData.getName());
                    userEmailTextView.setText(newUserData.getEmail());
                    nameEdit.setText(newUserData.getName());
                    addressEdit.setText(newUserData.getAddress());
                    phoneNumberEdit.setText(newUserData.getPhoneNumber());
                    userEdit.setText(newUserData.getEmail());
                    Toast.makeText(UserProfileActivity.this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserProfileActivity.this, "Failed to update profile. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserProfileActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
