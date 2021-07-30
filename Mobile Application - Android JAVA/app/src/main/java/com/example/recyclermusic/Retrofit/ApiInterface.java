package com.example.recyclermusic.Retrofit;

import com.example.recyclermusic.Model.SongModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api.php")
    Call<List<SongModel>> getconnect();
}
