package com.book.dogsandfox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button test_btn;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        media_sound_test();
        destory_sound();

        test_btn= findViewById(R.id.button);
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound_test();
                Intent intent = new Intent(MainActivity.this, page1.class);
                startActivity(intent);
            }
        });
    }

    private void sound_test(){
        SoundPool soundPool;
        int soundID;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.test,1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int id, int status) {
                if(status == 0){
                    soundPool.play(id,1f,1f,0,0,1f);
                }
            }
        });
    }

    private void media_sound_test() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.test);
        mediaPlayer.start();
    }
    private void destory_sound(){
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
    }
}