package com.example.android.restuarantfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminPassword extends AppCompatActivity {
    EditText password;
    Button button;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_password);
        password=(EditText)findViewById(R.id.editTextPassword);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals("password"))
                {
                    Toast.makeText(getApplicationContext(),"Password correct",Toast.LENGTH_SHORT).show();
                    Intent functionalities = new Intent(getApplicationContext(), AdminFunctionalitiesPage.class);
                    startActivity(functionalities);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect password",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
