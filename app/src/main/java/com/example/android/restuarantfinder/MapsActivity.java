package com.example.android.restuarantfinder;

import com.google.android.gms.location.FusedLocationProviderClient;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.*;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.util.*;
import android.location.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
    }
    double latLngcurrent;
    double latLngcurrent2;
    public static NearByPlaces nearByPlaces;
    private FusedLocationProviderClient fusedclient;
    private GoogleMap mMap;
    private boolean flag = false;
    private static final int code = 1234;
    private static final String TAG = "MapsActivity";
    private static final String Fine_Location = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String Coarse_Location = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Button editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       // editText1=(EditText)findViewById(R.id.input_search);

        init();

    }
    private void init()
    {

        Log.d(TAG,"Helooo");
        getLocationPermission();
                  //  geoLocate();


               /* public boolean onClick(TextView textView, int i, KeyEvent keyEvent) {
                    if( i== EditorInfo.IME_ACTION_SEARCH || i==EditorInfo.IME_ACTION_DONE|| keyEvent.getAction()==KeyEvent.ACTION_DOWN
                            ||keyEvent.getAction()==KeyEvent.KEYCODE_ENTER)
                    {
                        Log.d(TAG,"Trying to locate");

                    }
                    return false;
                }
            });*/
            hideKeyBoard();
    }

    public void findRestuarant(View v)
    {

            try {
                Log.d(TAG, "Reached safely");
                StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                Log.d(TAG, "Reached safely AFTER LINK");
                stringBuilder.append("location="+latLngcurrent+"," + latLngcurrent2);
                Log.d(TAG, "Reached safely AFTER GPS");
                stringBuilder.append("&radius=" + 1000);
                Log.d(TAG, "Reached safely AFTER RADIUS");
               stringBuilder.append("&types=" + "restaurant");
                stringBuilder.append("&key="+ getResources().getString(R.string.google_aps_api));
                String url = stringBuilder.toString();
                Log.d(TAG,url);
                Log.d(TAG, "Reached safely AFTER string builder");
                Object datatransfer[] = new Object[2];
                datatransfer[0] = mMap;
                datatransfer[1] = url;
                 nearByPlaces = new NearByPlaces();
                nearByPlaces.execute(datatransfer);
                Log.d(TAG, "Reached safely AFTER string builder last");
            }
            catch(SecurityException e)
            {
                Log.d(TAG,e.getMessage());
            }
    }
    public void showlist(View v)
    {
        Intent intent=new Intent(MapsActivity.this,showlist.class);
        startActivity(intent);
    }

    private void hideKeyBoard()
    {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
  /*  private void geoLocate()
    {
        //String searchString=editText1.getText().toString();
        Geocoder geocoder=new Geocoder(MapsActivity.this);
        List<Address> list=new ArrayList<>();
        try
        {
            list=geocoder.getFromLocationName(searchString,5);
            Log.d(TAG,"Found california");
        }catch(IOException e)
        {
            Log.e(TAG,e.getMessage());
        }
        if(list.size()>0)
        {
            Address adress=list.get(0);
            Log.d(TAG,"a locationnn" + adress.toString());
            moveCamera(new LatLng(adress.getLatitude(),adress.getLongitude()),15f,adress.getAddressLine(0));
        }
    }*/
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Fine_Location) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Coarse_Location) == PackageManager.PERMISSION_GRANTED) {
                flag = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, code);
            }

        } else {
            ActivityCompat.requestPermissions(this, permissions, code);
        }

    }

    private void getDeviceLocation() {
        Log.d(TAG, "Getting location");
        fusedclient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (flag) {
                Task location = fusedclient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Found");
                            Location found = (Location) task.getResult();
                            Log.d(TAG," "+found.getLatitude());
                            moveCamera(new LatLng(found.getLatitude(), found.getLongitude()), 15f,"My Location");
                            latLngcurrent=found.getLatitude();

                            Log.d(TAG," "+latLngcurrent);
                            latLngcurrent2=found.getLongitude();
                            Log.d(TAG," " +latLngcurrent2);
                        } else {
                            Log.d(TAG, "Not found");
                            Toast.makeText(getApplicationContext(), "CANT FIND", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.d(TAG, "CANT FIND " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latlng, float zoom,String title) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,zoom));

        MarkerOptions options=new MarkerOptions()
                .position(latlng)
                .title(title);
            mMap.addMarker(options);
        hideKeyBoard();
    }

    public void onRequestPermissionsResult(int requestcode, String[] permissions, int[] grantresult) {
        flag = false;
        switch (requestcode) {
            case code: {
                if (grantresult.length > 0) {
                    for (int i = 0; i < grantresult.length; i++) {
                        if (grantresult[i] != PackageManager.PERMISSION_GRANTED) {
                            flag = false;
                            return;
                        }
                    }
                    flag = true;
                    initMap();
                }

            }
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Toast.makeText(getApplicationContext(), "Map is ready", Toast.LENGTH_SHORT).show();
                mMap = googleMap;
                if (flag) {
                    getDeviceLocation();
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                    //mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    //mMap.getUiSettings().setZoomControlsEnabled(true);
                    //init();
                }
            }
        });
    }
}
