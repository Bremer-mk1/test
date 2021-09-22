package com.book.dogsandfox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class page5 extends AppCompatActivity {
    private Button next_btn;
    private Button back_btn;
    private InterstitialAd mInterstitialAd;
    private String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page5);

        //전면광고 설정
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        loadAd();

        //리스트뷰 셋트
        String [] eng_word_list = getResources().getStringArray(R.array.dogandfox_eng_word);
        String [] kor_word_list = getResources().getStringArray(R.array.dogandfox_kor_word);

        ListView eng_listView = findViewById(R.id.dogfox_eng_word);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,eng_word_list);
        eng_listView.setAdapter(adapter);

        ListView kor_listView = findViewById(R.id.dogfox_kor_word);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,kor_word_list);
        kor_listView.setAdapter(adapter1);

        //다음 페이지로
        next_btn= findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAds();
            }
        });

        //이전 페이지로
        back_btn= findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page5.this,page4.class);
                startActivity(intent);
            }
        });
    };

//광고 로드하기
public void loadAd() {
    AdRequest adRequest = new AdRequest.Builder().build();
    InterstitialAd.load(
            this,
            AD_UNIT_ID,
            adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    page5.this.mInterstitialAd = interstitialAd;
                    Log.i("LogTAG", "onAdLoaded");
//                    Toast.makeText(page5.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                    interstitialAd.setFullScreenContentCallback(
                            new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when fullscreen content is dismissed.
                                    // Make sure to set your reference to null so you don't
                                    // show it a second time.
                                    //광고 닫기 했을때
                                    page5.this.mInterstitialAd = null;
                                    Log.d("TAG", "The ad was dismissed.");
                                    Intent intent = new Intent(page5.this,main_list.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    // Called when fullscreen content failed to show.
                                    // Make sure to set your reference to null so you don't
                                    // show it a second time.
                                    page5.this.mInterstitialAd = null;
                                    Log.d("TAG", "The ad failed to show.");
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when fullscreen content is shown.
                                    Log.d("TAG", "The ad was shown.");
                                }
                            });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.i("LOgTAG", loadAdError.getMessage());
                    mInterstitialAd = null;

//                    String error =
//                            String.format(
//                                    "domain: %s, code: %d, message: %s",
//                                    loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
//                    Toast.makeText(
//                            page5.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
//                            .show();
                }
            });
}
//광고 보여주기
    private void showAds(){
        if(mInterstitialAd != null){
            mInterstitialAd.show(page5.this);
        }else{
//            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }
}