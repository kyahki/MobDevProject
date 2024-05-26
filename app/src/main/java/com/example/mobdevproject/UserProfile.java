package com.example.mobdevproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.AnimRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserProfile extends AppCompatActivity {

    float x1, x2, y1, y2;
    int pointscounter = 0;
    int tasks = 0;
    private static final int MIN_DISTANCE = 150;
    private static final int REQUEST_CODE_SHOP = 1;

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.userProfile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        pointscounter = intent.getIntExtra("points", 0);
        tasks = intent.getIntExtra("tasks", 0);
        TextView UserPointsText = findViewById(R.id.UserPoints);
        TextView tasksCompletedText = findViewById(R.id.tasksCompletedText);
        UserPointsText.setText("Current Points: " + pointscounter);
        tasksCompletedText.setText("Completed Tasks: " + tasks);
        Button btnLogout = findViewById(R.id.logoutButton);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, MainActivity.class);
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
                        if (x1 > x2) {
                            returnPointsToMainMenu();
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
        returnIntent.putExtra("returnedTasks", tasks);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
