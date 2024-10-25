package Adapter;

import android.content.Context;
import android.widget.Toast;
import Domain.User;
import com.google.firebase.auth.FirebaseAuth;

public class AuthAdapter {
    private final FirebaseAuth mAuth;

    public AuthAdapter() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void loginUser(User user, Context context, AuthListener listener) {
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listener.onSuccess();
            } else {
                Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                listener.onFailure(task.getException());
            }
        });
    }

    public void registerUser(User user, Context context, AuthListener listener) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listener.onSuccess();
            } else {
                Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show();
                listener.onFailure(task.getException());
            }
        });
    }

    public interface AuthListener {
        void onSuccess();
        void onFailure(Exception e);
    }
}