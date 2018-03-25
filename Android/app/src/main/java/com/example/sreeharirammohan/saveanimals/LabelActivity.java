package com.example.sreeharirammohan.saveanimals;

import android.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LabelActivity extends AppCompatActivity {
    Spinner spinner;

    private DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
        spinner = (Spinner)findViewById(R.id.spinner);


        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Bald Eagle");
        list.add("Beaver");
        list.add("Blue Butterfly");
        list.add("California Condor");
        list.add("California Vole");
        list.add("Kit Fox");
        list.add("Peregrine Falcon");

        list.add("Red-Legged Frog");
        list.add("Sea Otter");

        list.add("Wolverine Animal");


        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button b = findViewById(R.id.Submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animal = spinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "You just saw an " + animal, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void pushAnimalDataToFirebase(String animalType, double longitude, double latitude, double currentTime) {
        Log.v("Monkey", animalType);
        Log.v("Monkey", currentTime+"");

        firebaseRef.child(animalType).child("Location").child(doubleToString(currentTime)).setValue("" + latitude + "," + longitude);
    }


    public static String doubleToString(Double d) {
        if (d == null)
            return null;
        if (d.isNaN() || d.isInfinite())
            return d.toString();

        // Pre Java 8, a value of 0 would yield "0.0" below
        if (d.doubleValue() == 0)
            return "0";
        return new BigDecimal(d.toString()).stripTrailingZeros().toPlainString();
    }

    private double[] getLocation() {
        LocationManager locationManager = (LocationManager) this.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

        //You can still do this if you like, you might get lucky:
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        double longitude = 0;
        double latitude = 0;
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            Log.e("TAG", "GPS is on");
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Toast.makeText(this.getApplicationContext(), "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
        }
        else{
            //This is what you need:
            //locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
        }

        return new double[] {longitude, latitude};

    }

}
