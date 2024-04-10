package com.example.spotifywrappedbutgoated.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.spotifywrappedbutgoated.SongService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.example.spotifywrappedbutgoated.R;

public class TopSongs extends AppCompatActivity {
    ListView listView;
    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;
     ArrayList<SongData> songList = new ArrayList<SongData>();

    private SongService songService;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs);




        songService = new SongService(getApplicationContext());


        Log.i("TEST", "test");


            Log.i("TEST", songService.toString());
        Log.i("TEST", getApplicationContext().toString());

        songService.getTopTracks(() -> {
            Log.i("TEST", "test");
            songList = songService.getSongs();
            Log.i("TEST", "test");
            Log.i("TEST", songList.get(0).getSong());
            Log.i("TEST", songList.get(1).getSong());

            System.out.println(songList);
        }, WrappedFilter.getTimespan());
        listView = (ListView) findViewById(R.id.listOfSongs);
        SongAdapter songAdapter = new SongAdapter(this, android.R.layout.simple_list_item_1, songList);
        listView.setAdapter(songAdapter);




        clickRight = (FloatingActionButton) findViewById(R.id.topSongsClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TopArtists.class);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.topSongsClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), TopSongs.class);
                startActivity(myIntent);
            }
        });

    }
}