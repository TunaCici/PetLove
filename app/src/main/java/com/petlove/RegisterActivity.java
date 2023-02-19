package com.petlove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // get the databaseManager
        DatabaseManager databaseManager = new DatabaseManager(RegisterActivity.this);

        // get the editable text fields
        EditText nameFieldRegister = findViewById(R.id.nameFieldRegisterEdit);
        EditText emailFieldRegister = findViewById(R.id.emailFieldRegisterEdit);
        EditText passwordFieldRegister = findViewById(R.id.passwordFieldRegisterEdit);
        EditText passwordFieldRegister2 = findViewById(R.id.passwordFieldRegisterEdit2);

        // add listener to register button
        Button textButtonRegister = findViewById(R.id.textButtonLogin);
        textButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameFieldRegister.getText().toString().trim();
                String email = emailFieldRegister.getText().toString().trim();
                String password = passwordFieldRegister.getText().toString().trim();
                String password2 = passwordFieldRegister2.getText().toString().trim();

                Log.d("CREATION", name);
                Log.d("CREATION", email);
                Log.d("CREATION", password);
                Log.d("CREATION", password2);

                if (!name.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!password.isEmpty()) {
                            if (!password2.isEmpty()) {
                                if (password.equals(password2)) {
                                    // TODO: Register the user here.
                                    databaseManager.addUser(name, email, password);
                                    finish();
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "You must confirm your password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "You must enter a password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "You must enter an email.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "You must enter a name.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}