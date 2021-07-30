package com.example.recyclermusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclermusic.Model.SongModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private Context context;

    private List<SongModel> songList;
    ArrayList<String> arrayListUrl = new ArrayList<>();
    ArrayList<String> arrayListSong = new ArrayList<>();
    ArrayList<String> arrayListArtist = new ArrayList<>();
    ArrayList<String> arrayListImage = new ArrayList<>();

    public MyAdapter(List<SongModel> songList, Context context){
        this.songList = songList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {


        holder.songNameTextView.setText(songList.get(position).getSong());
        holder.songArtistTextView.setText(songList.get(position).getArtists());
        holder.songUrlTextView.setText(songList.get(position).getUrl());
        holder.songCoverImageTextView.setText(songList.get(position).getCover_image());
        Picasso.with(context).load(songList.get(position).getCover_image())
                .into(holder.imageView);


        if (!(arrayListUrl.contains(songList.get(position).getUrl())))
            arrayListUrl.add(songList.get(position).getUrl());
        if (!(arrayListSong.contains(songList.get(position).getSong())))
            arrayListSong.add(songList.get(position).getSong());
        if (!(arrayListArtist.contains(songList.get(position).getArtists())))
            arrayListArtist.add(songList.get(position).getArtists());
        if (!(arrayListImage.contains(songList.get(position).getCover_image())))
            arrayListImage.add(songList.get(position).getCover_image());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),PlayerActivity.class);

                i.putExtra("song",holder.songNameTextView.getText().toString());
                i.putExtra("url",holder.songUrlTextView.getText().toString());
                i.putExtra("artist",holder.songArtistTextView.getText().toString());
                i.putExtra("cover_image",holder.songCoverImageTextView.getText().toString());

                i.putExtra("arrayListUrl",arrayListUrl);
                i.putExtra("arrayListSong",arrayListSong);
                i.putExtra("arrayListArtist",arrayListArtist);
                i.putExtra("arrayListImage",arrayListImage);
                i.putExtra("position",String.valueOf(position));

                view.getContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView songNameTextView,songArtistTextView,songUrlTextView,songCoverImageTextView;
        ImageView imageView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            songNameTextView = itemView.findViewById(R.id.title);
            songArtistTextView = itemView.findViewById(R.id.artist);
            songUrlTextView = itemView.findViewById(R.id.url);
            songCoverImageTextView = itemView.findViewById(R.id.cover_image);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
