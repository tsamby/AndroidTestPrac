package com.example.jason.memi;

/**
 * Created by jason on 2017/12/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DataBaseAdapter
{
    double additional_cash;
    String formattedAddditionalCash;

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text,FINGERPRINT text,IMAGE text); ";

    static final String CREATE_SENDER_TABLE = "create table "+"SENDER"+
            "( " +"ID"+" integer primary key autoincrement,"+"NAME"+"  text,"+"SURNAME"+"  text,"+"ID"+" text,"+"MOBILE"+" text,"+"ADDRESS"+" text,"+"EMAIL"+" text,"+"PASSWORD"+" text); ";

    static final String CREATE_RECIPIENT_TABLE = "create table "+"RECIPIENT"+
            "( " +"ID"+" integer primary key autoincrement,"+"NAME"+"  text,"+"SURNAME"+"  text,"+"ID"+" text,"+"MOBILE"+" text,"+"ADDRESS"+" text,"+"EMAIL"+" text,"+"PASSWORD"+" text); ";

    static final String CREATE_REQUEST_TABLE = "create table "+"REQUEST"+
            "( " +"ID"+" integer primary key autoincrement,"+"DATE"+"  text,"+"TIME"+"  text,"+"SENDER"+" text,"+"RECIPIENT"+" text,"+"DESCRIPTION"+" text,"+"WEIGHT"+" text,"+"COLLECT"+" text,"+"DELIVER"+" text,"+"COLLECT_ADDRESS"+" text,"+"DISTANCE"+" text,"+"PAYMENT_METHOD"+" text,"+"ESTIMATED_COST"+" text); ";

    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  DataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  DataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName,String password,String fingerprint,String image)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);
        newValues.put("FINGERPRINT", fingerprint);
        newValues.put("IMAGE",image);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }


    public void insertSender(ArrayList<HashMap<String, String>> queryValues) {

        ContentValues newValues = new ContentValues();

        for(int i=0;i<queryValues.size();i++) {


            newValues.put("DATE", queryValues.get(i).get("date").toString());

            Date date = Calendar.getInstance().getTime();
            DateFormat format = new SimpleDateFormat("HH:mm:ss a");
            String time = format.format(date);
            System.out.println("Time : " + time);

            newValues.put("TIME", time);
            newValues.put("NAME", queryValues.get(i).get("name").toString());
            newValues.put("SURNAME", queryValues.get(i).get("surname").toString());
            newValues.put("ID", queryValues.get(i).get("id").toString());
            newValues.put("MOBILE",queryValues.get(i).get("mobile").toString());
            newValues.put("ADDRESS",queryValues.get(i).get("address").toString());
            newValues.put("EMAIL", queryValues.get(i).get("email").toString());
            newValues.put("PASSWORD", queryValues.get(i).get("password").toString());

            db.insert("SENDER", null, newValues);

        }

    }

    public void insertRecipient(ArrayList<HashMap<String, String>> queryValues) {

        ContentValues newValues = new ContentValues();

        for(int i=0;i<queryValues.size();i++) {


            newValues.put("DATE", queryValues.get(i).get("date").toString());

            Date date = Calendar.getInstance().getTime();
            DateFormat format = new SimpleDateFormat("HH:mm:ss a");
            String time = format.format(date);
            System.out.println("Time : " + time);

            newValues.put("TIME", time);
            newValues.put("NAME", queryValues.get(i).get("name").toString());
            newValues.put("SURNAME", queryValues.get(i).get("surname").toString());
            newValues.put("ID", queryValues.get(i).get("id").toString());
            newValues.put("MOBILE",queryValues.get(i).get("mobile").toString());
            newValues.put("ADDRESS",queryValues.get(i).get("address").toString());
            newValues.put("EMAIL", queryValues.get(i).get("email").toString());
            newValues.put("PASSWORD", queryValues.get(i).get("password").toString());

            db.insert("RECIPIENT", null, newValues);

        }

    }

    public void addRequest(String sender,String recipient, String description, String weight, String collect, String deliver,String collect_address,String distance, String _paymethod, String estimated_cost) {

        ContentValues values = new ContentValues();

        Date date = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        System.out.println("Today : " + today);

        //Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("HH:mm:ss a");
        String time = format.format(date);
        System.out.println("Time : " + time);

        values.put("DATE", today);
        values.put("TIME", time);
        values.put("SENDER", sender);
        values.put("RECIPIENT", recipient);
        values.put("DESCRIPTION", description);
        values.put("WEIGHT", weight);
        values.put("COLLECT", collect);
        values.put("DELIVER", deliver);
        values.put("COLLECT_ADDRESS", collect_address);
        values.put("DISTANCE", distance);
        values.put("PAYMENT_METHOD", _paymethod);
        values.put("ESTIMATED_COST", estimated_cost);

        db.insert("REQUEST", null, values);

    }


}

