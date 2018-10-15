package com.example.android.restuarantfinder;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewOrders extends AppCompatActivity {

    private RecyclerView section1;
    private RecyclerView.Adapter adapter;
     RecyclerView major_section;
     RecyclerView.Adapter major_adapter;
     public Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        major_section=findViewById(R.id.vieworders_recyclerview);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("manager/");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {

            //ArrayList<UserList> list=new ArrayList<>();


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<ArrayList> majorlist=new ArrayList<ArrayList>();
                final ArrayList<String> name=new ArrayList<>();
                final ArrayList<String> emailid = new ArrayList<>();
                Log.d("HELOOOOONEW","hi");


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {

                    final String key=dataSnapshot1.getKey();
                    DatabaseReference dataname1 =databaseReference.getParent().child(key);
                    dataname1.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            UserInformation user1= dataSnapshot.getValue(UserInformation.class);
                            final String name1=user1.name;
                            final String emailid1 = user1.email;


                            DatabaseReference dataname =databaseReference.getParent().child("manager").child(key);
                            dataname.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                    {


                                        String key1=dataSnapshot1.getKey();

                                        DatabaseReference dataname1 =databaseReference.getParent().child("manager").child(key).child(key1);
                                        //final ArrayList<ArrayList> majorlist=new ArrayList<ArrayList>();
                                        dataname1.addListenerForSingleValueEvent(new ValueEventListener() {

                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                final ArrayList<UserList> list=new ArrayList<>();


                                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                                    Log.d("WHta",""+dataSnapshot1.getValue());
                                                    OrderList user2 = dataSnapshot1.getValue(OrderList.class);
                                                    Log.d("INSIDE", " " + dataSnapshot1);
                                                    String itemName = user2.getItemName();
                                                    int itemPrice = user2.getItemPrice();
                                                    int quantity = user2.getQuantity();
                                                    int totalprice = user2.getTotalPrice();


                                                    list.add(new UserList(itemName, itemPrice, quantity, totalprice));


                                                    //Log.d("ARRAYLIST","" + list.get(0).itemname);
                                                    //list.clear();

                                                    //Log.d("HE"," "+list.toString());
                                                }


                                                majorlist.add(list);
                                               // Log.d("HEYNAME",""+name);
                                                name.add(name1);
                                                emailid.add(emailid1);
                                                major_adapter=new User(name,majorlist, emailid, context);

                                                major_section.setAdapter(major_adapter);
                                                major_adapter.notifyDataSetChanged();
                                                Log.d("ARRAYLIST","" + name.size());

                                                //majorlist.add(list);
                                               // Log.d("MJORARRAYLIST","" + majorlist.size());
                                                //adapter = new UserList2(list);
                                                //section1.setAdapter(adapter);



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
    }
}