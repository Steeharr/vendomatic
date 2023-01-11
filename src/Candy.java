package com.techelevator.view;

public class Candy extends Items{
    private String type;
    private int amount;

    // ------ Setters and getters ------
    public Candy(String id, String name, double price, String type, int pos) {
        super(id, name, price, pos);
        this.type = type;
        this.amount = 5;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // ------ End of Sets and gets ------

    @Override
    public String printMsg() {
        return "Munch Munch, Yum!";
    } //Overrides the Item printMsg with its own.


}
