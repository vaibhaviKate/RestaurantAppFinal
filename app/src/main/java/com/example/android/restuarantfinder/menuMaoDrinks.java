package com.example.android.restuarantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class menuMaoDrinks extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu_mao_drinks );

        dataBaseAccess databaseAccess = dataBaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        Intent intent = getIntent();
        String buttonText = intent.getStringExtra("itemType");
        ArrayList<allMenuMao> allmenumao = (ArrayList<allMenuMao>) databaseAccess.getStartersMao(buttonText);
        databaseAccess.close();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //every item in the recycler view will have fixed size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        adapter = new RecyclerViewAdapter(allmenumao, this);

        recyclerView.setAdapter(adapter); //sets adapter to recycler view
    }
}
