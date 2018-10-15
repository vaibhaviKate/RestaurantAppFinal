package com.example.android.restuarantfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addreview extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreviewlayout);

        Button addreviewbutton = findViewById(R.id.postbutton);
        addreviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postToFirebaseDatabase();
            }
        });

    }

    private void postToFirebaseDatabase() {
        EditText review =(EditText)findViewById(R.id.editreview);
        String name=review.getText().toString();
        if (review == null) {
            return;
        }
        FirebaseAuth user = FirebaseAuth.getInstance();
        FirebaseUser userid = user.getCurrentUser();
        if (userid != null) {
            String u = userid.getUid();
            Log.d("Mainn", "username ka uid hai " +u);
            Intent myintent = getIntent();
            String TAG = myintent.getStringExtra("tag");
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("posts/"+TAG);
            UserPost userpost = new UserPost(name);
            ref.child(u).push().setValue(userpost);
            Toast.makeText(this,"Your Post has been posted successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
class UserPost
{
    public String review;
    public String uid;
    UserPost(String review)
    {
        this.review=review;

    }
    UserPost()
    {

    }
}
