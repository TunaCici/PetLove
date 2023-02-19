package com.petlove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // add listener to register button
        Button textButtonLogin2 = findViewById(R.id.textButtonLogin2);
        textButtonLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent);
            }
        });

        // add listener to sign in button
        Button textButtonLogin = findViewById(R.id.textButtonLogin);
        textButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the databaseManager
                DatabaseManager databaseManager = new DatabaseManager(LoginActivity.this);

                // get the editable text fields
                EditText emailFieldSignin= findViewById(R.id.emailFieldSiginEdit);
                EditText passwordFieldSigin = findViewById(R.id.passwordFieldSiginEdit);

                String email = emailFieldSignin.getText().toString().trim();
                String password = passwordFieldSigin.getText().toString().trim();

                // check if these fields empty
                if (!email.isEmpty()) {
                    if (!password.isEmpty()) {
                        // check if exists in the database
                        Cursor cursor = databaseManager.getUsers();

                        ArrayList<String> email_list = new ArrayList<String>();
                        ArrayList<String> password_list = new ArrayList<String>();

                        if (cursor.getCount() == 0) {
                            Toast.makeText(LoginActivity.this, "Could not find the given email. Please register.", Toast.LENGTH_LONG).show();
                        }

                        else {
                            while (cursor.moveToNext()) {
                                String curr_id = cursor.getString(0);
                                String curr_email = cursor.getString(2);
                                String curr_password = cursor.getString(3);

                                if (curr_email.equals(email)) {
                                    if (curr_password.equals(password)) {
                                        // add it to the curr user
                                        databaseManager.addCurrUser(curr_id);

                                        // start the main activity
                                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(myIntent);
                                        finish();
                                        return;
                                    }
                                }
                            }

                            Toast.makeText(LoginActivity.this, "Email and passwords do not match.", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "You must enter a password.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "You must enter an email.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}