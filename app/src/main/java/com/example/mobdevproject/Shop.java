package com.example.mobdevproject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.AnimRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Shop extends AppCompatActivity {

    float x1, x2, y1, y2;
    int pointscounter;

    LinearLayout popUpLoad;
    private static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.shopScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent intent = getIntent();
        pointscounter = intent.getIntExtra("points", 0);

        TextView pointsTextView = findViewById(R.id.textView2);
        pointsTextView.setText("Points: " + pointscounter);

        Button showLoad = findViewById(R.id.btnBuyLoad);

        showLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, popupLoad.class);
                intent.putExtra("points", pointscounter);
                startActivity(intent);
            }
        });

        Button showNetflix = findViewById(R.id.btnBuyNetflix);
        showNetflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, buyNetflix.class);
                intent.putExtra("points", pointscounter);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if (Math.abs(deltaX) > MIN_DISTANCE || Math.abs(deltaY) > MIN_DISTANCE) {
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        if (x1 < x2) {
                            Intent i = new Intent(Shop.this, MainMenu.class);
                            startActivityWithAnimation(i, R.anim.slide_in_right, R.anim.slide_out_left);
                            returnPointsToMainMenu();
                            finish();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(touchEvent);
    }

    private void returnPointsToMainMenu() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("returnedPoints", pointscounter);
        setResult(RESULT_OK, returnIntent);

    }

    private void startActivityWithAnimation(Intent intent, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }
}