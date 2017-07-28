package com.example.muasdev.firebasesatu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static String passedString;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textView3);

        //getting the string
        passedString = getIntent().getStringExtra(MainActivity.clickedItem);
        textView.setText(passedString);
    }
}
