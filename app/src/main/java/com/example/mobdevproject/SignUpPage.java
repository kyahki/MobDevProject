// SignUpPage.java
package com.example.mobdevproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpPage extends AppCompatActivity {
    private EditText usernameEditText, emailEditText, passwordEditText, rePasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        rePasswordEditText = findViewById(R.id.repassword);

//        findViewById(R.id.signupbtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                  onCreateAccount();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                    Toast.makeText(SignUpPage.this, "Database error.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

//    private void onCreateAccount() throws SQLException {
//        String username = usernameEditText.getText().toString();
//        String email = emailEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//        String rePassword = rePasswordEditText.getText().toString();
//
//        if (!password.equals(rePassword)) {
//            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        try (Connection c = MySqlConnection.getConnection()) {
//            c.setAutoCommit(false);
//
//            try (PreparedStatement statement = c.prepareStatement(
//                    "INSERT INTO tblmobdevuser (name, email, password) VALUES (?, ?, ?)")) {
//                statement.setString(1, username);
//                statement.setString(2, email);
//                statement.setString(3, password);
//                int rows = statement.executeUpdate();
//
//                if (rows > 0) {
//                    Toast.makeText(this, "Account created successfully.", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Failed to create account.", Toast.LENGTH_SHORT).show();
//                }
//
//                c.commit();
//            } catch (SQLException e) {
//                c.rollback();
//                throw new RuntimeException("Transaction failed.", e);
//            } finally {
//                c.setAutoCommit(true);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Database connection error.", e);
//        }
//    }

    public void goToLoginPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
