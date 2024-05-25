package com.example.mobdevproject;

import android.os.Bundle;
import android.view.MotionEvent;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.annotation.AnimRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserProfile extends AppCompatActivity {

    float x1,x2,y1,y2;
    private static final int MIN_DISTANCE = 150;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.buyLoad), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if(Math.abs(deltaX) > MIN_DISTANCE || Math.abs(deltaY) > MIN_DISTANCE) {
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        if (x1 > x2) {
                            Intent i = new Intent(UserProfile.this, MainMenu.class);
                            startActivityWithAnimation(i, R.anim.slide_in_right, R.anim.slide_out_left);
                        }
//                        else {
//                            Intent i = new Intent(UserProfile.this, UserProfile.class);
//                            startActivityWithAnimation(i, R.anim.slide_in_left, R.anim.slide_out_right);
//                        }
                    }
                }
                break;
        }
        return false;
    }

    private void startActivityWithAnimation(Intent intent, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }
}