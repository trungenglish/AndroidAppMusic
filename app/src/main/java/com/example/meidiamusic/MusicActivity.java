package com.example.meidiamusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {
    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar skSong;
    ImageView Hinh,clickBack;
    ImageButton btnPre, btnPlay, btnStop, btnNext, btnMenu;

    ArrayList<Song> arraySong;
    int position =0;
    MediaPlayer mediaPlayer;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Anhxa();
        AddSong();
        KhoitaoMediaPlayer();
        animation = AnimationUtils.loadAnimation(this, R.anim.disc_animation);
        clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MusicActivity.this,HomePageActivity.class);
                startActivity(i);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position > arraySong.size()-1){
                    position =0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoitaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause_icon);
                SetTimetotal();
                UpdateTimeSong();
                Hinh.startAnimation(animation);
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position < 0){
                    position =arraySong.size()-1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoitaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause_icon);
                SetTimetotal();
                UpdateTimeSong();
                Hinh.startAnimation(animation);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    //pause --> đổi hình
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play_icon);
                    Hinh.clearAnimation();
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause_icon);
                    Hinh.startAnimation(animation);
                }
                SetTimetotal();
                UpdateTimeSong();

            }
        });
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void UpdateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhdangGio.format(mediaPlayer.getCurrentPosition()));
                //update seek bar cho chạy theo thời gian bài hát
                skSong.setProgress(mediaPlayer.getCurrentPosition());
                // bắt thời gian kết thúc của bài để chuyển nhạc
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position > arraySong.size()-1){
                            position =0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoitaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause_icon);
                        SetTimetotal();
                        UpdateTimeSong();
                    }
                });
                handler.postDelayed(this,100);
            }
        }, 100);
    }
    private void SetTimetotal(){
        SimpleDateFormat dinhdangtime = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhdangtime.format(mediaPlayer.getDuration()));
        //gán trị số max của bài hát = time bài hát
        skSong.setMax(mediaPlayer.getDuration());
    }
    private void KhoitaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(MusicActivity.this, arraySong.get(position).getFile() );
        txtTitle.setText(arraySong.get(position).getTitle());
    }
    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Devil", R.raw.devil));
        arraySong.add(new Song("The Force", R.raw.the_force));
        arraySong.add(new Song("SDP Interlude", R.raw.sdp));
    }

    private void Anhxa() {
        txtTimeSong = (TextView) findViewById(R.id.TextviewTimeSong);
        txtTimeTotal= (TextView) findViewById(R.id.TextviewTimeTotal);
        txtTitle    = (TextView) findViewById(R.id.TextviewTenbaihat);
        skSong      = (SeekBar) findViewById(R.id.seekBar);
        btnPre      = (ImageButton) findViewById(R.id.previousButton);
        btnPlay     = (ImageButton) findViewById(R.id.playButton);
        btnNext     = (ImageButton) findViewById(R.id.nextButton);
        Hinh        = (ImageView) findViewById(R.id.imageViewDisc);
        clickBack = findViewById(R.id.clickBack);
    }
}