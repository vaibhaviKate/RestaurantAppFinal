package com.example.android.restuarantfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRestaurantPage extends AppCompatActivity {
    Button button;
    EditText editText,locationEdit;
    dataBaseAccess db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant_page);
        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        locationEdit=(EditText)findViewById(R.id.locationEdit);
        db=dataBaseAccess.getInstance(getApplicationContext());
        db.openForWriting();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=editText.getText().toString();
                db.insert(x,locationEdit.getText().toString());
                Toast.makeText(getApplicationContext(),"INSERTED",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
