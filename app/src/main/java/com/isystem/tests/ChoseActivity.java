package com.isystem.tests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class ChoseActivity extends AppCompatActivity {
    private CardView geographyCard, mathematicsCard, englishCard, russiaCard;
    private ImageButton backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setNavigationBarColor(getColor(R.color.white));
            window.setStatusBarColor(getColor(R.color.white));
        }

        loadView();
        checkedCard();
    }


    private void loadView() {
        geographyCard = findViewById(R.id.geography_card_view);
        mathematicsCard = findViewById(R.id.matematik_card_view);
        englishCard = findViewById(R.id.english_card_view);
        russiaCard = findViewById(R.id.russia_card_view);

        backButton = findViewById(R.id.back_button);
    }

    private void checkedCard() {
        geographyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseActivity.this, GameActivity.class);
                intent.putExtra(GameActivity.TYPE_TEST, GameActivity.ONA_TILI);
                startActivity(intent);
                finish();
            }
        });
        mathematicsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseActivity.this, GameActivity.class);
                intent.putExtra(GameActivity.TYPE_TEST, GameActivity.MATH);
                startActivity(intent);
                finish();
            }
        });
        englishCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseActivity.this, GameActivity.class);
                intent.putExtra(GameActivity.TYPE_TEST, GameActivity.ENGLISH);
                startActivity(intent);
                finish();
            }
        });
        russiaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseActivity.this, GameActivity.class);
                intent.putExtra(GameActivity.TYPE_TEST, GameActivity.RUS);
                startActivity(intent);
                finish();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoseActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ChoseActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}