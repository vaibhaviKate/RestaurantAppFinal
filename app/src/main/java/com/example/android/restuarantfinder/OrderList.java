package com.example.android.restuarantfinder;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderList {

    private String itemName;
    private int itemPrice;
    private int quantity;
    private int totalPrice;
    private String user;

    public OrderList(String user)
    {
        this.user=user;
    }
    public OrderList()
    {
        getItemName();
        getItemPrice();
        getQuantity();
        getTotalPrice();
        getuser();
    }
    public OrderList(String itemName, int itemPrice, int quantity, int totalPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;


    }

    public String getuser() {
        return user;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
