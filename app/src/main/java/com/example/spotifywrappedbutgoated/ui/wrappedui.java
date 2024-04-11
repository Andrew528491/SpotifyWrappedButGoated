package com.example.spotifywrappedbutgoated.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


import com.example.spotifywrappedbutgoated.ArtistService;
import com.example.spotifywrappedbutgoated.SongService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



import com.example.spotifywrappedbutgoated.R;

public class wrappedui extends AppCompatActivity {

    ListView songListview;
    ListView artistListview;
    ArrayList<SongData> songList = new ArrayList<>();
    ArrayList<ArtistData> artistList = new ArrayList<>();

    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;

    SongService songService;
    ArtistService artistService;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrappedui);

        songService = new SongService(getApplicationContext());
        artistService = new ArtistService(getApplicationContext());
        songService.getTopTracks(() -> {
            Log.i("TEST", "test");
            runOnUiThread(() -> {  // UI updates must happen on the main thread
                songList = songService.getSongs();  // Retrieve updated song list
                songListview = (ListView) findViewById(R.id.songWrappedList);
                WrappedUISongAdapter songAdapter = new WrappedUISongAdapter(this, android.R.layout.simple_list_item_1, songList);
                songListview.setAdapter(songAdapter);
            });

            Log.i("TEST", "test");
            Log.i("TEST", songList.get(0).getSong());
            Log.i("TEST", songList.get(1).getSong());

            System.out.println(songList);
        }, WrappedFilter.getTimespan());



            artistService.getTopArtists(() -> {
                Log.i("TEST", "test");
                runOnUiThread(() -> {  // UI updates must happen on the main thread
                    artistList = artistService.getArtists();
                    artistListview = (ListView) findViewById(R.id.artistWrappedList);


                    WrappedUIArtistAdapter artistAdapter = new WrappedUIArtistAdapter(this, android.R.layout.simple_list_item_1, artistList);
                    artistListview.setAdapter(artistAdapter);
                });

                Log.i("TEST", "test");
                Log.i("TEST", artistList.get(0).getArtist());
                Log.i("TEST", artistList.get(1).getArtist());

                System.out.println(artistList);
            }, WrappedFilter.getTimespan());

        songListview = (ListView) findViewById(R.id.songWrappedList);
        artistListview = (ListView) findViewById(R.id.artistWrappedList);
        WrappedUISongAdapter songAdapter = new WrappedUISongAdapter(this, android.R.layout.simple_list_item_1, songList);
        songListview.setAdapter(songAdapter);
        WrappedUIArtistAdapter artistAdapter = new WrappedUIArtistAdapter(this, android.R.layout.simple_list_item_1, artistList);
        artistListview.setAdapter(artistAdapter);

        clickRight = (FloatingActionButton) findViewById(R.id.wrappedClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.wrappedClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), NewArtists.class);
                startActivity(myIntent);
            }
        });

    }
}
