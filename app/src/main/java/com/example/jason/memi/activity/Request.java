package com.example.jason.memi.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jason.memi.R;
import com.example.jason.memi.RequestAllDetails;

public class Request extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    public static Spinner Receipient;
    public static Spinner Weight;
    //public static Spinner Distance;
    //public static Spinner Payment;

    public static String weight;
    public static String receipient;
   // public static String distance;
   // public static String payment;



    String recipientArray [] = {"Select Recipient","Sibonubuhle","Novuyo","Musa"};
    String weightArray [] = {"Select Weight","20kg-50kg","51kg-80kg","81kg-100kg",">100kg"};
   // String distanceArray [] = {"Select Distance","5km-50km","50-100km",">100km"};
   // String paymentArray [] = {"Payment Method","Card","Cash","Eft"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Button btnProceed = (Button) findViewById(R.id.btnProceed);

        btnProceed.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent myIntent = new Intent(Request.this,
                        RequestAllDetails.class);
                startActivity(myIntent);


            }

        });


        Receipient = (Spinner) findViewById(R.id.txtRecipient);
        // Creating adapter for spinner
        ArrayAdapter<String> recipientAdapter = new ArrayAdapter<String>(Request.this,
                R.layout.spinner_item, recipientArray);
        // Drop down layout style - list view with radio button
        recipientAdapter
                .setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        Receipient.setAdapter(recipientAdapter);
        Receipient.setOnItemSelectedListener(this);

        Weight = (Spinner) findViewById(R.id.txtWeight);
        Weight.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(Request.this,
                R.layout.spinner_item, weightArray);
        // Drop down layout style - list view with radio button
        weightAdapter
                .setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        Weight.setAdapter(weightAdapter);

        /*Distance = (Spinner) findViewById(R.id.txtDistance);
        Distance.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<String>(Request.this,
                R.layout.spinner_item, distanceArray);
        // Drop down layout style - list view with radio button
        distanceAdapter
                .setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        Distance.setAdapter(distanceAdapter);


        Payment = (Spinner) findViewById(R.id.txtPayment);
        Payment.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(Request.this,
                R.layout.spinner_item, paymentArray);
        // Drop down layout style - list view with radio button
        paymentAdapter
                .setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        Payment.setAdapter(paymentAdapter);
        */




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (parent.getId()) {
            case R.id.txtWeight:
                // do stuffs with you spinner 1
                weight = Weight.getItemAtPosition(pos).toString();

                break;
           /* case R.id.txtDistance:
                // do stuffs with you spinner 2
                distance = Distance.getItemAtPosition(pos).toString();

                break;
            case R.id.txtPayment:
                // do stuffs with you spinner 2
                payment = Payment.getItemAtPosition(pos).toString();

                break;
                */
            case R.id.txtRecipient:
                // do stuffs with you spinner 2
                receipient = Receipient.getItemAtPosition(pos).toString();

                break;
            default:
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


}
