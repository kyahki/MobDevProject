package com.example.mobdevproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.AnimRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class buyNetflix extends AppCompatActivity {

    int pointscounter;
    int tasks;

    Button btn1month, btn3months, btn1year;
    float x1, x2, y1, y2;
    private static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_netflix);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.buyNetflix), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        pointscounter = intent.getIntExtra("points", 0);
        tasks = intent.getIntExtra("tasks", 0);
        TextView pointCounter = findViewById(R.id.pointCounter);
        pointCounter.setText("Points: " + pointscounter);

        btn1month = findViewById(R.id.btn1Month);
        btn3months = findViewById(R.id.btn3months);
        btn1year = findViewById(R.id.btn1Year);

        btn1month.setOnClickListener(v -> handleSubscription(120));
        btn3months.setOnClickListener(v -> handleSubscription(360));
        btn1year.setOnClickListener(v -> handleSubscription(1400));
    }

    private void handleSubscription(int requiredPoints) {
        if (pointscounter >= requiredPoints) {
            int updatedPoints = pointscounter - requiredPoints;
            Intent returnIntent = new Intent();
            returnIntent.putExtra("returnedPoints", updatedPoints);
            returnIntent.putExtra("returnedTasks",tasks);
            setResult(RESULT_OK, returnIntent);
            finish();
        } else {
            Toast.makeText(buyNetflix.this, "Insufficient Points", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                return true;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                float deltaY = y2 - y1;
                if (Math.abs(deltaY) > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(x2 - x1)) {
                    if (y1 < y2) {  // Swipe down
                        onBackPressed();
                    }
                }
                return true;
        }
        return super.onTouchEvent(touchEvent);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("returnedPoints", pointscounter);
        returnIntent.putExtra("returnedTasks", tasks);
        setResult(RESULT_OK, returnIntent);
        super.onBackPressed();
    }
}