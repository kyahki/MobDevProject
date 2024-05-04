package com.example.mobdevproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainMenu extends AppCompatActivity {

    private static final int MIN_DISTANCE = 150;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        ScrollView scrollView = findViewById(R.id.mainScrollView);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            private float x1, x2;

            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        if (Math.abs(deltaX) > MIN_DISTANCE)
                        {
                            if (x2 > x1)
                            {
                                Intent i = new Intent(MainMenu.this, UserProfile.class);
                                startActivity(i);
                            } else
                            {
                                Intent i = new Intent(MainMenu.this, Leaderboard.class);
                                startActivity(i);
                            }
                        }
                        break;
                }
                return false;
            }
        });
    }



//    @Override
//    public boolean onTouchEvent(MotionEvent touchEvent){
//        switch(touchEvent.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                x1 = touchEvent.getX();
//                y1 = touchEvent.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = touchEvent.getX();
//                y2 = touchEvent.getY();
//                if(x1 < x2){
//                Intent i = new Intent(MainMenu.this, UserProfile.class);
//                startActivity(i);
//            }else if(x1 > x2){
//                Intent i = new Intent(MainMenu.this,Leaderboard.class);
//                startActivity(i);
//            }
//            break;
//        }
//        return false;
//    }


}