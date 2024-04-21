package com.example.spotifywrappedbutgoated.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.spotifywrappedbutgoated.LoginActivity;
import com.example.spotifywrappedbutgoated.R;

public class WrappedFilter extends AppCompatActivity {
    Button fourWeeks;
    Button sixMonths;
    Button allTime;

    static String timespan = "short_term";


    public static String getTimespan() {
        return timespan;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapped_filter);
        fourWeeks = (Button) findViewById(R.id.fourWeeksSelect);
        sixMonths = (Button) findViewById(R.id.sixMonthsSelect);
        allTime = (Button) findViewById(R.id.allTimeSelect);
        Intent intent = getIntent();
        String userText = intent.getStringExtra("username");
        String passText = intent.getStringExtra("password");
        Log.d("WrappedFilter", "Username received: " + userText);
        fourWeeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timespan = "short_term";

                Intent myIntent = new Intent(getApplicationContext(), TopSongs.class);
                myIntent.putExtra("username", userText);
                myIntent.putExtra("password", passText);
                startActivity(myIntent);
            }
        });
        sixMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timespan = "medium_term";
                Log.i("TEST", timespan);
                Intent myIntent = new Intent(getApplicationContext(), TopSongs.class);
                myIntent.putExtra("username", userText);
                myIntent.putExtra("password", passText);
                startActivity(myIntent);
            }
        });
        allTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timespan = "long_term";
                Intent myIntent = new Intent(getApplicationContext(), TopSongs.class);
                myIntent.putExtra("username", userText);
                myIntent.putExtra("password", passText);
                startActivity(myIntent);
            }
        });


    }
}