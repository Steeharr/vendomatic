package com.techelevator.view;

public class Gum extends Items {


    private String type;
    private int amount;

    // Subclass constructor
    public Gum(String id, String name, double price, String type, int pos) {
        super(id, name, price, pos);
        this.type = type;
        this.amount = 5;
    }

    // ------- Setters and Getters ------
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

    // ------ end of sets and gets

    @Override
    public String printMsg() {
        return "Chew Chew, Yum!";
    } //overrides the Parent Item printMsg with its own message.

}
