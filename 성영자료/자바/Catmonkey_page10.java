package com.book.dogsandfox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Catmonkey_page10 extends AppCompatActivity {

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
        setContentView(R.layout.catmonkey_page10);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                start_main_sound();
            }
        },1000);

        //다음 페이지로
        next_btn= findViewById(R.id.next_btn);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next_btn.setVisibility(View.VISIBLE);
            }
        },1300);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_chk != 0){
                    destroy_sound();
                }
                Intent intent = new Intent(Catmonkey_page10.this,loading_word.class);
                intent.putExtra("title_num",2);
                startActivity(intent);
                finish();
            }
        });

        //이전 페이지로
        back_btn= findViewById(R.id.back_btn);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                back_btn.setVisibility(View.VISIBLE);
            }
        },1300);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_chk != 0){
                    destroy_sound();
                }
                Intent intent = new Intent(Catmonkey_page10.this,Catmonkey_page9.class);
                startActivity(intent);
                finish();
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

        //영어 텍스트 변환
        eng_chg_btn= findViewById(R.id.eng_chg_btn);
        eng_chg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text_p4 = findViewById(R.id.catmonkey_text10);
                if(eng_chk == 1){
                    text_p4.setText(getString(R.string.catmonkey_p10_kor));
                    eng_chk = 0;
                }else{
                    text_p4.setText(getString(R.string.catmonkey_p10_eng));
                    eng_chk = 1;
                }
            }
        });
    }

    private void start_main_sound(){
        if(eng_chk == 0){
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.catmonkey10);
        }else{
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.catmonkey10_eng);
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