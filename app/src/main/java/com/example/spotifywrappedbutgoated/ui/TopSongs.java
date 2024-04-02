package com.example.spotifywrappedbutgoated.ui;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.spotifywrappedbutgoated.R;
import com.example.spotifywrappedbutgoated.databinding.FragmentTopSongsBinding;
import com.example.spotifywrappedbutgoated.databinding.FragmentWrappeduiBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TopSongs extends Fragment {

    private TopSongsViewModel mViewModel;
    ListView listView;

    FragmentTopSongsBinding binding;
    ArrayList<String> arrayList = new ArrayList<>();

    public static TopSongs newInstance() {
        return new TopSongs();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TopSongsViewModel tsVM = new ViewModelProvider(this).get(TopSongsViewModel.class);
        binding = FragmentTopSongsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.list2;
        FloatingActionButton button = binding.floatingActionButton3;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        for (int i = 0; i < 5; i++) {
            arrayList.add("SONG NAME");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(binding.getRoot().getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_wrappedui, container, false);
    }


}