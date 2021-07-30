package com.example.recyclermusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.recyclermusic.Model.SongModel;
import com.example.recyclermusic.Retrofit.ApiClient;
import com.example.recyclermusic.Retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsListActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<SongModel>> call = service.getconnect();

        call.enqueue(new Callback<List<SongModel>>() {
            @Override
            public void onResponse(Call<List<SongModel>> call, Response<List<SongModel>> response) {
                loadDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<SongModel>> call, Throwable t) {
                Toast.makeText(SongsListActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loadDataList(List<SongModel> songsList) {

        recyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new MyAdapter(songsList,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SongsListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }
}