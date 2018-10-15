package com.example.android.restuarantfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMenuItem extends AppCompatActivity {
    EditText x,y,z;
    dataBaseAccess db;
    Button insertButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);
        db=dataBaseAccess.getInstance(getApplicationContext());
        db.openForWriting();
        x=(EditText)findViewById(R.id.itemType);
        y=(EditText)findViewById(R.id.itemName);
        z=(EditText)findViewById(R.id.itemPrice);
        insertButton=(Button)findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemType=x.getText().toString();
                String itemName=y.getText().toString();
                int itemPrice=Integer.parseInt(z.getText().toString());
                db.insertItem(itemType,itemName,itemPrice);
                Toast.makeText(getApplicationContext(),"MENU ITEM INSERTED", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
