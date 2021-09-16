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

        //Handler는 심심해서 넣어본거임 3초뒤 실행되게 해놓음
        //Handler 굳이 없어도 화면 이동 했을떄 무리없이 재생됬음
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
