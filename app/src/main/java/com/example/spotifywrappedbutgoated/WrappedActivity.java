package com.example.spotifywrappedbutgoated;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class WrappedActivity extends AppCompatActivity {

    private SongService songService;
    private ArtistService artistService;
    private ArrayList<Song> topTracks;
    private ArrayList<Artist> topArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapped);

        SharedPreferences sharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
        songService = new SongService(getApplicationContext());
        getTracks();
        artistService = new ArtistService(getApplicationContext());
        getArtists();

        System.out.println(topTracks);
    }

    private void getTracks() {
        songService.getTopTracks(() -> {
            topTracks = songService.getSongs();
        });
    }

    private void getArtists() {
        artistService.getTopArtists(() -> {
            topArtists = artistService.getArtists();
        });
    }
}