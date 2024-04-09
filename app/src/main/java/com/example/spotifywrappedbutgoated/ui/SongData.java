package com.example.spotifywrappedbutgoated.ui;

public class SongData {
    String artist;
    String songName;
    String albumCover;
    String songPlayer;
    public SongData(String artist, String songName, String albumCover, String songPlayer) {
        this.artist = artist;
        this.songName = songName;
        this.albumCover = albumCover;
        this.songPlayer = songPlayer;
    }
    public String getArtist() {
        return artist;
    }
    public String getSong() {
        return songName;
    }
    public String getAlbum() {
        return albumCover;
    }
    public String getSongPlayer() {
        return songPlayer;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setSong(String songName) {
        this.songName = songName;
    }
    public void setAlbum(String albumCover) {
        this.albumCover = albumCover;
    }
    public void setSongPlayer(String songPlayer) {
        this.songPlayer = songPlayer;
    }
}
