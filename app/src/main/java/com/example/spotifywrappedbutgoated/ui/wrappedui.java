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
import com.example.spotifywrappedbutgoated.databinding.FragmentWrappeduiBinding;

import java.util.ArrayList;

public class wrappedui extends Fragment {

    private WrappeduiViewModel mViewModel;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();

    FragmentWrappeduiBinding binding;
    public static wrappedui newInstance() {
        return new wrappedui();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        WrappeduiViewModel wuiVM = new ViewModelProvider(this).get(WrappeduiViewModel.class);
        binding = FragmentWrappeduiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = binding.list;

        for (int i = 0; i < 5; i++) {
            arrayList.add("SONG NAME");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(binding.getRoot().getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_wrappedui, container, false);
    }



}