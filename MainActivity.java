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

        //mp3파일재생방법은 2가지인듯함
        //1번째는 SoundPool 사용 -> 짧은 효과음등을 재생할때 사용
        //2번째는 MediaPlayer 사용 -> SoundPool보다 긴 파일을 재생할때 사용
        
        //onCreate시에 바로 재생하게 했지만 SoundPool의 경우 소리가 중간에 끊어짐 안됨
        sound_test();
        //onCreate시에 바로 재생하게 했지만 MediaPlayer의경우 안됨
        media_sound_test();
        destory_sound();
        //앱의 최초 onCreate시 로드가 제대로 안되서 안되는듯?

        test_btn= findViewById(R.id.button);
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //클릭시 SoundPool테스트 결과 -> 문제없이 사용가능
                sound_test();
                Intent intent = new Intent(MainActivity.this, page1.class);
                startActivity(intent);
            }
        });
    }

    //1번째 SoundPool 사용-> 샘플로 보내준 파일 재생가능함 안끊어짐
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
    
//2번째 MediaPlayer 사용-> 샘플로 보내준 파일 재생가능함 안끊어짐
    private void media_sound_test() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.test);
        mediaPlayer.start();
    }
    
    //2번째 MediaPlayer 사용의 경우 사용 후 릴리즈 해줘야됨
    private void destory_sound(){
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
    }
}
