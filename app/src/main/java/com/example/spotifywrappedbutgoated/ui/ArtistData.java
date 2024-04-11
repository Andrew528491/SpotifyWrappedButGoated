package com.example.spotifywrappedbutgoated.ui;

public class ArtistData {
    String artistName;
    String artistPicture;
    String artistURI;
    public ArtistData(String artistName, String artistPicture, String artistURI) {
        this.artistName = artistName;
        this.artistPicture = artistPicture;
        this.artistURI = artistURI;
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
    public String getArtistURI() {
        return artistURI;
    }

}
