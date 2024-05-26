package com.example.mobdevproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
//    public static final String URL = "jdbc:mysql://localhost:3306/dbmobdev";
//
//    public static final String USERNAME = "root";
//    public static final String PASSWORD ="";
//
//    static Connection getConnection(){
//        Connection c = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            c = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//            System.out.println("DB connected!");
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return c;
//    }

    EditText username;
    EditText password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getConnection();
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if ((user.equals("tisumi") && pass.equals("1234")) ||
                        (user.equals("isleden") && pass.equals("password123")) ||
                        (user.equals("kilotski") && pass.equals("password123"))) {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
           
        });


    }
    public void goToSignUpPage(View view) {
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }


}