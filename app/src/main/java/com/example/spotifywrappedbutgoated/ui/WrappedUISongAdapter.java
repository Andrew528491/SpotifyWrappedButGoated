package com.example.spotifywrappedbutgoated.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.spotifywrappedbutgoated.R;

import java.util.List;

public class WrappedUISongAdapter extends ArrayAdapter<SongData> {
    public WrappedUISongAdapter(Context context, int resource, List<SongData> songList) {
        super(context, resource, songList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongData song = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewwrappedsongs, parent, false);
        }

        TextView songName = (TextView) convertView.findViewById(R.id.songWrappedName);
        String total = (position + 1) + "  " + song.getSong();
        songName.setText(total);

        return convertView;
    }
}

