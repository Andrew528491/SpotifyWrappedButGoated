package com.example.spotifywrappedbutgoated.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.spotifywrappedbutgoated.R;
import com.example.spotifywrappedbutgoated.databinding.FragmentTopSongsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TopSongs extends Fragment {

    private TopSongsViewModel mViewModel;
    ListView listView;

    FragmentTopSongsBinding binding;
    ArrayList<SongData> songList = new ArrayList<SongData>();

    public static TopSongs newInstance() {
        return new TopSongs();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TopSongsViewModel tsVM = new ViewModelProvider(this).get(TopSongsViewModel.class);
        binding = FragmentTopSongsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.listOfSongs;


        for (int i = 0; i < 5; i++) {
            songList.add(new SongData("Ah", "Ah", "IDK", "IDK"));
        }
        SongAdapter songAdapter = new SongAdapter(binding.getRoot().getContext(), android.R.layout.simple_list_item_1, songList);
        listView.setAdapter(songAdapter);



        return inflater.inflate(R.layout.fragment_wrappedui, container, false);
    }


}