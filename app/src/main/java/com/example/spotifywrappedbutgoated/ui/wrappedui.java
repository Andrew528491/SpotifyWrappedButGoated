package com.example.spotifywrappedbutgoated.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.app.AlertDialog;

import com.example.spotifywrappedbutgoated.UpdateLogin;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.spotifywrappedbutgoated.ArtistService;
import com.example.spotifywrappedbutgoated.SongService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.OutputStream;
import java.util.ArrayList;

// save imports
import android.Manifest;
import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import android.os.Environment;
import android.widget.Toast;


import com.example.spotifywrappedbutgoated.R;

public class wrappedui extends AppCompatActivity {

    Dialog myDialog;
    ListView songListview;
    ListView artistListview;
    ArrayList<SongData> songList = new ArrayList<>();
    ArrayList<ArtistData> artistList = new ArrayList<>();

    FloatingActionButton clickRight;
    FloatingActionButton clickLeft;
  
    private String userText;
    private String passText;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] permissionstorage = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    SongService songService;
    ArtistService artistService;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrappedui);
        Intent intent = getIntent();
        userText = intent.getStringExtra("username");
        passText = intent.getStringExtra("password");
        myDialog = new Dialog(this);

        songService = new SongService(getApplicationContext());
        artistService = new ArtistService(getApplicationContext());
        songService.getTopTracks(() -> {
            Log.i("TEST", "test");
            runOnUiThread(() -> {  // UI updates must happen on the main thread
                songList = songService.getSongs();  // Retrieve updated song list
                songListview = (ListView) findViewById(R.id.songWrappedList);
                WrappedUISongAdapter songAdapter = new WrappedUISongAdapter(this, android.R.layout.simple_list_item_1, songList);
                songListview.setAdapter(songAdapter);
            });

            Log.i("TEST", "test");
            Log.i("TEST", songList.get(0).getSong());
            Log.i("TEST", songList.get(1).getSong());

            System.out.println(songList);
        }, WrappedFilter.getTimespan());



            artistService.getTopArtists(() -> {
                Log.i("TEST", "test");
                runOnUiThread(() -> {  // UI updates must happen on the main thread
                    artistList = artistService.getArtists();
                    artistListview = (ListView) findViewById(R.id.artistWrappedList);


                    WrappedUIArtistAdapter artistAdapter = new WrappedUIArtistAdapter(this, android.R.layout.simple_list_item_1, artistList);
                    artistListview.setAdapter(artistAdapter);
                });

                Log.i("TEST", "test");

                System.out.println(artistList);
            }, WrappedFilter.getTimespan());
        songListview = (ListView) findViewById(R.id.songWrappedList);
        artistListview = (ListView) findViewById(R.id.artistWrappedList);
        WrappedUISongAdapter songAdapter = new WrappedUISongAdapter(this, android.R.layout.simple_list_item_1, songList);
        songListview.setAdapter(songAdapter);
        WrappedUIArtistAdapter artistAdapter = new WrappedUIArtistAdapter(this, android.R.layout.simple_list_item_1, artistList);
        artistListview.setAdapter(artistAdapter);
        clickRight = (FloatingActionButton) findViewById(R.id.wrappedClickRight);
        clickRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), wrappedui.class);
                startActivity(myIntent);
            }
        });
        clickLeft = (FloatingActionButton) findViewById(R.id.wrappedClickLeft);
        clickLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), NewArtists.class);
                startActivity(myIntent);
            }
        });

        Button screenshotButton = findViewById(R.id.screenshotButton);
        screenshotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
            }
        });

        Button settingsButton = findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUpdateLogin();
            }
        });

        Button shareScreenshotButton = findViewById(R.id.shareScreenshotButton);
        shareScreenshotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(wrappedui.this)
                        .setTitle("Share Screenshot") // Title for the dialog
                        .setMessage("Do you want to share this screenshot?") // Message in the dialog
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Uri screenshotUri = takeScreenshot(); // Capture the screen and get the Uri
                                if (screenshotUri != null) {
                                    // Share the screenshot
                                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                    shareIntent.setPackage("com.android.mms"); // Set package to open Messages app
                                    shareIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                    shareIntent.setType("image/jpeg");
                                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    startActivity(Intent.createChooser(shareIntent, "Share Screenshot"));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Failed to capture screenshot", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, null) // No-op listener
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        }

    public Uri takeScreenshot() {
        Date now = new Date();
        final String relativeLocation = Environment.DIRECTORY_PICTURES + File.separator + "Screenshots";

        final ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now).toString() + ".jpg");
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation);

        final ContentResolver resolver = getContentResolver();
        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        try {
            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            rootView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);

            if (uri != null) {
                try (OutputStream stream = resolver.openOutputStream(uri)) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
                    Toast.makeText(getApplicationContext(), "Screenshot saved to Photos", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    resolver.delete(uri, null, null);
                    Toast.makeText(getApplicationContext(), "Failed to save screenshot", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed to take screenshot", Toast.LENGTH_SHORT).show();
        }
        return uri;
    }

    public void goToUpdateLogin() {
        Intent editIntent = new Intent(wrappedui.this, UpdateLogin.class);
        editIntent.putExtra("username", userText);
        editIntent.putExtra("password", passText);
        startActivity(editIntent);
    }

    public void ShowPopup(View v) {
        TextView txtclose;
        Button button2;
        Button button3;
        myDialog.setContentView(R.layout.share_pop_up);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        button2 = (Button) myDialog.findViewById(R.id.button2);
        button3 = (Button) myDialog.findViewById(R.id.button3);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}
