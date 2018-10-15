package com.example.android.restuarantfinder;
import android.util.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.view.View;
import android.widget.*;
import android.content.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private static final int ERROR_DIALOG_REQUEST=9001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void init(View v)
    {

        if(isServicesOk())
        {

                    Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(intent);

        }

    }
    public boolean isServicesOk()
    {
        Log.d(TAG,"checking services");
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available== ConnectionResult.SUCCESS)
        {
            Log.d(TAG,"Everything fine");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available))
        {
            Log.d(TAG,"just smal error");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOG_REQUEST);
                    dialog.show();
        }
        else
        {
            Toast.makeText(this,"Cant proced",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
