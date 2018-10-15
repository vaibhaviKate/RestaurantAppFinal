package com.example.android.restuarantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RestaurantManagerLogin extends AppCompatActivity {
    Button button;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_manager_login);
        button=(Button)findViewById(R.id.button);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("manager") && password.getText().toString().equals("password"))
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),ViewOrders.class));
                }

            }
        });
    }
}
