package com.example.spotifywrappedbutgoated.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.spotifywrappedbutgoated.NewArtistService;
import com.example.spotifywrappedbutgoated.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NewArtists extends AppCompatActivity {
    ListView listView;
    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;
    ArrayList<ArtistData> newArtistList = new ArrayList<ArtistData>();
    private NewArtistService newArtistService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_artists);
        Intent intent = getIntent();
        String userText = intent.getStringExtra("username");
        String passText = intent.getStringExtra("password");
        Log.d("NewArtists", "Username received: " + userText);
        newArtistService = new NewArtistService(getApplicationContext());
        System.out.println("TEST");
        newArtistService.getNewArtists(() -> {
            Log.i("TEST", "test");
            runOnUiThread(() -> {  // UI updates must happen on the main thread
                newArtistList = newArtistService.getArtists();
                listView = (ListView) findViewById(R.id.listNewArtists);


                ArtistAdapter artistAdapter = new ArtistAdapter(this, android.R.layout.simple_list_item_1, newArtistList);
                listView.setAdapter(artistAdapter);
            });

            Log.i("TEST", "test");

            System.out.println(newArtistList);
        });

        clickRight = (FloatingActionButton) findViewById(R.id.newArtistsClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                myIntent.putExtra("username", userText);
                myIntent.putExtra("password", passText);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.newArtistsClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(getApplicationContext(), TopArtists.class);
                myIntent.putExtra("username", userText);
                myIntent.putExtra("password", passText);
                startActivity(myIntent);
            }
        });


    }
}