package com.techelevator.view;

public class Drink extends Items {
    private String type;
    private int amount;

    //Drink subclass Constructor
    public Drink(String id, String name, double price, String type, int pos) {
        super(id, name, price, pos);
        this.type = type;
        this.amount = 5;
    }

    // ------ setters and getters ------
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    // ------ end of sets and gets ------
    @Override
    public String printMsg(){
        return "Glug Glug, Yum!";
    } //overrides the Item printMsg method with its own version
}