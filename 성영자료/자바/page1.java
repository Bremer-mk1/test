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

public class page1 extends AppCompatActivity {

    private Button dog_btn;
    private Button replay_btn;
    private Button next_btn;
    private Button eng_chg_btn;
    private int eng_chk=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

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
                Intent intent = new Intent(page1.this,page2.class);
                startActivity(intent);
            }
        });

        //리플레이
        replay_btn= findViewById(R.id.replay_btn);
        replay_btn.setOnClickListener(new View.OnClickListener() {

//        replay_btn.setOnClickListener(new OnSingleClickListener() {
        @Override
        public void onClick(View view){
//            public void onSingleClick(View view) {
                start_main_sound();
            }
        });

        //개소리
        dog_btn= findViewById(R.id.dog_p1_btn);
        dog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_dog_sound();
            }
        });

        //영어 텍스트 변환
        eng_chg_btn= findViewById(R.id.eng_chg_btn);
        eng_chg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text_p1 = findViewById(R.id.dogfox_text1);
                if(eng_chk == 1){
                    text_p1.setText("몇몇 개들은 사자의 가죽을 발견했고\n" +
                            "그들은 이빨로 맹렬히 그것을 찢기 시작했다.");
                    eng_chk = 0;
                }else{
                    text_p1.setText("Some Dogs found the skin of a lion \n " +
                            "and furiously began to tear it with their teeth.");
                    eng_chk = 1;
                }
            }
        });
    }

    private  void start_dog_sound(){
        SoundPool soundPool;
        int soundID;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.dogs,1);

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
        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.dogandfox1);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                mp.stop();
                mp.release();
            }
        });

    }


    private void start_replay_sound(){

        SoundPool soundPool;
        int soundID;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);	//작성
        soundID = soundPool.load(this,R.raw.dogandfox1,1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int id, int status) {
                if(status == 0){
                    soundPool.play(id,1f,1f,0,0,1f);
                }
            }
        });

    }

}