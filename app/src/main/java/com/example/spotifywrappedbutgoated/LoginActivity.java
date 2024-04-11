package com.example.spotifywrappedbutgoated;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences.Editor editor;
    private SharedPreferences msharedPreferences;
    private RequestQueue queue;
    private boolean flag;

    private static final String CLIENT_ID = "2ba604432e854103b6e06527656074cc";
    private static final String REDIRECT_URI = "com.spotifywrappedbutgoated://callback";
    private static final int REQUEST_CODE = 1337;
    private static final String SCOPES = "user-top-read,user-read-recently-played";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText username;
    EditText password;
    Button signupButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        msharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
        queue = Volley.newRequestQueue(this);

        signupButton.setOnClickListener(v -> {

            String userText = username.getText().toString().trim();
            String passText = password.getText().toString().trim();

            if (!userText.equals("") && !passText.equals("")) {
                Map<String, String> newUser = new HashMap<>();
                newUser.put("password", passText);

                // Add a new document to the "users" collection
                db.collection("users").document(userText)
                        .set(newUser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                authenticateSpotify();
                                Toast.makeText(getApplicationContext(), "Signup Successful", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(getApplicationContext(), "Username and password must be filled out", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(v -> {

            String userText = username.getText().toString().trim();
            String passText = password.getText().toString().trim();

            db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                boolean correctPass = false;
                boolean userExists = false;
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot user : task.getResult()) {
                            if (user.getId().trim().equals(userText)) {
                                userExists = true;
                                if (user.getData().values().toString().trim().equals("["+passText+"]")) {
                                    correctPass = true;
                                    authenticateSpotify();
                                    goToUpdateLogin();
                                }
                            }
                        }
                    if (!userExists) {
                        Toast.makeText(getApplicationContext(), "That username doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                    if (!correctPass) {
                        Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    private void authenticateSpotify() {
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{SCOPES});
        AuthorizationRequest request = builder.build();
        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                case TOKEN:
                    editor = getSharedPreferences("SPOTIFY", 0).edit();
                    editor.putString("token", response.getAccessToken());
                    editor.apply();
                    Log.d("STARTING", "GOT AUTH TOKEN");
                    // Handle successful Spotify authentication, such as navigating to another activity
                    break;
                case ERROR:
                    // Handle Spotify authentication error
                    displayExceptionMessage(getStringByIdName(this, "authorization_error"), getStringByIdName(this, "authorization_error_description_spotify"), this);
                    break;
                default:
                    // Handle other cases, such as user cancellation
                    displayExceptionMessage(getStringByIdName(this, "authorization_error"), getStringByIdName(this, "authorization_error_description_appbackend"), this);
                    break;
            }
        }
    }

    private void displayExceptionMessage(String title, String msg, Activity reportActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(reportActivity);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setNeutralButton("I understand", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private static String getStringByIdName(Context context, String idName) {
        Resources res = context.getResources();
        return res.getString(res.getIdentifier(idName, "string", context.getPackageName()));
    }

    public void goToUpdateLogin() {
        String userText = username.getText().toString().trim();
        String passText = password.getText().toString().trim();

        Intent intent = new Intent(LoginActivity.this, UpdateLogin.class);
        intent.putExtra("username", userText);
        intent.putExtra("password", passText);
        startActivity(intent);
    }

}
