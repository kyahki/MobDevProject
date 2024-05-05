package com.example.mobdevproject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    private static final int MIN_DISTANCE = 150;
    private List<String> tasks = new ArrayList<>();
    private TextView currentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button btnAddSched = findViewById(R.id.btnAddSched);
        ScrollView scrollView = findViewById(R.id.mainScrollView);



        setTimeTextViewClickListener(R.id.sixam, "6:00 AM");
        setTimeTextViewClickListener(R.id.sevenam, "7:00 AM");
        setTimeTextViewClickListener(R.id.eightam, "8:00 AM");
        setTimeTextViewClickListener(R.id.nineam, "9:00 AM");
        setTimeTextViewClickListener(R.id.tenam, "10:00 AM");
        setTimeTextViewClickListener(R.id.elevenam, "11:00 AM");
        setTimeTextViewClickListener(R.id.twelvepm, "12:00 PM");
        setTimeTextViewClickListener(R.id.onepm, "1:00 PM");
        setTimeTextViewClickListener(R.id.twopm, "2:00 PM");
        setTimeTextViewClickListener(R.id.threepm, "3:00 PM");
        setTimeTextViewClickListener(R.id.fourpm, "4:00 PM");
        setTimeTextViewClickListener(R.id.fivepm, "5:00 PM");
        setTimeTextViewClickListener(R.id.sixpm, "6:00 PM");
        setTimeTextViewClickListener(R.id.sevenpm, "7:00 PM");
        setTimeTextViewClickListener(R.id.eightpm, "8:00 PM");
        setTimeTextViewClickListener(R.id.ninepm, "9:00 PM");
        setTimeTextViewClickListener(R.id.tenpm, "10:00 PM");

        btnAddSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != null) {

                    showAddScheduleDialog(currentTextView.getText().toString());
                } else {
                    Toast.makeText(MainMenu.this, "Select a time slot first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            private float x1, x2;

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        if (Math.abs(deltaX) > MIN_DISTANCE) {
                            if (x2 > x1) {
                                Intent i = new Intent(MainMenu.this, UserProfile.class);
                                startActivity(i);
                            } else {
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

    private void setTimeTextViewClickListener(int textViewId, final String time) {
        TextView textView = findViewById(textViewId);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTextView = (TextView) v;

                showAddScheduleDialog(time);
            }
        });
    }
    private void showAddScheduleDialog(String time) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Schedule");

        View view = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        EditText editTextTask = view.findViewById(R.id.editTextTask);
        ListView listViewTasks = view.findViewById(R.id.listViewTasks);


        List<String> tasksForCurrentTime = new ArrayList<>();
        for (String task : tasks) {
            if (task.contains(time)) {
                tasksForCurrentTime.add(task);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasksForCurrentTime);
        listViewTasks.setAdapter(adapter);

        builder.setView(view);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String task = editTextTask.getText().toString().trim();
                if (!task.isEmpty()) {
                    tasks.add(time + ": " + task);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainMenu.this, "Schedule added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainMenu.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", null);

        Dialog dialog = builder.create();
        dialog.show();
    }




}




