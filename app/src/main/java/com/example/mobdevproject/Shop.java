package com.example.mobdevproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Shop extends AppCompatActivity {

    float x1, x2, y1, y2;
    int pointscounter;
    int tasks;

    private TextView pointsTextView;
    private static final int MIN_DISTANCE = 150;
    private static final int REQUEST_CODE_SUBSCRIPTION = 1;

    private static final int REQUEST_CODE_LOAD = 2;
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
        tasks = intent.getIntExtra("tasks", 0);

        pointsTextView = findViewById(R.id.textView2);
        updatePoints(pointscounter);

        Button showLoad = findViewById(R.id.btnBuyLoad);
        showLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, popupLoad.class);
                intent.putExtra("points", pointscounter);
                intent.putExtra("tasks", tasks);
                startActivityForResult(intent, REQUEST_CODE_SUBSCRIPTION);
            }
        });

        Button showNetflix = findViewById(R.id.btnBuyNetflix);
        showNetflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, buyNetflix.class);
                intent.putExtra("points", pointscounter);
                intent.putExtra("tasks", tasks);
                startActivityForResult(intent, REQUEST_CODE_SUBSCRIPTION);
            }
        });
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
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if (Math.abs(deltaX) > MIN_DISTANCE && Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (x1 < x2) {
                        returnPointsToMainMenu();
                    }
                }
                return true;
        }
        return super.onTouchEvent(touchEvent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_CODE_SUBSCRIPTION || requestCode == REQUEST_CODE_LOAD) && resultCode == RESULT_OK) {
            if (data != null) {
                int returnedPoints = data.getIntExtra("returnedPoints", pointscounter);
                int returnedTasks = data.getIntExtra("returnedTasks", tasks);
                pointscounter = returnedPoints;
                tasks = returnedTasks;

                updatePoints(pointscounter);
            }
        }
    }

    private void returnPointsToMainMenu() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("returnedPoints", pointscounter);
        returnIntent.putExtra("returnedTasks", tasks);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    private void updatePoints(int newPoints) {
        pointscounter = newPoints;
        pointsTextView.setText("Points: " + pointscounter);
    }
}
