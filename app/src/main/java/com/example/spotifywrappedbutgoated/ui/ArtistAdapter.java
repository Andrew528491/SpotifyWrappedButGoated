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

public class ArtistAdapter extends ArrayAdapter<ArtistData> {
    public ArtistAdapter(Context context, int resource, List<ArtistData> artistList) {
        super(context, resource, artistList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArtistData artist = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewnewartists, parent, false);
        }
//        ImageView artistPicture = (ImageView) convertView.findViewById(R.id.artistPicture);

        TextView artistName = (TextView) convertView.findViewById(R.id.newArtistName);
        artistName.setText(artist.getArtist());

        return convertView;
    }
}