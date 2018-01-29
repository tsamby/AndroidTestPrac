package com.example.jason.memi.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.memi.Constants;
import com.example.jason.memi.MainActivityLocation;
import com.example.jason.memi.R;

public class RequestDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AddressResultReceiver mResultReceiver;

    ProgressBar progressBar;
    TextView infoText;
    boolean fetchAddress;
    int fetchType = Constants.USE_ADDRESS_LOCATION;

    private static final String TAG = "MAIN_ACTIVITY";

    //public static Spinner Receipient;
    //public static Spinner Weight;
    public static Spinner Distance;
    public static Spinner Payment;

    //public static String weight;
    //public static String receipient;
    public static String distance;
    public static String payment;


    //String recipientArray [] = {"Select Recipient","Sibonubuhle","Novuyo","Musa"};
    //String weightArray [] = {"Select Weight","20kg-50kg","51kg-80kg","81kg-100kg",">100kg"};
    String distanceArray [] = {"Select Distance","5km-50km","50-100km",">100km"};
    String paymentArray [] = {"Payment Method","Card","Cash","Eft"};

    public static RadioButton collectRadioButton, deliverRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Resp();


        collectRadioButton = (RadioButton) findViewById(R.id.radioCollect);
        deliverRadioButton = (RadioButton) findViewById(R.id.radioDeliver);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        infoText = (TextView) findViewById(R.id.infoText);

        Button btnProceed = (Button) findViewById(R.id.btnProceed);

        btnProceed.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (collectRadioButton.isChecked() ) {

                } else if (deliverRadioButton.isChecked()){

                } else {

                    Toast.makeText(getApplicationContext(), "Please select Collect or Deliver", Toast.LENGTH_SHORT).show();

                }

                Intent myIntent = new Intent(RequestDetails.this,
                        ConfirmRequest.class);
                startActivity(myIntent);


            }

        });


       /* Receipient = (Spinner) findViewById(R.id.txtRecipient);
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
*/
        Distance = (Spinner) findViewById(R.id.txtDistance);
        Distance.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<String>(RequestDetails.this,
                R.layout.spinner_item, distanceArray);
        // Drop down layout style - list view with radio button
        distanceAdapter
                .setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        Distance.setAdapter(distanceAdapter);


        Payment = (Spinner) findViewById(R.id.txtPayment);
        Payment.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(RequestDetails.this,
                R.layout.spinner_item, paymentArray);
        // Drop down layout style - list view with radio button
        paymentAdapter
                .setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        Payment.setAdapter(paymentAdapter);





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
            case R.id.txtDistance:
                // do stuffs with you spinner 2
                distance = Distance.getItemAtPosition(pos).toString();

                break;
            case R.id.txtPayment:
                // do stuffs with you spinner 2
                payment = Payment.getItemAtPosition(pos).toString();

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
        AlertDialog.Builder builder1 = new AlertDialog.Builder(RequestDetails.this,R.style.MyDialogTheme);
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

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData) {
            if (resultCode == Constants.SUCCESS_RESULT) {
                final Address address = resultData.getParcelable(Constants.RESULT_ADDRESS);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        infoText.setVisibility(View.VISIBLE);
                        infoText.setText("Latitude: " + address.getLatitude() + "\n" +
                                "Longitude: " + address.getLongitude() + "\n" +
                                "Address: " + resultData.getString(Constants.RESULT_DATA_KEY));
                    }
                });
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        infoText.setVisibility(View.VISIBLE);
                        infoText.setText(resultData.getString(Constants.RESULT_DATA_KEY));
                    }
                });
            }
        }
    }
}
