package com.example.android.restuarantfinder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Vrindavan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_vrindavan );

        dataBaseAccess databaseAccess = dataBaseAccess.getInstance(getApplicationContext());

        databaseAccess.open();


        TextView restName = (TextView)findViewById(R.id.vrindavan);
        TextView restAdd = (TextView)findViewById(R.id.vrindavanAdd);

        Intent intent = getIntent();
        String name = intent.getStringExtra("restName");

        String rest_name = databaseAccess.getRestName(name);
        databaseAccess.close();

        restName.setText(rest_name);

        Button placeOrderButton = (Button) findViewById(R.id.placeOrderButton);
        placeOrderButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        } );
    }
}


