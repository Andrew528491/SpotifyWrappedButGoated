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
        ImageView albumCover = (ImageView) convertView.findViewById(R.id.songAlbumCover);
//        Picasso.get().load("https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228").placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .into(albumCover);
        TextView songName = (TextView) convertView.findViewById(R.id.songName);
        ImageButton playButton = (ImageButton) convertView.findViewById(R.id.imageButton);
        songName.setText(song.getSong());

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LIST_VIEW", song.getSong());
            }
        });
        return convertView;
    }
}