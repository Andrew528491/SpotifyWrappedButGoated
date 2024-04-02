package com.example.spotifywrappedbutgoated.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WrappeduiViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public WrappeduiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is main Wrapped UI ViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}