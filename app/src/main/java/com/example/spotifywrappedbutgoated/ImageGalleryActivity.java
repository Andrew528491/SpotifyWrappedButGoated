package com.example.spotifywrappedbutgoated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.spotifywrappedbutgoated.ui.wrappedui;

import java.io.File;
import java.util.ArrayList;

public class ImageGalleryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private String userText;
    private String passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        userText = intent.getStringExtra("username");
        passText = intent.getStringExtra("password");

        String userFolder = userText + "'s Past Wraps";
        String directoryPath = Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_PICTURES + File.separator + userFolder;
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    imagePaths.add(file.getAbsolutePath());
                }
            }
        }

        ImageAdapter imageAdapter = new ImageAdapter(this, imagePaths);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                myIntent.putExtra("username", userText);
                myIntent.putExtra("password", passText);
                startActivity(myIntent);
            }
        });
    }
}
