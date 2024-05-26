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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class popupLoad extends AppCompatActivity {

    int pointscounter;
    EditText amount;

    int tasks;
    float x1, x2, y1, y2;
    private static final int MIN_DISTANCE = 150;
    private static final int REQUEST_CODE_LOAD = 2;

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
        tasks = intent.getIntExtra("tasks",0);
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
                    int requiredPoints = 2 * loadAmount;

                    if (pointscounter >= requiredPoints) {
                        int updatedPoints = pointscounter - requiredPoints;
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("returnedPoints", updatedPoints);
                        returnIntent.putExtra("returnedTasks",tasks);
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    } else {
                        Toast.makeText(popupLoad.this, "Insufficient Points", Toast.LENGTH_SHORT).show();
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
                float deltaY = y2 - y1;
                if (Math.abs(deltaY) > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(x2 - x1)) {
                    if (y1 < y2) {  // Swipe down
                        onBackPressed();
                    }
                }
                return true;  // Returning true to indicate event was handled
        }
        return super.onTouchEvent(touchEvent);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("returnedPoints", pointscounter);
        returnIntent.putExtra("returnedTasks",tasks);
        setResult(RESULT_OK, returnIntent);
        super.onBackPressed();
    }
}
