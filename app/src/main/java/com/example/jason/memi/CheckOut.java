package com.example.jason.memi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jason.memi.activity.ConfirmRequest;

public class CheckOut extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    public static Spinner Payment;
    public static String payment;
    String paymentArray [] = {"Payment Method","Card","Cash","Eft"};

    TextView distanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        distanceText = (TextView) findViewById(R.id.txtEstimatedDistance);
        distanceText.setText(String.valueOf(RequestAllDetails.distance + "m"));

        Payment = (Spinner) findViewById(R.id.txtPayment);
        Payment.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(CheckOut.this,
                R.layout.spinner_item, paymentArray);
        // Drop down layout style - list view with radio button
        paymentAdapter
                .setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        Payment.setAdapter(paymentAdapter);


        Button proceed = (Button) findViewById(R.id.btnProceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                RespOne();


            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (parent.getId()) {
            /*case R.id.txtWeight:
                // do stuffs with you spinner 1
                weight = Weight.getItemAtPosition(pos).toString();

                break;
                */

            case R.id.txtPayment:
                // do stuffs with you spinner 2
                payment = Payment.getItemAtPosition(pos).toString();

                if(payment.equals("Eft")){
                    Resp();
                }

                break;

            /*case R.id.txtRecipient:
                // do stuffs with you spinner 2
                receipient = Receipient.getItemAtPosition(pos).toString();

                break;
                */
            default:
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    public void Resp(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(CheckOut.this,R.style.MyDialogTheme);
        builder1.setMessage("Please be advised if payment is eft make sure to enter reference");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        return;

                        /*Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);*/

                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public void RespOne(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(CheckOut.this,R.style.MyDialogTheme);
        builder1.setMessage("Request successfully submitted" +
                "");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //return;

                        Intent intent = new Intent(getApplicationContext(),RequestAllDetails.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
}
