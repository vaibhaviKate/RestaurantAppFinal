package com.example.android.restuarantfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditMenuItem extends AppCompatActivity {
    Button deleteButton,editButton;
    EditText itemName,itemPrice,itemNameForEdit;
    dataBaseAccess db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu_item);
        db=dataBaseAccess.getInstance(getApplicationContext());
        db.openForWriting();
        deleteButton=(Button)findViewById(R.id.deleteButton);
        editButton=(Button)findViewById(R.id.editItemButton);
        itemName=(EditText)findViewById(R.id.itemName);
        itemPrice=(EditText)findViewById(R.id.itemPriceForEdit);
        itemNameForEdit=(EditText)findViewById(R.id.itemNameForEdit);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteItem(itemName.getText().toString());
                Toast.makeText(getApplicationContext(),"MENU ITEM DELETED", Toast.LENGTH_SHORT).show();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateItem(Integer.parseInt(itemPrice.getText().toString()),itemNameForEdit.getText().toString());
                Toast.makeText(getApplicationContext(),"MENU ITEM UPDATED", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
