package com.example.recyclermusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class SongModel {

    @Expose
    @SerializedName("title")
    String song;

    @Expose
    @SerializedName("upath")
    String url;

    @Expose
    @SerializedName("artist")
    String artists;

    @Expose
    @SerializedName("cover_image")
    String cover_image;


    public SongModel(String song, String url, String cover_image, String artists) {
        this.song = song;
        this.url = url;
        this.cover_image = cover_image;
        this.artists = artists;
    }

    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
    }

    public String getUrl() {
        String uurl = "https://skybeats.ga/assets/uploads/";
        return uurl+url;
    }

    public String getCover_image() {
        String uurl = "https://skybeats.ga/assets/uploads/";
        return uurl+cover_image;
    }

    public String getArtists() {

        return artists;
    }
}
