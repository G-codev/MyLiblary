package com.gustiramadhan.myliblary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME ="student_database";
    private static  final int DATABASE_VERSION = 1;
    private static  final  String TABLE_STUDENT="student";
    private static final String KEY_ID="id";
    private static final String KEY_FIRSTNAME="name";

    private static final String CREATE_TABLE_STUDENT="" +
            "CREATE TABLE "+TABLE_STUDENT+"("
            +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_FIRSTNAME+" TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table",CREATE_TABLE_STUDENT);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL
                ("DROP TABLE IF EXISTS '"+
                        TABLE_STUDENT+"'");
        onCreate(sqLiteDatabase);
    }
    public long addStudentDetail(String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, student);
        long insert = db.insert(TABLE_STUDENT, null, values);
        return insert;
    }
    public ArrayList<String> getAllStudentList(){
        ArrayList<String> studentArrayList = new ArrayList<>();
        String name ="";
        String selectQuery = "SELECT * FROM "+TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                studentArrayList.add(name);
            } while (c.moveToNext());
        }
        return studentArrayList;
    }
}
