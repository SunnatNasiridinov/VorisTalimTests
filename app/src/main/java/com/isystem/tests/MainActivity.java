package com.isystem.tests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button startButton,resultButton,exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setNavigationBarColor(getColor(R.color.white));
            window.setStatusBarColor(getColor(R.color.white));
        }
        loadView();
        setListner();
    }

    private void loadView() {
        startButton=findViewById(R.id.btn1);
        resultButton=findViewById(R.id.btn2);
        exitButton=findViewById(R.id.btn4);
    }

    private void setListner() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ChoseActivity.class);
                startActivity(intent);
                finish();
            }
        });
       resultButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,AllResultsActivity.class);
               startActivity(intent);
           }
       });
       exitButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
    }

}