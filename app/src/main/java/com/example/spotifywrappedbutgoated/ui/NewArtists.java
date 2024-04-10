package com.example.spotifywrappedbutgoated.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.spotifywrappedbutgoated.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NewArtists extends AppCompatActivity {
    ListView listview;
    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;
    ArrayList<ArtistData> newArtistList = new ArrayList<ArtistData>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_artists);
        listview = (ListView) findViewById(R.id.listNewArtists);
        newArtistList.add(new ArtistData("The Weeknd", "IDK"));
        newArtistList.add(new ArtistData("Lana Del Ray", "IDK"));
        newArtistList.add(new ArtistData("Dominic Fike", "IDK"));
        newArtistList.add(new ArtistData("Ariana Grande", "IDK"));
        newArtistList.add(new ArtistData("Travis Scott", "IDK"));

        ArtistAdapter newArtistsAdapter = new ArtistAdapter(this, android.R.layout.simple_list_item_1, newArtistList);
        listview.setAdapter(newArtistsAdapter);

        clickRight = (FloatingActionButton) findViewById(R.id.newArtistsClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.newArtistsClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(getApplicationContext(), TopArtists.class);
                startActivity(myIntent);
            }
        });


    }
}