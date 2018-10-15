package com.example.android.restuarantfinder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    static List<allMenuMao> menuMaos;
    private Context context;
    private static int count = 0;

    public ItemListAdapter(List<allMenuMao> menuMaos, Context context) {
        this.menuMaos = menuMaos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {
        //allMenuMao allmenumao = menuMaos.get(position);

        holder.itemName.setText("" + menuMaos.get(position).getItemName());
        holder.itemPrice.setText("" + menuMaos.get(position).getItemPrice());

        holder.checkBox.setChecked(menuMaos.get(position).getSelected());
        holder.checkBox.setTag(position);

        holder.checkBox.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.checkBox.getTag();
                if(menuMaos.get(pos).getSelected()){
                    menuMaos.get(pos).setSelected(false);

                    String currentVal = holder.count.getText().toString();
                    int currentValInt = Integer.parseInt(currentVal);
                    int val=0;
                    if(currentValInt>0){
                        val = 0;
                    }
                    holder.count.setText(""+val);

                    boolean deselect = true;
                    int price = menuMaos.get(pos).getItemPrice();
                    String name = menuMaos.get(pos).getItemName();
                    Intent intent = new Intent("message");
                    intent.putExtra("price", price);
                    intent.putExtra("currentValInt", currentValInt);
                    intent.putExtra("deselect", deselect);
                    intent.putExtra("name", name);
                    intent.putExtra("pos", pos);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


                }else{
                    menuMaos.get(pos).setSelected(true);
                    //String currentVal = holder.count.getText().toString();
                    int currentValInt = 1;

                    holder.count.setText(""+currentValInt);

                    boolean checked = menuMaos.get(pos).getSelected();

                    int price = menuMaos.get(pos).getItemPrice();
                    String name = menuMaos.get(pos).getItemName();
                    Intent intent = new Intent("message");
                    intent.putExtra("price", price);
                    intent.putExtra("currentValInt", currentValInt);
                    intent.putExtra("checked", checked);
                    intent.putExtra("name", name);
                    intent.putExtra("pos", pos);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


                }

            }
        } );

        holder.buttonInc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quant = 0;
                Integer pos = (Integer) holder.checkBox.getTag();

                if(menuMaos.get(pos).getSelected()){
                    String currentVal = holder.count.getText().toString();
                    int currentValInt = Integer.parseInt(currentVal);
                    currentValInt++;

                    holder.count.setText(""+currentValInt);

                    boolean checkInc = true;

                    int price = menuMaos.get(pos).getItemPrice();
                    String name = menuMaos.get(pos).getItemName();

                    Intent intent = new Intent("message");
                    intent.putExtra("price", price);
                    intent.putExtra("currentValInt", currentValInt);
                    intent.putExtra("checkInc", checkInc);
                    intent.putExtra("name", name);
                    intent.putExtra("pos", pos);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    checkInc = false;
                }

            }
        } );

        holder.buttonDec.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkDec;
                Integer pos = (Integer) holder.checkBox.getTag();
                if(menuMaos.get(pos).getSelected()){
                    String currentVal = holder.count.getText().toString();
                    int currentValInt = Integer.parseInt(currentVal);

                    checkDec = true;


                    int price = menuMaos.get(pos).getItemPrice();
                    String name = menuMaos.get(pos).getItemName();
                    Intent intent = new Intent("message");
                    intent.putExtra("price", price);
                    intent.putExtra("currentValInt", currentValInt);
                    intent.putExtra("name", name);
                    intent.putExtra("checkDec", checkDec);
                    intent.putExtra("pos",  pos);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    if(currentValInt>1){
                        currentValInt--;
                    }

                    holder.count.setText(""+currentValInt);
                    checkDec = false;

                }

            }
        } );
    }

    @Override
    public int getItemCount() {
        return menuMaos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView itemName;
        public TextView itemPrice;
        public Button buttonDec;
        public Button buttonInc;
        public CheckBox checkBox;
        public TextView count;
        public TextView total;

        public ViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.cartItemName);
            itemPrice = itemView.findViewById(R.id.cartItemPrice);
            buttonDec = itemView.findViewById(R.id.buttonDecrease);
            buttonInc = itemView.findViewById(R.id.buttonIncrease);
            checkBox = itemView.findViewById(R.id.cartCheckbox);
            count = itemView.findViewById(R.id.count);
            total = itemView.findViewById(R.id.total);
        }
    }

}
