package com.example.android.restuarantfinder;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class manager_rowbill extends AppCompatActivity {
    private RecyclerView section1;
    private RecyclerView major_section;
    private  RecyclerView.Adapter adapter;
    private  RecyclerView.Adapter major_adapter;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_rowbill);

        section1 =findViewById(R.id.card_view);
        //major_section =findViewById(R.id.vieworders_recyclerview);
        //section1.setLayoutManager(new LinearLayoutManager(this));
        //Intent m=getIntent();
        //major_section.setLayoutManager(new LinearLayoutManager((Context)m.getSerializableExtra("yay")));
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("manager/");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {

            //ArrayList<UserList> list=new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("HELOOOOONEW","hi");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {

                    final String key=dataSnapshot1.getKey();
                    DatabaseReference dataname1 =databaseReference.getParent().child(key);
                    dataname1.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            UserInformation user1= dataSnapshot.getValue(UserInformation.class);
                            final String name=user1.name;
                            DatabaseReference dataname =databaseReference.getParent().child("manager").child(key);
                            dataname.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                    {
                                        final ArrayList<UserList> list=new ArrayList<>();
                                        String key1=dataSnapshot1.getKey();

                                        DatabaseReference dataname1 =databaseReference.getParent().child("manager").child(key).child(key1);

                                        dataname1.addListenerForSingleValueEvent(new ValueEventListener() {


                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                list.clear();
                                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                    Log.d("WHta",""+dataSnapshot1.getValue());
                                                    OrderList user2 = dataSnapshot1.getValue(OrderList.class);
                                                    Log.d("INSIDE", " " + dataSnapshot1);
                                                    String itemName = user2.getItemName();
                                                    int itemPrice = user2.getItemPrice();
                                                    int quantity = user2.getQuantity();
                                                    int totalprice = user2.getTotalPrice();
                                                    UserList obj = new UserList(itemName, itemPrice, quantity, totalprice);
                                                    list.add(obj);




                                                    //Log.d("HE"," "+list.toString());
                                                }
                                                Log.d("ARRAYLIST","" + list.size());
                                                //majorlist.add(list);

                                               adapter = new UserList2(list);
                                               section1.setAdapter(adapter);


                                                //sendList(majorlist);


                                                // section1.setAdapter(new displayUserList(list));

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }

                                        });

                                        //Intent myintent=new Intent(getApplicationContext(),ViewOrders.class);
                                        //myintent.putExtra("LIST",majorlist);
                                       // major_adapter= new OuterAdapter(adapter,new manager_rowbill());
                                        //major_section.setAdapter(major_adapter);

                                        Log.d("What1","end");


                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


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

       // Log.d("MJORSIZEEEE",""+majorlist.size());
        //recyclerView.setAdapter(adapter);

    }


}
class UserList
{
    String itemname;
    int itemprice;
    int quantity;
    int  totalprice;

    UserList(String itemname,int itemprice,int quantity,int totalprice)
    {
        this.totalprice=totalprice;
        this.quantity=quantity;
        this.itemname=itemname;
        this.itemprice=itemprice;
    }
}