package com.example.pogoda1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Button startNewActivityButton = (Button) findViewById(R.id.pogoda_button);
        startNewActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                SplashActivity.this.finish();
            }
        });
        if (getSupportActionBar() !=null) {
            getSupportActionBar().hide();
        }

    }
}