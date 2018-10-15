package com.example.android.restuarantfinder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cart );

        LocalBroadcastManager.getInstance( this ).registerReceiver( mMessageReceiver,new IntentFilter( "message" ) );

        dataBaseAccess databaseAccess = dataBaseAccess.getInstance( getApplicationContext() );
        databaseAccess.open();

//        Intent intent = getIntent();
//        String buttonText = intent.getStringExtra("itemType");
//
        ArrayList<allMenuMao> allmenumao = databaseAccess.getAllMenuMao();
        databaseAccess.close();

        recyclerView = findViewById( R.id.recycler_cart );
        recyclerView.setHasFixedSize( true ); //every item in the recycler view will have fixed size
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        adapter = new ItemListAdapter( allmenumao,this );

        recyclerView.setAdapter( adapter ); //sets adapter to recycler view

        Button submitOrder = (Button) findViewById( R.id.submit_order );
        submitOrder.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( CartActivity.this,BillPayment.class );
                intent.putExtra("currentTotal", currentTotal);
                startActivity( intent );
            }
        } );

        TextView totalPrice = (TextView) findViewById( R.id.total );
    }

    int currentTotal = 0;

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context,Intent intent) {

            //intent = getIntent();
            //ArrayList<OrderList> orderList = new ArrayList<>();

            int price = intent.getIntExtra( "price",0 );
            int currentValInt = intent.getIntExtra( "currentValInt",0 );
            String name = intent.getStringExtra("name");
            boolean checked = intent.getBooleanExtra( "checked", false);
            boolean checkInc = intent.getBooleanExtra("checkInc", false);
            boolean checkDec = intent.getBooleanExtra("checkDec", false);
            boolean deselect = intent.getBooleanExtra("deselect", false);
            int pos = intent.getIntExtra("pos", 0);
            //String buttonDec = intent.getStringExtra("buttonDec");

            if (checked) {
                currentTotal += price;
                ItemListAdapter.menuMaos.get(pos).setTotalPrice(currentValInt*ItemListAdapter.menuMaos.get(pos).getItemPrice());
                ItemListAdapter.menuMaos.get(pos).setQuantity(currentValInt);
            }

            if(checkInc){
                currentTotal += price;
                ItemListAdapter.menuMaos.get(pos).setTotalPrice(currentValInt*ItemListAdapter.menuMaos.get(pos).getItemPrice());
                ItemListAdapter.menuMaos.get(pos).setQuantity(currentValInt);
            }

            if(checkDec){
                if(currentValInt>1 && currentTotal>0){
                    currentTotal -= price;
                    ItemListAdapter.menuMaos.get(pos).setTotalPrice((currentValInt-1)*ItemListAdapter.menuMaos.get(pos).getItemPrice());
                    ItemListAdapter.menuMaos.get(pos).setQuantity(currentValInt-1);
                }
            }

            if(deselect){
                if(currentTotal>0){
                    currentTotal -= currentValInt*price;
                    ItemListAdapter.menuMaos.get(pos).setTotalPrice(currentValInt*ItemListAdapter.menuMaos.get(pos).getItemPrice());
                    ItemListAdapter.menuMaos.get(pos).setQuantity(currentValInt);
                }
            }

                TextView totalPrice = (TextView) findViewById( R.id.total );
                totalPrice.setText( "" + currentTotal + " Rs." );

        }
    };
}
