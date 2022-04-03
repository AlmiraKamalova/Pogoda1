package com.example.pogoda1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TestIntentFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent_filter);
        Intent i = getIntent();
        /*TextView tv1 = (TextView) findViewById(R.id.tv_subject);
        tv1.setText(i.getStringExtra(Intent.EXTRA_SUBJECT));
        TextView tv2 = (TextView) findViewById(R.id.tv_text);
        tv2.setText(i.getStringExtra(Intent.EXTRA_TEXT));*/
    }
}