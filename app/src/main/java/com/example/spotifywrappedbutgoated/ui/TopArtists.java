package com.example.spotifywrappedbutgoated.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifywrappedbutgoated.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TopArtists extends AppCompatActivity {
    ListView listView;
    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;
    ArrayList<ArtistData> artistList = new ArrayList<ArtistData>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_artists);
        listView = (ListView) findViewById(R.id.listOfSongs);

        artistList.add(new ArtistData("The Weeknd", "IDK"));
        artistList.add(new ArtistData("Lana Del Ray", "IDK"));
        artistList.add(new ArtistData("Dominic Fike", "IDK"));
        artistList.add(new ArtistData("Ariana Grande", "IDK"));
        artistList.add(new ArtistData("Travis Scott", "IDK"));

        ArtistAdapter artistAdapter = new ArtistAdapter(this, android.R.layout.simple_list_item_1, artistList);
        listView.setAdapter(artistAdapter);

        clickRight = (FloatingActionButton) findViewById(R.id.topArtistsClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.topArtistsClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                startActivity(myIntent);
            }
        });

    }
}