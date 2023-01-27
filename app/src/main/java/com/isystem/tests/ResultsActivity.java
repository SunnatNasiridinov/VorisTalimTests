package com.isystem.tests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.isystem.tests.cache.MemoryHelper;

public class ResultsActivity extends AppCompatActivity {
    private TextView trueAnswer, falseAnswer;
    private int totalTrue, totalFalse;
    private ImageButton backButton,homeButton,saveButton;
    private EditText editText;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window=this.getWindow();
        window.setNavigationBarColor(getResources().getColor(R.color.white));
        window.setStatusBarColor(this.getResources().getColor(R.color.white));


        Intent intent=getIntent();
        totalTrue=intent.getIntExtra("trueAnswers",0);
        totalFalse=intent.getIntExtra("falseAnswers",0);

        setContentView(R.layout.activity_results);

        loadView();
        setTextToResults();
        clickButton();

    }

    private void clickButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultsActivity.this,ChoseActivity.class);
                startActivity(intent);
                finish();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultsActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=editText.getText().toString();
                MemoryHelper.getHelper().saveData(new UserData(
                        name,totalTrue,totalFalse
                ));
                Intent intent=new Intent(ResultsActivity.this,ChoseActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setTextToResults() {
        trueAnswer.setText("To`g`ri javoblar soni: "+totalTrue);
        falseAnswer.setText("No to`g`ri javoblar soni: "+totalFalse);
    }

    private void loadView() {
        editText=findViewById(R.id.user_name_editor);
        saveButton=findViewById(R.id.btn_save_result);
        homeButton=findViewById(R.id.btn_home_result);
        backButton=findViewById(R.id.btn_back_result);

        trueAnswer=findViewById(R.id.true_answers_user);
        falseAnswer=findViewById(R.id.false_answers_user);
    }
}