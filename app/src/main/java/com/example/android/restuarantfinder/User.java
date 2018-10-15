package com.example.android.restuarantfinder;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class User extends RecyclerView.Adapter<User.ViewHolder> {

    private Context context;
    static ArrayList<String> name;
    ArrayList<ArrayList> list;
    ArrayList<String> emailid;
    User(ArrayList<String> name, ArrayList<ArrayList> list, ArrayList<String> emailid, Context context)
    {
        this.name=name;
        this.list=list;
        this.emailid = emailid;
        this.context = context;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,int position) {
       // TextView obj;
       // obj=holder.itemView.findViewById(R.id.manager_username);
        //TextView username=obj.findViewById(R.id.manager_username);
        holder.username.setText(name.get(position).toString());
        holder.email.setText(emailid.get(position).toString());
        RecyclerView section;
        RecyclerView.Adapter adapter;
        section=holder.itemView.findViewById(R.id.card_view);
        adapter=new UserList2(list.get(position));


        //Log.d("HIIII","yuhoo");
        section.setAdapter(adapter);

        holder.confirm.setTag(position);
        holder.confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.confirm.getTag();

                String user = holder.username.getText().toString();
                String email = holder.email.getText().toString();



                Log.d("User", "selected button is "+pos);
                Log.d("User", "selected user is "+user);
                Log.d("User", "selected email id is "+email);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData( Uri.parse("mailto:"));
                intent.setType("message/rfc822");
                //intent.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.putExtra(Intent.EXTRA_SUBJECT, "order confirmation");
                intent.putExtra(Intent.EXTRA_TEXT, "Your order has been confirmed");

                try {

                    context.startActivity(Intent.createChooser(intent, ""));


                } catch (android.content.ActivityNotFoundException ex) {

                    //Toast.makeText(User.this, "No email client installed.",Toast.LENGTH_LONG).show();

                }



            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public User.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manager_rowbill, parent, false);
        return new User.ViewHolder(view);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
       public RecyclerView obj;
       public Button confirm;
        public TextView email;


        public ViewHolder(View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.manager_username);
            confirm = itemView.findViewById(R.id.manager_button);
            email = itemView.findViewById(R.id.manager_emailid);
            //obj=itemView.findViewById(R.id.card_view);


        }
    }
}
