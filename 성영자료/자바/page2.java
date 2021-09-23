package com.book.dogsandfox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class page2 extends AppCompatActivity {

    private Button fox_btn;
    private Button replay_btn;
    private Button next_btn;
    private Button back_btn;
    private Button eng_chg_btn;
    private int eng_chk=0;
    private int m_position;
    private MediaPlayer mediaPlayer;
    private int m_chk = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                start_main_sound();
            }
        },1000);

        //다음 페이지로
        next_btn= findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_chk != 0){
                    destroy_sound();
                }
                Intent intent = new Intent(page2.this,page3.class);
                startActivity(intent);
            }
        });

        //이전 페이지로
        back_btn= findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_chk != 0){
                    destroy_sound();
                }
                Intent intent = new Intent(page2.this,page1.class);
                startActivity(intent);
            }
        });

        //리플레이
        replay_btn= findViewById(R.id.replay_btn);
        replay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                replay_sound();
            }
        });

        //여우소리
        fox_btn= findViewById(R.id.fox_p2_btn);
        fox_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_fox_sound();
            }
        });

        //영어 텍스트 변환
        eng_chg_btn= findViewById(R.id.eng_chg_btn);
        eng_chg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text_p2 = findViewById(R.id.dogfox_text2);
                if(eng_chk == 1){
                    text_p2.setText(getString(R.string.dogandfox_p2_kor));
                    eng_chk = 0;
                }else{
                    text_p2.setText(getString(R.string.dogandfox_p2_eng));
                    eng_chk = 1;
                }
            }
        });
    }

    private  void start_fox_sound(){
        SoundPool soundPool;
        int soundID;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.sound_fox,1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int id, int status) {
                if(status == 0){
                    soundPool.play(id,1f,1f,0,0,1f);
                }
            }
        });
    }

    private void start_main_sound(){
        if(eng_chk == 0){
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.dogandfox2);
        }else{
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.dogandfox2_eng);
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        m_chk = 1;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                destroy_sound();
            }
        });
    }

    private void replay_sound(){
        if(m_chk == 0) {
            start_main_sound();
        }else if(m_chk == 1){
            mediaPlayer.pause();
            m_position = mediaPlayer.getCurrentPosition();
            m_chk = 2;
        }else{
            mediaPlayer.seekTo(m_position);
            mediaPlayer.start();
            m_chk = 1;
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    destroy_sound();
                }
            });
        }
    }

    private void destroy_sound(){
        if(m_chk != 0){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        m_chk = 0;
    }

}