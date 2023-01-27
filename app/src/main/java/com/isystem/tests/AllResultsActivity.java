package com.isystem.tests;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.UnicodeSet;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.isystem.tests.cache.MemoryHelper;

import java.util.ArrayList;

public class AllResultsActivity extends AppCompatActivity {
    private ListView listView;
    private ResultAdapter resultAdapter;
    private ArrayList<UserData> data;
    private ImageButton backButton, clearData;

    private int totalsTrue;
    private int totalsFalse;
    private String nameUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.all_result);
        Window window=getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.white));
            window.setNavigationBarColor(getColor(R.color.white));
        }

        super.onCreate(savedInstanceState);
        listView=findViewById(R.id.home_list);
        backButton=findViewById(R.id.all_result_back);
        clearData=findViewById(R.id.clear_data);

        resultAdapter=new ResultAdapter();
        listView.setAdapter(resultAdapter);

        loadData();
        clickButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clickButton();
        clearAllData();
    }

    private void clickButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
    public void clearAllData(){
        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
    }

    private void dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Clear Data")
                .setMessage("Hamma natijalarni o`chirishni hohlaysizmi")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MemoryHelper.getHelper().clearData();
                        clearAllData();
                        loadData();
                    }
                })
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }

    private void loadData() {
        resultAdapter.setData(MemoryHelper.getHelper().getLastData());
    }

}
