package com.example.mobdevproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.AnimRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class popupLoad extends AppCompatActivity {

    int pointscounter;
    EditText amount;

    float x1, x2, y1, y2;
    private static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_popup_load);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.userProfile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        pointscounter = intent.getIntExtra("points", 0);
        TextView pointCounter = findViewById(R.id.pointCounter);
        pointCounter.setText("Points: " + pointscounter);

        amount = findViewById(R.id.etxtLoadAmount);
        Button buyLoad = findViewById(R.id.btnPurchaseLoad);
        buyLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountText = amount.getText().toString();
                if (!amountText.isEmpty()) {
                    int loadAmount = Integer.parseInt(amountText);
                    int updatedPoints = pointscounter - (2 * loadAmount);

                    if (pointscounter == 0 || pointscounter < (2 * loadAmount)) {
                        Toast.makeText(popupLoad.this, "Insufficient Points", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent returnIntent = new Intent(popupLoad.this, Shop.class);
                        returnIntent.putExtra("points", updatedPoints);
                        startActivity(returnIntent);
                        finish();
                    }
                } else {
                    amount.setError("Please enter an amount");
                }
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                return true;  // Returning true to indicate event was handled
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if (Math.abs(deltaY) > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(deltaX)) {
                    if (y1 < y2) {  // Swipe down
                        Intent i = new Intent(popupLoad.this, Shop.class);  // Replace with your target activity
                        startActivityWithAnimation(i, R.anim.slide_in_up, R.anim.slide_out_down);
                        finish();
                    }
                }
                return true;  // Returning true to indicate event was handled
        }
        return super.onTouchEvent(touchEvent);
    }

    private void startActivityWithAnimation(Intent intent, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }
}
