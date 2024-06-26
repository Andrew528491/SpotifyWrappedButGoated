package com.example.spotifywrappedbutgoated.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrappedbutgoated.ArtistService;
import com.example.spotifywrappedbutgoated.R;
import com.example.spotifywrappedbutgoated.SongService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TopArtists extends AppCompatActivity {
    ListView listView;
    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;
    ArrayList<ArtistData> artistList = new ArrayList<ArtistData>();

    ArtistService artistService;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_artists);
        Intent intent = getIntent();
        String userText = intent.getStringExtra("username");
        String passText = intent.getStringExtra("password");
        Log.d("TopArtists", "Username received: " + userText);
        artistService = new ArtistService(getApplicationContext());

        artistService.getTopArtists(() -> {
            runOnUiThread(() -> {  // UI updates must happen on the main thread
                artistList = artistService.getArtists();
                listView = (ListView) findViewById(R.id.listTopArtists);

                ArtistAdapter artistAdapter = new ArtistAdapter(this, android.R.layout.simple_list_item_1, artistList);
                listView.setAdapter(artistAdapter);
            });
        }, WrappedFilter.getTimespan());

        clickRight = (FloatingActionButton) findViewById(R.id.topArtistsClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), NewArtists.class);
                myIntent.putExtra("username", userText);
                myIntent.putExtra("password", passText);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.topArtistsClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TopSongs.class);
                myIntent.putExtra("username", userText);
                myIntent.putExtra("password", passText);
                startActivity(myIntent);
            }
        });

    }
}