package com.example.android.restuarantfinder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import com.xwray.groupie.GroupAdapter;

//import static com.example.android.restuarantfinder.R.string.reviewname;

public class Mao extends AppCompatActivity {

    private static final String TAG = "Mao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mao);

        //create instance of database access class
        dataBaseAccess databaseAccess = dataBaseAccess.getInstance(getApplicationContext());

        databaseAccess.open();

        TextView restName = (TextView) findViewById(R.id.mao);
        TextView restAdd = (TextView) findViewById(R.id.maoAdd);

        Intent intent = getIntent();
        //int pos = intent.getIntExtra("position", -1);
        String name = intent.getStringExtra("restName");


        String rest_name = databaseAccess.getRestName(name);
        databaseAccess.close();

        restName.setText(rest_name);

        final Button startersButton = (Button) findViewById(R.id.starter);
        startersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mao.this, menuMaoStarter.class);
                String buttonText = ((Button) view).getText().toString();
                intent.putExtra("itemType", buttonText);
                startActivity(intent);
            }
        });

        final Button drinksButton = (Button) findViewById(R.id.drinks);
        drinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mao.this, menuMaoDrinks.class);
                String buttonText = ((Button) view).getText().toString();
                intent.putExtra("itemType", buttonText);
                startActivity(intent);
            }
        });


        Button placeOrderButton = (Button) findViewById(R.id.placeOrderButton);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Mao.this, CartActivity.class);

                startActivity(intent);
            }
        });

        Button addreviewbutton = (Button) findViewById(R.id.addreviewbutton);
        addreviewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Mao.this, Addreview.class);
                myintent.putExtra("tag", TAG);
                startActivity(myintent);
            }
        });

        GroupAdapter adapter = new GroupAdapter<ViewHolder>();
        if (adapter != null) {
            RecyclerView review_recyclerview = findViewById(R.id.review_recyclerview);
            review_recyclerview.setAdapter(adapter);
            review_recyclerview.setAdapter(adapter);
            fetchList(review_recyclerview);
        }
    }

    private void fetchList(final RecyclerView recyclerView) {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("posts/" + TAG);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            GroupAdapter section1 = new GroupAdapter<ViewHolder>();
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    final String key = dataSnapshot1.getKey();



                    DatabaseReference dataname =databaseReference.getParent().getParent().child("posts").child(TAG).child(key);
                    dataname.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot11) {
                            for (DataSnapshot dataSnapshot2 : dataSnapshot11.getChildren())
                            {
                                final UserPost user1 = dataSnapshot2.getValue(UserPost.class);
                                if(user1!=null)
                                {
                                    Log.d("helloo s this good",""+user1.review);
                                }
                                DatabaseReference databaseReference2= FirebaseDatabase.getInstance().getReference(key);
                                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        UserInformation user2= dataSnapshot.getValue(UserInformation.class);
                                        Log.d("helloo s this good",""+user2.name);
                                        ListData obj1 = new ListData(user2.name,user1.review);
                                        section1.add(new UserItem2(obj1));

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                recyclerView.setAdapter(section1);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}

/* if (key != null) {

     DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference(key);
     databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
         String new_review;

         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                 String obj = dataSnapshot1.getValue(String.class);
                 String name=obj;
                 Log.d("Hi","Insideeee hi how are you" +name);
             }

             Log.d("Hi","Insideeee" +dataSnapshot);
             UserPost user = dataSnapshot.getValue(UserPost.class);
             new_review=user.review;


                //
                //




         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }


     });


 }



}

}

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {

}
});
}



}*/
class ListData
{
    public String name;
    public String reviewbar;
    public String abcd;

    ListData(String name,String reviewbar)
    {
        this.name=name;


        this.reviewbar=reviewbar;
    }

    ListData()
    {

    }
}
class UserItem2 extends Item<ViewHolder>
{
    public  ListData user;
    UserItem2(ListData user)
    {
        this.user=user;



    }




    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        TextView user_name=viewHolder.itemView.findViewById(R.id.review_listname);

        user_name.setText(user.name);
        TextView review_listreview=(TextView)viewHolder.itemView.findViewById(R.id.review_listreview);
        review_listreview.setText(user.reviewbar);
    }

    @Override
    public int getLayout() {
        return R.layout.review_layout;
    }
}