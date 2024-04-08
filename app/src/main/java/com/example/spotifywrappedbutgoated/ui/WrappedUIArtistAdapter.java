package com.example.spotifywrappedbutgoated.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.spotifywrappedbutgoated.R;

import java.util.List;

public class WrappedUIArtistAdapter extends ArrayAdapter<String> {
    public WrappedUIArtistAdapter(Context context, int resource, List<String> artistList) {
        super(context, resource, artistList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String artist = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewwrappedartists, parent, false);
        }

        TextView artistName = (TextView) convertView.findViewById(R.id.songWrappedArtist);
        String total = (position + 1) + "  " + artist;
        artistName.setText(total);

        return convertView;
    }
}