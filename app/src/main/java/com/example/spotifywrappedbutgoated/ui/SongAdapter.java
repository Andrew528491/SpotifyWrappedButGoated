package com.example.spotifywrappedbutgoated.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.spotifywrappedbutgoated.R;

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

        TextView songName = (TextView) convertView.findViewById(R.id.songName);
        songName.setText(song.getSong());

        return convertView;
    }
}