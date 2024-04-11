package com.example.spotifywrappedbutgoated;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import com.example.spotifywrappedbutgoated.ui.wrappedui;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.spotifywrappedbutgoated.ui.TopSongs;
import com.example.spotifywrappedbutgoated.ui.WrappedFilter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;
import android.widget.ImageButton;

public class UpdateLogin extends AppCompatActivity {

    EditText updateUsername, updatePassword;
    Button updateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.updateLogin);
        updateUsername = findViewById(R.id.updateUsername);
        updatePassword = findViewById(R.id.updatePassword);

        Bundle extras = getIntent().getExtras();
        String oldUsername = "";

        if (extras != null) {
            oldUsername = extras.getString("username");
            String password = extras.getString("password");

            updateUsername.setText(oldUsername);
            updatePassword.setText(password);
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // If we change the user, rather than trying to change the individual user, we create a new one then delete the current one
        String finalOldUsername = oldUsername;
        updateButton.setOnClickListener(v -> {
            String newUsername = updateUsername.getText().toString().trim();
            String newPassword = updatePassword.getText().toString().trim();

            if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
                DocumentReference userDocRef = db.collection("users").document(finalOldUsername);
                if (!newUsername.equals(finalOldUsername)) {
                    Map<String, Object> newUser = new HashMap<>();
                    newUser.put("password", newPassword);
                    db.collection("users").document(newUsername).set(newUser)
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(UpdateLogin.this, "Username and Password updated successfully", Toast.LENGTH_SHORT).show();

                                userDocRef.delete();
                            })
                            .addOnFailureListener(e -> Toast.makeText(UpdateLogin.this, "Update failed", Toast.LENGTH_SHORT).show());
                } else {
                    userDocRef.update("password", newPassword)  // if its just password, we just update password
                            .addOnSuccessListener(unused -> Toast.makeText(UpdateLogin.this, "Password updated successfully", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(UpdateLogin.this, "Update failed", Toast.LENGTH_SHORT).show());
                }
            } else {
                Toast.makeText(UpdateLogin.this, "Username and Password cannot be empty", Toast.LENGTH_SHORT).show();
            }
            Intent myIntent = new Intent(getApplicationContext(), WrappedFilter.class);
            startActivity(myIntent);
        });

        Button backToWrappedUIButton = findViewById(R.id.backToWrappedUIButton);
        backToWrappedUIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedUsername = updateUsername.getText().toString().trim();
                String updatedPassword = updatePassword.getText().toString().trim();

                Intent intent = new Intent(UpdateLogin.this, wrappedui.class);

                intent.putExtra("username", updatedUsername);
                intent.putExtra("password", updatedPassword);

                startActivity(intent);

                finish();
            }
        });

    }


}
