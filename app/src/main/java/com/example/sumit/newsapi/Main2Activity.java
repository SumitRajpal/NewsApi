package com.example.sumit.newsapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Thread background = new Thread() {
            public void run() {

                try {

                    sleep(2000);


                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(intent);

                    finish();

                } catch (Exception e) {

                }
            }
        };


        background.start();
    }
}
