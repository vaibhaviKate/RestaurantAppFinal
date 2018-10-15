package com.example.android.restuarantfinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BillPayment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bill_payment );

        final ArrayList<allMenuMao> list = new ArrayList<>();

        int i;
        for(i=0; i<ItemListAdapter.menuMaos.size(); i++){
            if(ItemListAdapter.menuMaos.get(i).getSelected()){
                list.add(new allMenuMao(ItemListAdapter.menuMaos.get(i).getItemName(), ItemListAdapter.menuMaos.get(i).getItemPrice(),
                        ItemListAdapter.menuMaos.get(i).getQuantity(), ItemListAdapter.menuMaos.get(i).getTotalPrice()));

            }
        }

        Intent intent = getIntent();
        int currentTotal = intent.getIntExtra("currentTotal", 0);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); //every item in the recycler view will have fixed size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SelectedItemListAdapter(list, this);

        recyclerView.setAdapter(adapter); //sets adapter to recycler view

        TextView totalprice = (TextView) findViewById(R.id.total);
        totalprice.setText("Total Bill "+ currentTotal);

        FirebaseAuth user = FirebaseAuth.getInstance();
        FirebaseUser userid = user.getCurrentUser();
        String u = userid.getUid();
        final EditText editAddress=(EditText)findViewById(R.id.editAddress);
        DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference(u);
        mDatabase1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInformation editAddress1=dataSnapshot.getValue(UserInformation.class);
                String editAddress2=editAddress1.address;
                //String editAddress2=editAddress1.toString();
                editAddress.setText(editAddress2);
                //Log.d("Isthisright"," " +editAddress);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button ProceedToPay=(Button)findViewById(R.id.ProceedToPay);
        ProceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth user = FirebaseAuth.getInstance();
                FirebaseUser userid = user.getCurrentUser();
                String u = userid.getUid();

                //UserPost userpost = new UserPost(name);

                DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference("/manager/"+u).push();
                for(int i=0; i<ItemListAdapter.menuMaos.size(); i++){
                    if(ItemListAdapter.menuMaos.get(i).getSelected()){
                        String itemName=ItemListAdapter.menuMaos.get(i).getItemName();
                        int itemPrice=ItemListAdapter.menuMaos.get(i).getItemPrice();
                        int quantity=ItemListAdapter.menuMaos.get(i).getQuantity();
                        int totalprice=ItemListAdapter.menuMaos.get(i).getTotalPrice();
                        OrderList obj= new OrderList(itemName,itemPrice,quantity,totalprice);
                        mDatabase1.push().setValue(obj);

                    }
                }















                Toast.makeText(getApplicationContext(),"Your order has been sent for processing", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

