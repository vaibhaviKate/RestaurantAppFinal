package com.example.android.restuarantfinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserList2 extends RecyclerView.Adapter<UserList2.ViewHolder> {


    static ArrayList<UserList> list;
    private Context context;
    private static int count = 0;

    public UserList2(ArrayList<UserList> list) {
        this.list = list;
       // this.context = context;
    }

    @Override
    public UserList2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manager_row, parent, false);
        return new UserList2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserList2.ViewHolder holder, int position) {
        holder.itemName.setText("" + list.get(position).itemname);
        holder.itemPrice.setText("" + list.get(position).itemprice);
        holder.itemQuant.setText("" + list.get(position).quantity);
        holder.itemTotalPrice.setText("" + list.get(position).totalprice);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView itemPrice;
        public TextView itemQuant;
        public TextView itemTotalPrice;


        public ViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.manager_itemname);
            itemPrice = itemView.findViewById(R.id.manager_Price);
            itemQuant = itemView.findViewById(R.id.manager_itemquant);
            itemTotalPrice = itemView.findViewById(R.id.manager_itemtotalprice);

        }
    }
}

