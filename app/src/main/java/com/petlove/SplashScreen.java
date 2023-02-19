package com.petlove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DatabaseManager databaseManager = new DatabaseManager(SplashScreen.this);

        Cursor cursor = databaseManager.getSettings();

        if (cursor.getCount() == 0) {
            databaseManager.addSetting("first_time", "false");

            // populate the database
            databaseManager.addPet("0", "Lori", "5", "English Beagle Mix");
            databaseManager.addPet("0", "Lexi", "2", "Labrador");
            databaseManager.addPet("0", "Cookie", "1", "Shepherd");
            // databaseManager.addPet("0", "Maya", "2", "Pug");
            // databaseManager.addPet("0", "Edgar", "3", "Golden Retriever");

            databaseManager.addPet("0", "Sari", "4", "Stray Cat");
            databaseManager.addPet("0", "Smokey", "1", "British Short Hair");
            databaseManager.addPet("0", "Finesse", "8", "Ragdoll");
            // databaseManager.addPet("0", "Jon", "3", "Siberian Cat");
            // databaseManager.addPet("0", "Dimitri", "2", "Russian Blue");

            databaseManager.addDoc("Atwater Veterinary Center","Long Beach, Los Angeles, USA");
            databaseManager.addDoc("Healthy Paws Animal Hospital","Nisantasi, Istanbul, TR");
            databaseManager.addDoc("MyPet's Animal Hospital","Batikent, Ankara, TR");

            databaseManager.addPark("Bolivar Park", "Harvestehude, Hamburg, DE");
            databaseManager.addPark("Central Park", "Manhattan, New York, USA");
            databaseManager.addPark("Genclik Parki", "Altindag, Ankara, TR");
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // get the current user
                        Cursor cursor = databaseManager.getCurrUser();

                        if (cursor.getCount() == 0) {
                            Intent myIntent = new Intent(SplashScreen.this, LoginActivity.class);
                            startActivity(myIntent);
                            finish();
                        }
                        else {
                            Intent myIntent = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(myIntent);
                            finish();
                        }
                    }
                });
            }
        };
        thread.start(); //start the thread
    }
}