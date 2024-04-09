package com.example.spotifywrappedbutgoated.ui;

public class ArtistData {
    String artistName;
    String artistPicture;
    public ArtistData(String artistName, String artistPicture) {
        this.artistName = artistName;
        this.artistPicture = artistPicture;
    }
    public String getArtist() {
        return artistName;
    }
    public String getArtistPicture() {
        return artistPicture;
    }
    public void setArtist(String artist) {
        this.artistName = artistName;
    }
    public void setArtistPicture(String artistPicture) {
        this.artistPicture = artistPicture;
    }

}
