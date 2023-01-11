package com.techelevator;

import com.techelevator.view.Log;
import com.techelevator.view.TextColors;

import java.util.Scanner;

public class Money {

    private static double totalMoney = 0.0;
    private static double remainingMoney = 0.0;
    final double FIVE_DOLLAR_BILL = 5.00;
    final double ONE_DOLLAR_BILL = 1.00;

    final static int QUARTER = 25;
    final static int DIME = 10;
    final static int NICKEL = 5;


    public Money() {
    }

    public static double getRemainingMoney() {
        return remainingMoney;
    }

    public static void setRemainingMoney(double remainingMoney) {
        Money.remainingMoney = remainingMoney;
    }

    public double getFIVE_DOLLAR_BILL() {
        return FIVE_DOLLAR_BILL;
    }

    public double getONE_DOLLAR_BILL() {
        return ONE_DOLLAR_BILL;
    }

    public double getQUARTER() {
        return QUARTER;
    }

    public double getDIME() {
        return DIME;
    }

    public double getNICKEL() {
        return NICKEL;
    }

    public static double getTotalMoney() {
        return totalMoney;
    }

    public static void setTotalMoney(double totalMoney) {
        Money.totalMoney = totalMoney;
    }

    public static void moneyInserted() {
        System.out.println("------------------------------------");
        System.out.println(TextColors.getCyantxt() + "         Vendo-Matic 800" + TextColors.getResetTxtColor());
        System.out.println("------------------------------------");
        if(Money.getTotalMoney() == 0) {
            System.out.printf("\n" + "Current Money Provided: " + TextColors.getRedTxt() + "$ %.2f" + TextColors.getResetTxtColor() + "\n", Money.getTotalMoney());
        }else {
            System.out.printf("\n" + "Current Money Provided: " + TextColors.getGreenTxt() + "$ %.2f" + TextColors.getResetTxtColor() + "\n", Money.getTotalMoney());
        }
        System.out.println("\n");
        Scanner input = new Scanner(System.in); //created scanner to capture system input.
        System.out.print("Please Insert Money amount: $"); //prints out instruction for user.
        double addedMoney = input.nextDouble(); // double totalMoney is equal to the nextLine of system input.
        totalMoney += addedMoney;
        input.nextLine(); // adds a newline char to advance the system input.
        Log.log("FEED MONEY:", addedMoney,totalMoney ); //logs the action
    }

    public static String changeDue(double totalMoneyReceived) {
        final int[] coins = {NICKEL, DIME, QUARTER};  //Constant int[] called coins to hold the values of the coins
        int change = (int) ((totalMoneyReceived) * 100); //calculates the amount of change and casts the result of it to an int
        int[] coinsReturned = new int[3]; // creates a new int[] called coinsReturned and sets it to hold 3 values.
        //double changeOwed = 0.0;
        //changeOwed = totalMoneyReceived - totalAmountOwed;
        for (int i = 2; i >= 0; i--) {  //for i starts at pos 2, i is greater than or equal to 0, decrement i by 1.
            coinsReturned[i] = change / coins[i]; //coinsReturned[] position i = change divided by the value of coins array position i.
            change %= coins[i]; //change is mod or equal to coins array position i
        }
        //return coinsReturned;
        return ("\n"+"Your change is :" + "\n" + TextColors.getGreenTxt() +"Quarters: " + TextColors.getResetTxtColor() + coinsReturned[2] + "\n" + TextColors.getGreenTxt() +"Dimes: " + TextColors.getResetTxtColor() + coinsReturned [1] + "\n" + TextColors.getGreenTxt() +"Nickels: " +  TextColors.getResetTxtColor() + coinsReturned[0] );
    }
}

