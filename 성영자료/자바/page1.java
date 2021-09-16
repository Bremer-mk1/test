package com.book.dogsandfox;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class page1 extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {

                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.test);
                mediaPlayer.start();
            }
        },3000);
        destory_sound();
    }

    private void destory_sound(){
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
    }
}