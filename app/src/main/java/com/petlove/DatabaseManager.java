package com.petlove;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "petlove";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_NAME_CURR_USER = "curr_user";

    private static final String TABLE_NAME_USERS = "users";
    private static final String TABLE_NAME_PETS = "pets";
    private static final String TABLE_NAME_DOCS = "docs";
    private static final String TABLE_NAME_PARKS = "parks";
    private static final String TABLE_NAME_DOCS_REVIEWS = "docs_reviews";
    private static final String TABLE_NAME_PARKS_REVIEWS = "parks_reviews";
    private static final String TABLE_NAME_SETTINGS= "settings";

    private static final String COLUMN_NAME_CURR_USER_ID = "_id";
    private static final String COLUMN_NAME_CURR_USER_OWNER = "owner_id";

    private static final String COLUMN_NAME_USERS_ID = "_id";
    private static final String COLUMN_NAME_USERS_NAME = "name";
    private static final String COLUMN_NAME_USERS_EMAIL = "email";
    private static final String COLUMN_NAME_USERS_PASSWORD = "password";

    private static final String COLUMN_NAME_PETS_ID = "_id";
    private static final String COLUMN_NAME_PETS_OWNER = "owner_id";
    private static final String COLUMN_NAME_PETS_NAME = "name";
    private static final String COLUMN_NAME_PETS_BIRTHDATE = "birthdate";
    private static final String COLUMN_NAME_PETS_TYPE = "type";

    private static final String COLUMN_NAME_DOCS_ID = "_id";
    private static final String COLUMN_NAME_DOCS_NAME = "name";
    private static final String COLUMN_NAME_DOCS_LOCATION = "location";

    private static final String COLUMN_NAME_PARKS_ID = "_id";
    private static final String COLUMN_NAME_PARKS_NAME = "name";
    private static final String COLUMN_NAME_PARKS_LOCATION = "location";

    private static final String COLUMN_NAME_DOCS_REVIEW_ID = "_id";
    private static final String COLUMN_NAME_DOCS_REVIEW_OWNER = "owner_id";
    private static final String COLUMN_NAME_DOCS_REVIEW_SCORE = "score";
    private static final String COLUMN_NAME_DOCS_REVIEW_COMMENT = "comment";

    private static final String COLUMN_NAME_PARKS_REVIEW_ID = "_id";
    private static final String COLUMN_NAME_PARKS_REVIEW_OWNER = "owner_id";
    private static final String COLUMN_NAME_PARKS_REVIEW_SCORE = "score";
    private static final String COLUMN_NAME_PARKS_REVIEW_COMMENT = "comment";

    private static final String COLUMN_NAME_SETTINGS_ID = "_id";
    private static final String COLUMN_NAME_SETTINGS_KEY = "name";
    private static final String COLUMN_NAME_SETTINGS_VALUE = "value";


    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String curr_user_query =
                "CREATE TABLE " + TABLE_NAME_CURR_USER +
                        " (" + COLUMN_NAME_CURR_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_CURR_USER_OWNER + " INTEGER);";

        String users_query =
                "CREATE TABLE " + TABLE_NAME_USERS +
                        " (" + COLUMN_NAME_USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_USERS_NAME + " TEXT, " +
                        COLUMN_NAME_USERS_EMAIL + " TEXT, " +
                        COLUMN_NAME_USERS_PASSWORD + " TEXT);";

        String pets_query =
                "CREATE TABLE " + TABLE_NAME_PETS +
                        " (" + COLUMN_NAME_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_PETS_OWNER + " INTEGER, " +
                        COLUMN_NAME_PETS_NAME + " TEXT, " +
                        COLUMN_NAME_PETS_BIRTHDATE + " INTEGER, " +
                        COLUMN_NAME_PETS_TYPE + " TEXT);";
        String docs_query =
                "CREATE TABLE " + TABLE_NAME_DOCS +
                        " (" + COLUMN_NAME_DOCS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_DOCS_LOCATION + " TEXT, " +
                        COLUMN_NAME_DOCS_NAME + " TEXT);";

        String parks_query =
                "CREATE TABLE " + TABLE_NAME_PARKS +
                        " (" + COLUMN_NAME_PARKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_PARKS_LOCATION + " TEXT, " +
                        COLUMN_NAME_PARKS_NAME + " TEXT);";

        String docs_review_query =
                "CREATE TABLE " + TABLE_NAME_DOCS_REVIEWS +
                        " (" + COLUMN_NAME_DOCS_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_DOCS_REVIEW_OWNER + " INTEGER, " +
                        COLUMN_NAME_DOCS_REVIEW_SCORE + " REAL, " +
                        COLUMN_NAME_DOCS_REVIEW_COMMENT + " TEXT);";

        String parks_review_query =
                "CREATE TABLE " + TABLE_NAME_PARKS_REVIEWS +
                        " (" + COLUMN_NAME_PARKS_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_PARKS_REVIEW_OWNER + " INTEGER, " +
                        COLUMN_NAME_PARKS_REVIEW_SCORE + " REAL, " +
                        COLUMN_NAME_PARKS_REVIEW_COMMENT + " TEXT);";

        String settings_query =
                "CREATE TABLE " + TABLE_NAME_SETTINGS +
                        " (" + COLUMN_NAME_SETTINGS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME_SETTINGS_KEY + " TEXT, " +
                        COLUMN_NAME_SETTINGS_VALUE + " TEXT);";

        db.execSQL(curr_user_query);
        db.execSQL(users_query);
        db.execSQL(pets_query);
        db.execSQL(docs_query);
        db.execSQL(parks_query);
        db.execSQL(docs_review_query);
        db.execSQL(parks_review_query);
        db.execSQL(settings_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CURR_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DOCS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PARKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DOCS_REVIEWS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PARKS_REVIEWS);
        onCreate(db);
    }

    public void addCurrUser(String owner_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_CURR_USER_OWNER, owner_id);


        long result = db.insert(TABLE_NAME_CURR_USER, null, cv);
        if (result == -1) {
            Log.d("CREATION", "addCurrUser: Failed!");
        }
        else {
            Log.d("CREATION", "addCurrUser: Success!");
        }
    }

    public void addUser(String name, String email, String password) {

        Log.d("CREATION", "ADDING USER");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_USERS_NAME, name);
        cv.put(COLUMN_NAME_USERS_EMAIL, email);
        cv.put(COLUMN_NAME_USERS_PASSWORD, password);

        long result = db.insert(TABLE_NAME_USERS, null, cv);
        if (result == -1) {
            Log.d("CREATION", "addUser: Failed!");
        }
        else {
            Log.d("CREATION", "addUser: Success!");
        }
    }

    public void addPet(String owner_id, String name, String birthday,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_PETS_OWNER, owner_id);
        cv.put(COLUMN_NAME_PETS_NAME, name);
        cv.put(COLUMN_NAME_PETS_BIRTHDATE, birthday);
        cv.put(COLUMN_NAME_PETS_TYPE, type);

        long result = db.insert(TABLE_NAME_PETS, null, cv);
        if (result == -1) {
            Log.d("CREATION", "addPet: Failed!");
        }
        else {
            Log.d("CREATION", "addPet: Success!");
        }
    }

    public void addPark(String name, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_PARKS_NAME, name);
        cv.put(COLUMN_NAME_PARKS_LOCATION, location);

        long result = db.insert(TABLE_NAME_PARKS, null, cv);
        if (result == -1) {
            Log.d("CREATION", "addDoc: Failed!");
        }
        else {
            Log.d("CREATION", "addDoc: Success!");
        }
    }

    public void addDoc(String name, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_DOCS_NAME, name);
        cv.put(COLUMN_NAME_DOCS_LOCATION, location);

        long result = db.insert(TABLE_NAME_DOCS, null, cv);
        if (result == -1) {
            Log.d("CREATION", "addDoc: Failed!");
        }
        else {
            Log.d("CREATION", "addDoc: Success!");
        }
    }

    public void addDocReview(String owner_id, Double score, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_DOCS_REVIEW_OWNER, owner_id);
        cv.put(COLUMN_NAME_DOCS_REVIEW_SCORE, score);
        cv.put(COLUMN_NAME_DOCS_REVIEW_COMMENT, comment);

        long result = db.insert(TABLE_NAME_DOCS_REVIEWS, null, cv);
        if (result == -1) {
            Log.d("CREATION", "addDocReview: Failed!");
        }
        else {
            Log.d("CREATION", "addDocReview: Success!");
        }
    }

    public void addParkReview(String owner_id, Double score, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_PARKS_REVIEW_OWNER, owner_id);
        cv.put(COLUMN_NAME_PARKS_REVIEW_SCORE, score);
        cv.put(COLUMN_NAME_PARKS_REVIEW_COMMENT, comment);

        long result = db.insert(TABLE_NAME_PARKS_REVIEWS, null, cv);
        if (result == -1) {
            Log.d("CREATION", "addParkReview: Failed!");
        }
        else {
            Log.d("CREATION", "addParkReview: Success!");
        }
    }

    public void addSetting(String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_SETTINGS_KEY, key);
        cv.put(COLUMN_NAME_SETTINGS_VALUE, value);

        long result = db.insert(TABLE_NAME_SETTINGS, null, cv);
        if (result == -1) {
            Log.d("CREATION", "addSetting: Failed!");
        }
        else {
            Log.d("CREATION", "addSetting: Success!");
        }
    }

    public Cursor getCurrUser() {
        String query = "SELECT * FROM " + TABLE_NAME_CURR_USER;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getUsers() {
        String query = "SELECT * FROM " + TABLE_NAME_USERS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getDocs() {
        String query = "SELECT * FROM " + TABLE_NAME_DOCS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getParks() {
        String query = "SELECT * FROM " + TABLE_NAME_PARKS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getPets() {
        String query = "SELECT * FROM " + TABLE_NAME_PETS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getDocsReview() {
        String query = "SELECT * FROM " + TABLE_NAME_DOCS_REVIEWS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getParksReview() {
        String query = "SELECT * FROM " + TABLE_NAME_PARKS_REVIEWS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor getSettings() {
        String query = "SELECT * FROM " + TABLE_NAME_SETTINGS;
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public void delCurrUser() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME_CURR_USER, null, null);
    }

}
