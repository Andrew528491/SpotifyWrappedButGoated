package com.example.spotifywrappedbutgoated;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
//import com.example.spotifywrappedbutgoated.ui.TopSongs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WrappedActivity extends AppCompatActivity {

    private SongService songService;
    private ArtistService artistService;
    private ArrayList<Song> topTracks;
    private ArrayList<Artist> topArtists;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Hello Wo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapped);

        SharedPreferences sharedPreferences = this.getSharedPreferences("SPOTIFY", 0);
        songService = new SongService(getApplicationContext());
        artistService = new ArtistService(getApplicationContext());
        getTracks();
        getArtists();

    }

    private void getTracks() {
        songService.getTopTracks(() -> {
            topTracks = songService.getSongs();
            System.out.println(topTracks);
        });
    }

    private void getArtists() {
        artistService.getTopArtists(() -> {
            topArtists = artistService.getArtists();
            System.out.println(topArtists);
        });
    }
}