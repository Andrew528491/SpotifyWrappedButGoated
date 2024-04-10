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

        artistService = new ArtistService(getApplicationContext());

        artistService.getTopArtists(() -> {
            artistList = artistService.getArtists();
            Log.i("TEST", artistList.get(0).getArtist());
            Log.i("TEST", artistList.get(1).getArtist());
            System.out.println(artistList);
        }, WrappedFilter.getTimespan());


        listView = (ListView) findViewById(R.id.listTopArtists);


        ArtistAdapter artistAdapter = new ArtistAdapter(this, android.R.layout.simple_list_item_1, artistList);
        listView.setAdapter(artistAdapter);

        clickRight = (FloatingActionButton) findViewById(R.id.topArtistsClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), NewArtists.class);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.topArtistsClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TopSongs.class);
                startActivity(myIntent);
            }
        });

    }
}