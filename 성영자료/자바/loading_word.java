package com.book.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class loading_word extends AppCompatActivity {

    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_word);

        num = getIntent().getIntExtra("title_num",0);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(loading_word.this,word_list.class);
                intent.putExtra("title_num",num);
                startActivity(intent);
                finish();
            }
        },700);
    }
}