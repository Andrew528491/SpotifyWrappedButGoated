package com.example.spotifywrappedbutgoated.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotifywrappedbutgoated.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class SongAdapter extends ArrayAdapter<SongData> {
    public SongAdapter(Context context, int resource, List<SongData> songList) {
        super(context, resource, songList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongData song = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewsongplayer, parent, false);
        }
        ImageView albumCover = (ImageView) convertView.findViewById(R.id.songAlbumCover);
        TextView songName = (TextView) convertView.findViewById(R.id.songName);
        FloatingActionButton playButton = (FloatingActionButton) convertView.findViewById(R.id.playButton);
        //albumCover.setImageResource(song.getAlbum());
        songName.setText(song.getSong());

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RETURN SONG PLAYER HERE
            }
        });
        return convertView;
    }
}





















