package com.example.android.restuarantfinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SelectedItemListAdapter extends RecyclerView.Adapter<SelectedItemListAdapter.ViewHolder> {

    static List<allMenuMao> menuMaos;
    private Context context;
    private static int count = 0;

    public SelectedItemListAdapter(List<allMenuMao> menuMaos) {
        this.menuMaos = menuMaos;

    }



    public SelectedItemListAdapter(List<allMenuMao> menuMaos, Context context) {
        this.menuMaos = menuMaos;
        this.context = context;
    }

    public SelectedItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_billpayment, parent, false);
        return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemListAdapter.ViewHolder holder,int position) {

        holder.itemName.setText("" + menuMaos.get(position).getItemName());
        holder.itemPrice.setText("" + menuMaos.get(position).getItemPrice());
        holder.itemQuant.setText("" + menuMaos.get(position).getQuantity());
        holder.itemTotalPrice.setText(""+ menuMaos.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return menuMaos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView itemName;
        public TextView itemPrice;
        public TextView itemQuant;
        public TextView itemTotalPrice;


        public ViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.manager_itemname);
            itemPrice = itemView.findViewById(R.id.manager_itemprice);
            itemQuant = itemView.findViewById(R.id.manager_itemquant);
            itemTotalPrice = itemView.findViewById(R.id.itemtotalprice);

        }
    }
}
