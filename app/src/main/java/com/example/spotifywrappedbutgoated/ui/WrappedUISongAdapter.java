package com.example.spotifywrappedbutgoated.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.spotifywrappedbutgoated.R;

import java.util.List;

public class WrappedUISongAdapter extends ArrayAdapter<String> {
    public WrappedUISongAdapter(Context context, int resource, List<String> songList) {
        super(context, resource, songList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String song = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewwrappedsongs, parent, false);
        }

        TextView songName = (TextView) convertView.findViewById(R.id.songWrappedName);
        String total = (position + 1) + "  " + song;
        songName.setText(total);

        return convertView;
    }
}

