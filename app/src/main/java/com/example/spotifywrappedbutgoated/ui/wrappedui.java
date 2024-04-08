package com.example.spotifywrappedbutgoated.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



import com.example.spotifywrappedbutgoated.R;

public class wrappedui extends AppCompatActivity {

    ListView songListview;
    ListView artistListview;
    ArrayList<String> songList = new ArrayList<>();
    ArrayList<String> artistList = new ArrayList<>();

    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrappedui);
        songListview = (ListView) findViewById(R.id.songWrappedList);
        artistListview = (ListView) findViewById(R.id.artistWrappedList);
        songList.add("Prisoner");
        songList.add("Ultraviolence");
        songList.add("Westcoast Collective");
        songList.add("We Can't Be Friends");
        songList.add("Nightcrawler");
        artistList.add("The Weeknd");
        artistList.add("Lana Del Ray");
        artistList.add("Dominic Fike");
        artistList.add("Ariana Grande");
        artistList.add("Travis Scott");
        WrappedUISongAdapter songAdapter = new WrappedUISongAdapter(this, android.R.layout.simple_list_item_1, songList);
        songListview.setAdapter(songAdapter);
        WrappedUIArtistAdapter artistAdapter = new WrappedUIArtistAdapter(this, android.R.layout.simple_list_item_1, artistList);
        artistListview.setAdapter(artistAdapter);

        clickRight = (FloatingActionButton) findViewById(R.id.wrappedClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TopSongs.class);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.wrappedClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TopSongs.class);
                startActivity(myIntent);
            }
        });

    }
}