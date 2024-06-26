package com.example.spotifywrappedbutgoated;

import androidx.annotation.NonNull;

public class Artist {

    private String id;
    private String name;

    public Artist(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String toString() {
        return getName() + " " + getId();
    }

}
