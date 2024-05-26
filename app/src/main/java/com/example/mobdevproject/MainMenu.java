package com.example.mobdevproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.AnimRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    private static final int MIN_DISTANCE = 150;
    private List<String> tasks = new ArrayList<>();
    private int points = 0;
    private int tasksFinished = 0;
    private int pointsReturned;
    private TextView currentTextView, pointCounter;
    private static final int REQUEST_CODE_SHOP = 1;
    private static final int REQUEST_CODE_USER_PROFILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button btnAddSched = findViewById(R.id.btnAddSched);
        ScrollView scrollView = findViewById(R.id.mainScrollView);
        pointCounter = findViewById(R.id.pointCounter);

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

        Button btnMonday = findViewById(R.id.btnMonday);
        Button btnTuesday = findViewById(R.id.btnTuesday);
        Button btnWednesday = findViewById(R.id.btnWednesday);
        Button btnThursday = findViewById(R.id.btnThursday);
        Button btnFriday = findViewById(R.id.btnFriday);
        Button btnSaturday = findViewById(R.id.btnSaturday);
        Button btnSunday = findViewById(R.id.btnSunday);

        // Set click listeners for each button
        btnMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity();
            }
        });

        btnTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity();
            }
        });

        btnWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity();
            }
        });

        btnThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity();
            }
        });

        btnFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity();
            }
        });

        btnSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity();
            }
        });

        btnSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreateActivity();
            }
        });

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
                                i.putExtra("points", points);
                                i.putExtra("tasks", tasksFinished);

                                startActivityForResult(i, REQUEST_CODE_USER_PROFILE);
                                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                            } else {
                                Intent i = new Intent(MainMenu.this, Shop.class);
                                i.putExtra("points", points);
                                i.putExtra("tasks",tasksFinished);
                                startActivityForResult(i, REQUEST_CODE_SHOP);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }
                        }
                        break;
                }
                return false;
            }
        });

        Intent update = getIntent();
        points = update.getIntExtra("returnedPoints", 0);
        tasksFinished = update.getIntExtra("returnedTasks", 0);
        updatePoints(points);
        updateTasksCompleted(tasksFinished);
    }

    private void startActivityWithAnimation(Intent intent, @AnimRes int enterAnim, @AnimRes int exitAnim) {
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
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

    private void updatePoints(int newPoints) {
        points = newPoints;
        pointCounter.setText("Points: " + points);
    }

    private void updateTasksCompleted(int newTasksCompleted) {
        tasksFinished = newTasksCompleted;
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

        TextView pointCounter = view.findViewById(R.id.pointCounter);
        pointCounter.setText("Points: " + points);

        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String task = tasksForCurrentTime.get(position);
                if (!task.contains("[Completed]")) {
                    tasksForCurrentTime.set(position, "[Completed] " + task);
                    adapter.notifyDataSetChanged();
                    points+=20;
                    tasksFinished++;
                    pointCounter.setText("Points: " + points);
                }
            }
        });

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

        builder.setNeutralButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < tasksForCurrentTime.size(); i++) {
                    if (tasksForCurrentTime.get(i).contains("[Completed]")) {
                        tasksForCurrentTime.remove(i);
                        i--;
                    }
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(MainMenu.this, "Tasks completed and removed", Toast.LENGTH_SHORT).show();
                updatePoints(points);
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void recreateActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_CODE_USER_PROFILE || requestCode == REQUEST_CODE_SHOP) && resultCode == RESULT_OK) {
            if (data != null) {
                int returnedPoints = data.getIntExtra("returnedPoints", points);
                int returnedTasks = data.getIntExtra("returnedTasks", tasksFinished);
                updatePoints(returnedPoints);
                updateTasksCompleted(returnedTasks);
            }
        }
    }
}
