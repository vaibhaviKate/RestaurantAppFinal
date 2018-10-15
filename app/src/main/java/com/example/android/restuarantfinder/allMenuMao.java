package com.example.android.restuarantfinder;

public class allMenuMao {
    private String itemName;
    private int itemPrice;
    private boolean isSelected;
    private int quantity;
    private int totalPrice;

//    public allMenuMao(){
//        return;
//    }

    public allMenuMao(String itemName, int itemPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public allMenuMao(String itemName, int itemPrice, int quantity, int totalPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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
