package com.example.android.restuarantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminFunctionalitiesPage extends AppCompatActivity {
    Button button,button2,editButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_functionalities_page);
        button = (Button)findViewById(R.id.button);
        editButton =(Button)findViewById(R.id.editButton);
        button2=(Button)findViewById(R.id.button2);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editmenuitem = new Intent(getApplicationContext(), EditMenuItem.class);
                startActivity(editmenuitem);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addrestpage = new Intent(getApplicationContext(), AddRestaurantPage.class);
                startActivity(addrestpage);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addmenuitem = new Intent(getApplicationContext(), AddMenuItem.class);
                startActivity(addmenuitem);

            }
        });



    }
}
