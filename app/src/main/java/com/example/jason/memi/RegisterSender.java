package com.example.jason.memi;

import com.example.jason.memi.R;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterSender extends AppCompatActivity {


    public static EditText Name;
    public static EditText Surname;
    public static EditText Id;
    public static EditText Mobile;
    public static EditText Address;
    public static EditText Email;
    public static EditText Password;


    public static String _name;
    public static String _surname;
    public static String _id;
    public static String _mobile;
    public static String _address;
    public static String _email;
    public static String _password;

    public static ArrayList<HashMap<String, String>> valuesmap = new ArrayList<HashMap<String, String>>();
    DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sender2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataBaseAdapter = new DataBaseAdapter(this);
        dataBaseAdapter = dataBaseAdapter.open();

        Name = (EditText) findViewById(R.id.name);
        Surname = (EditText) findViewById(R.id.surname);
        Id = (EditText) findViewById(R.id.id);
        Mobile = (EditText) findViewById(R.id.mobile);
        Address = (EditText) findViewById(R.id.address);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);


        Button register = (Button) findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {



                _name =Name.getText().toString();
                _surname = Surname.getText().toString();
                _id = Id.getText().toString();
                _mobile =Id.getText().toString() ;
                _address = Address.getText().toString() ;
                _email =  Email.getText().toString();
                _password = Password.getText().toString() ;

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", Name.getText().toString());
                map.put("surname", Surname.getText().toString());
                map.put("id", Id.getText().toString());
                map.put("mobile", Mobile.getText().toString());
                map.put("address", Address.getText().toString());
                map.put("email", Email.getText().toString());
                map.put("password", Password.getText().toString());

                valuesmap.add(map);

                dataBaseAdapter.insertSender(valuesmap);

            }


        });




    }

}
