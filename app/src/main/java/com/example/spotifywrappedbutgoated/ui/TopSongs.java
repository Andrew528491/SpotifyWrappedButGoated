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

public class TopSongs extends AppCompatActivity {
    ListView listView;
    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;
    ArrayList<SongData> songList = new ArrayList<SongData>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs);
        listView = (ListView) findViewById(R.id.listOfSongs);

        songList.add(new SongData("The Weeknd", "Prisoner", "@android:drawable/ic_media_play", "IDK"));
        songList.add(new SongData("Lana Del Ray", "Ultraviolence", "@android:drawable/ic_media_play", "IDK"));
        songList.add(new SongData("Dominic Fike", "Westcoast Collective", "@android:drawable/ic_media_play", "IDK"));
        songList.add(new SongData("Ariana Grande", "We Can't Be Friends", "@android:drawable/ic_media_play", "IDK"));
        songList.add(new SongData("Travis Scott", "Nightcrawler", "@android:drawable/ic_media_play", "IDK"));

        SongAdapter songAdapter = new SongAdapter(this, android.R.layout.simple_list_item_1, songList);
        listView.setAdapter(songAdapter);

        clickRight = (FloatingActionButton) findViewById(R.id.topSongsClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.topSongsClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                startActivity(myIntent);
            }
        });

    }
}