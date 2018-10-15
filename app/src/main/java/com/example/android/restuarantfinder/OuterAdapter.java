package com.example.android.restuarantfinder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OuterAdapter extends RecyclerView.Adapter<OuterAdapter.Viewholder> {
    Context context;
    RecyclerView e1;
    OuterAdapter(RecyclerView e1)
    {
        this.e1=e1;
    }
    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public OuterAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manager_rowbill, parent, false);
        return new OuterAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OuterAdapter.Viewholder holder, int position) {
        holder.obj=e1;
        holder.name.setText("tea villa");
    }



    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView name;
        RecyclerView obj;
        public Viewholder(View v)
        {
            super(v);
            name=itemView.findViewById(R.id.manager_username);
            obj=itemView.findViewById(R.id.card_view);
        }
    }
}
