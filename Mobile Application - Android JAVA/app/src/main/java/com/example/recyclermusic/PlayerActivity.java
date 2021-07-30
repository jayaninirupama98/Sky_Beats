package com.example.recyclermusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.content.BroadcastReceiver;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.recyclermusic.Model.SongModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.recyclermusic.App.CHANNEL_2_ID;

public class PlayerActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    public MediaSessionCompat mediaSession;

    ImageView CoverImage;
    TextView SongTitle, SongArtist;

    Button Play, Pause, Next, Prev;
    MediaPlayer mediaPlayer;

    SeekBar seekBar;
    TextView Pass, Due;

    Handler handler;
    String out, out2;
    Integer difference;
    Integer position;

    ArrayList<String> arrayListUrl;
    ArrayList<String> arrayListSong;
    ArrayList<String> arrayListArtist;
    ArrayList<String> arrayListImage;


    Animation uptodown, rotate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        notificationManager = NotificationManagerCompat.from(this);

        mediaSession = new MediaSessionCompat(this, "tag");

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);

        CoverImage = (ImageView) findViewById(R.id.coverImageView);
        SongTitle = (TextView) findViewById(R.id.song_title);
        SongArtist = (TextView) findViewById(R.id.song_artist);

        Play = (Button) findViewById(R.id.playBtn);
        Pause = (Button) findViewById(R.id.pauseBtn);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        Pass = (TextView) findViewById(R.id.tv_pass);
        Due = (TextView) findViewById(R.id.tv_due);

        Next = (Button) findViewById(R.id.next);
        Prev = (Button) findViewById(R.id.prev);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play.setVisibility(View.INVISIBLE);
                Pause.setVisibility(View.VISIBLE);
                if (arrayListUrl.size() == position + 1) {
                    position = 0;
                    init(arrayListSong.get(position), arrayListArtist.get(position), arrayListUrl.get(position), arrayListImage.get(position));
                } else {
                    position = position + 1;
                    init(arrayListSong.get(position), arrayListArtist.get(position), arrayListUrl.get(position), arrayListImage.get(position));

                }
            }
        });
        Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play.setVisibility(View.INVISIBLE);
                Pause.setVisibility(View.VISIBLE);

                if (position == 0) {
                    position = arrayListUrl.size() - 1;
                    init(arrayListSong.get(position), arrayListArtist.get(position), arrayListUrl.get(position), arrayListImage.get(position));
                } else {
                    position = position - 1;
                    init(arrayListSong.get(position), arrayListArtist.get(position), arrayListUrl.get(position), arrayListImage.get(position));
                }


            }
        });

        mediaPlayer = new MediaPlayer();

        handler = new Handler();

        Intent i = getIntent();
        String song = i.getStringExtra("song");
        String url = i.getStringExtra("url");
        String artist = i.getStringExtra("artist");
        String cover_image = i.getStringExtra("cover_image");

        arrayListUrl = i.getStringArrayListExtra("arrayListUrl");
        arrayListSong = i.getStringArrayListExtra("arrayListSong");
        arrayListArtist = i.getStringArrayListExtra("arrayListArtist");
        arrayListImage = i.getStringArrayListExtra("arrayListImage");
        position = Integer.parseInt(i.getStringExtra("position"));

        Toast.makeText(this, song, Toast.LENGTH_SHORT).show();

        init(song, artist, url, cover_image);


        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });
        initializeSeekBar();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void init(String song, String artist, String url, String cover_image) {

        SongTitle.setText(song);
        SongArtist.setText(artist);

        SongTitle.setAnimation(uptodown);
        SongArtist.setAnimation(uptodown);

        URL url2 = null;
        Bitmap image = null;
        try {
            url2 = new URL(arrayListImage.get(position));
            image = BitmapFactory.decodeStream(url2.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.t1);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.pause1)
                .setContentTitle(arrayListSong.get(position))
                .setContentText(arrayListArtist.get(position))
                .setLargeIcon(image)
                .addAction(R.drawable.ic_skip_previous_black_24dp, "Previous", null)
                .addAction(R.drawable.ic_pause_black_24dp, "Pause", null)
                .addAction(R.drawable.ic_play_arrow_black_24dp, "Play", null)
                .addAction(R.drawable.ic_skip_next_black_24dp, "Next", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                        .setMediaSession(mediaSession.getSessionToken()))
                .setSubText("Sub Text")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification);


        Glide.with(this)
                .load(cover_image)
                .override(300, 200)
                .into(CoverImage);

        CoverImage.setAnimation(rotate);

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

        initializeSeekBar();


    }

    private void initializeSeekBar() {

        seekBar.setMax(mediaPlayer.getDuration() / 1000);

        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);

                    out = String.format("%02d:%02d", seekBar.getProgress() / 60, seekBar.getProgress() % 60);
                    Pass.setText(out);

                    difference = mediaPlayer.getDuration() / 1000 - mediaPlayer.getCurrentPosition() / 1000;
                    out2 = String.format("%02d:%02d", difference / 60, difference % 60);

                    Due.setText(out2);
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


    private void play() {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.start();
        Play.setVisibility(View.INVISIBLE);
        Pause.setVisibility(View.VISIBLE);
        //int message = arrayListUrl.size();
        //String str = String.valueOf(message);
        //System.out.println("String is: "+str);
        String imgCover = arrayListImage.get(position);
        Toast.makeText(this, arrayListImage.get(position), Toast.LENGTH_SHORT).show();


    }

    private void pause() {

        mediaPlayer.pause();
        Play.setVisibility(View.VISIBLE);
        Pause.setVisibility(View.INVISIBLE);
        //Toast.makeText(this, position, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, arrayListImage.toString(), Toast.LENGTH_SHORT).show();
    }

}
