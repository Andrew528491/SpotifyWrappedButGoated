package com.example.spotifywrappedbutgoated;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences.Editor editor;
    private SharedPreferences msharedPreferences;
    private RequestQueue queue;
    private ArrayList<Song> topTracks;
    private boolean flag;

    private static final String CLIENT_ID = "2ba604432e854103b6e06527656074cc";
    private static final String REDIRECT_URI = "com.spotifywrappedbutgoated://callback";
    private static final int REQUEST_CODE = 1337;
    private static final String SCOPES = "user-top-read,user-read-recently-played";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        authenticateSpotify();

        msharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
        queue = Volley.newRequestQueue(this);
    }

    private void authenticateSpotify(){
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{SCOPES});
        AuthorizationRequest request = builder.build();
        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the login call
        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                // Successful response, authorization token has been received
                case TOKEN:
                    editor = getSharedPreferences("SPOTIFY", 0).edit();
                    editor.putString("token", response.getAccessToken());
                    Log.d("STARTING", "GOT AUTH TOKEN");
                    editor.commit();
                    waitForUserInfo();
                    break;

                // Auth flow returned an error
                case ERROR:
                    displayExceptionMessage(getStringByIdName(this, "authorization_error"), getStringByIdName(this, "authorization_error_description_spotify"), this);
                    break;

                default:
                    displayExceptionMessage(getStringByIdName(this, "authorization_error"), getStringByIdName(this, "authorization_error_description_appbackend"), this);
                    break;
            }
        }
    }

    private void displayExceptionMessage(String title, String msg, Activity reportActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(reportActivity);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setNeutralButton("I understand",
                new DialogInterface.OnClickListener() {
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

    private void waitForUserInfo() {
        //This method should be changed in some way to get the user ID and password
        startWrappedActivity();
    }

    private void startWrappedActivity() {
        Intent newintent = new Intent(LoginActivity.this, WrappedActivity.class);
        startActivity(newintent);
    }

}