package com.example.jason.memi;

/**
 * Created by jason on 2017/12/18.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.memi.activity.ConfirmRequest;
import com.example.jason.memi.activity.Request;
import com.example.jason.memi.activity.RequestDetails;

public class RequestAllDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AddressResultReceiver mResultReceiver;

    EditText addressEdit,description;
    ProgressBar progressBar;
    //TextView distanceText;
    CheckBox checkBox;

    boolean fetchAddress;
    int fetchType = Constants.USE_ADDRESS_LOCATION;
    public static Spinner Receipient;
    public static Spinner Weight;

    public static float distance;

    private static final String TAG = "MAIN_ACTIVITY";

    public static String weight,desc,collectadd;
    public static String receipient;
    String recipientArray [] = {"Select Recipient","Sibonubuhle","Novuyo","Musa"};
    String weightArray [] = {"Select Weight","20kg-50kg","51kg-80kg","81kg-100kg",">100kg"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






        //longitudeEdit = (EditText) findViewById(R.id.longitudeEdit);
        //latitudeEdit = (EditText) findViewById(R.id.latitudeEdit);
        description = (EditText) findViewById(R.id.decsription);
        addressEdit = (EditText) findViewById(R.id.addressEdit);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //distanceText = (TextView) findViewById(R.id.txtEstimatedDistance);
        checkBox = (CheckBox) findViewById(R.id.checkbox);



        mResultReceiver = new AddressResultReceiver(null);

        Receipient = (Spinner) findViewById(R.id.txtRecipient);
        // Creating adapter for spinner
        ArrayAdapter<String> recipientAdapter = new ArrayAdapter<String>(RequestAllDetails.this,
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
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(RequestAllDetails.this,
                R.layout.spinner_item, weightArray);
        // Drop down layout style - list view with radio button
        weightAdapter
                .setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        Weight.setAdapter(weightAdapter);



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


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioAddress:
                if (checked) {
                    fetchAddress = false;
                    fetchType = Constants.USE_ADDRESS_NAME;
                   // longitudeEdit.setEnabled(false);
                    //latitudeEdit.setEnabled(false);
                    addressEdit.setEnabled(true);
                    addressEdit.requestFocus();
                }
                break;
            case R.id.radioLocation:
                if (checked) {
                    fetchAddress = true;
                    fetchType = Constants.USE_ADDRESS_LOCATION;
                    //latitudeEdit.setEnabled(true);
                    //latitudeEdit.requestFocus();
                    //longitudeEdit.setEnabled(true);
                    addressEdit.setEnabled(false);
                }
                break;
        }
    }

    public void onButtonClicked(View view) {

        desc= description.getText().toString();
        collectadd =addressEdit.getText().toString();

        Intent intent = new Intent(this, GeocodeAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.FETCH_TYPE_EXTRA, fetchType);
        if(fetchType == Constants.USE_ADDRESS_NAME) {
            if(addressEdit.getText().length() == 0) {
                Toast.makeText(this, "Please enter an address name", Toast.LENGTH_LONG).show();
                return;
            }
            intent.putExtra(Constants.LOCATION_NAME_DATA_EXTRA, addressEdit.getText().toString());
        }
        else {


            Intent myIntent = new Intent(RequestAllDetails.this,
                    ConfirmRequest.class);
            startActivity(myIntent);

          /*  if(latitudeEdit.getText().length() == 0 || longitudeEdit.getText().length() == 0) {
                Toast.makeText(this,
                        "Please enter both latitude and longitude",
                        Toast.LENGTH_LONG).show();
                return;
            }

            intent.putExtra(Constants.LOCATION_LATITUDE_DATA_EXTRA,
                    Double.parseDouble(latitudeEdit.getText().toString()));
            intent.putExtra(Constants.LOCATION_LONGITUDE_DATA_EXTRA,
                    Double.parseDouble(longitudeEdit.getText().toString()));
                   */
        }
        //infoText.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Log.e(TAG, "Starting Service");
        startService(intent);
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
                       // infoText.setVisibility(View.VISIBLE);
                        //infoText.setText("Latitude: " + address.getLatitude() + "\n" +
                        //        "Longitude: " + address.getLongitude() + "\n" +
                        //        "Address: " + resultData.getString(Constants.RESULT_DATA_KEY));
                        System.out.println("Latitude: " + address.getLatitude() + "\n" +
                                "Longitude: " + address.getLongitude() + "\n" +
                                "Address: " + resultData.getString(Constants.RESULT_DATA_KEY));



                        Location locationA = new Location("point A");

                        locationA.setLatitude(-26.185679400);
                        locationA.setLongitude(28.054795800);

                        Location locationB = new Location("point B");

                        locationB.setLatitude(address.getLatitude());
                        locationB.setLongitude(address.getLongitude());

                        distance = locationA.distanceTo(locationB);


                        //distanceText.setText(String.valueOf(distance)+"m");

                        System.out.println("DISTANCE" + distance + "m");

                        Intent myIntent = new Intent(RequestAllDetails.this,
                                ConfirmRequest.class);
                        startActivity(myIntent);

                    }
                });
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                       // infoText.setVisibility(View.VISIBLE);
                        //infoText.setText(resultData.getString(Constants.RESULT_DATA_KEY));
                        System.out.println(resultData.getString(Constants.RESULT_DATA_KEY));
                    }
                });
            }
        }
    }



    /*
    Location location1=new Location("locationA");
            near_locations.setLatitude(17.372102);
            near_locations.setLongitude(78.484196);
Location location2=new Location("locationA");
            near_locations.setLatitude(17.375775);
            near_locations.setLongitude(78.469218);
double distance=selected_location.distanceTo(near_locations);
     */

}
