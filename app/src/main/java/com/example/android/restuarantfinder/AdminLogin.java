package com.example.android.restuarantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText adminusername;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminusername=(EditText)findViewById(R.id.adminusername);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(adminusername.getText().toString().equals("Admin"))
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),AdminPassword.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect username",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
