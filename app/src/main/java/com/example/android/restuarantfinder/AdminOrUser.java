package com.example.android.restuarantfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminOrUser extends Activity {
    Button user,admin;
    Button restmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_or_user);
        user=(Button)findViewById(R.id.user);
        admin=(Button)findViewById(R.id.admin);
        restmanager=(Button)findViewById(R.id.restaurantmanager);
        restmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),RestaurantManagerLogin.class));

            }
        });
        user.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                        startActivity(new Intent(getApplicationContext(),StartUpActivity.class));
                                    }
                                }
        );
        admin.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         finish();
                                         startActivity(new Intent(getApplicationContext(),AdminLogin.class));
                                     }
                                 }
        );

    }

}
