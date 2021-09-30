package com.book.dogsandfox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_list extends AppCompatActivity {

    private Button dogAndFox;
    private Button catmonkey;
    private Button finish_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        dogAndFox = findViewById(R.id.dogAndFox);
        dogAndFox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_list.this,page1.class);
                startActivity(intent);
                finish();
            }
        });

        catmonkey = findViewById(R.id.catmonkey);
        catmonkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_list.this,Catmonkey_page1.class);
                startActivity(intent);
                finish();
            }
        });

        finish_btn = findViewById(R.id.finish_btn);
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}