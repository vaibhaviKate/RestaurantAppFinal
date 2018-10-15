package com.example.android.restuarantfinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<allMenuMao> menuMaos;
    private Context context;

    public RecyclerViewAdapter(List<allMenuMao> menuMaos, Context context) {
        this.menuMaos = menuMaos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        allMenuMao allmenumao = menuMaos.get(position);

        holder.itemName.setText("" + allmenumao.getItemName());
        holder.itemPrice.setText("" + allmenumao.getItemPrice());
    }

    @Override
    public int getItemCount() {
        return menuMaos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView itemName;
        public TextView itemPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.manager_itemname);
            itemPrice = (TextView) itemView.findViewById(R.id.manager_itemprice);
        }
    }
}

