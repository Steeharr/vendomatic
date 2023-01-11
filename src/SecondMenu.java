package com.techelevator.view;


import com.techelevator.Money;
import com.techelevator.VendingMachineCLI;

import java.util.*;

import static com.techelevator.VendingMachineCLI.*;

public class SecondMenu {

    private static final String MENU_OPTION_FEEDMONEY =  TextColors.getGreenTxt() + "Feed Money" + TextColors.getResetTxtColor(); //created a constant String for 1st option.
    private static final String MENU_OPTION_SELECT = TextColors.getYellowTxt() + "Select Product" + TextColors.getResetTxtColor();  // created a constant String for 2nd option.
    private static final String MENU_OPTION_FINISH = TextColors.getRedTxt() +"Finish Transaction"+ TextColors.getResetTxtColor();// created a constant String for 3rd option.

    private static final String[] MENU_OPTIONS = {MENU_OPTION_FEEDMONEY, MENU_OPTION_SELECT, MENU_OPTION_FINISH}; //created a constant String[] to hold the menu options.

    public static List<Items> Cart = new ArrayList<>(); // added a List to hold Item objects like candy,gum,drink,chips
    public static Map<String, Items> itemKeys = new HashMap<>(); // Map containing Ids and Items to access their values


    private Menu menu2; //created a Menu object called menu2

    public SecondMenu(Menu menu) {
        this.menu2 = menu;
    }

    //menu2 Constructor
    public void run2() { //run2 is the second menu when you select purchase from the main menu

        while (true) { //while the condition is true
            String choice = (String) menu2.getChoiceFromOptions(MENU_OPTIONS); // sets String choice equal to String casted result of the method menu2.getChoiceFromOptions
            if (choice.equals(MENU_OPTION_FEEDMONEY)) { //if user selects first option...
                Money.moneyInserted(); //calls the static method from the money class to add money

            } else if (choice.equals(MENU_OPTION_SELECT)) { //if user selects the second option...
               userIn(); //calls the selectProduct method within this class

            } else if (choice.equals(MENU_OPTION_FINISH)) { //if user selects the third option...
                finalizeTrans(); //calls the finalizeTrans method
            }

        }
    }

    public static void userIn(){
        VendingMachineCLI.printInventory(); //calls the printInventory method from VendingMachineCLI.
        //Asks for users product choice
        Scanner prodChoice = new Scanner(System.in); //creates a Scanner obj called prodChoice.
        System.out.println("---------------------------------"); // to establish a border.
        System.out.print("Select Product: ");  //prints out instruction.
        String usrChoice = prodChoice.nextLine().toUpperCase(); //Sets a String called choice to equal the captured input from prodChoice.
        selectProduct(usrChoice);
    }



    public static void selectProduct(String choice) {

        //Loops the ID string and matches it to an Item object's getID method
        if(itemKeys.containsKey(choice)) { //if the map contains the key stored in String choice.... move on to next if statement
            if(itemKeys.get(choice).getAmount() != 0){ // if the item's getAmount in the map is not 0.... move on to next statement
                if(Money.getTotalMoney() != 0 && itemKeys.get(choice).getPrice() < Money.getTotalMoney()){ //if total Money is not 0 and the item's getPrice is less than the totalMoney.... move on to next statement

                    addItemToCart(itemKeys.get(choice).getPos()); //calls on addItemToCart method  and passes along the item's getPos in the map.

                }else{ //if the item price is bigger than the totalMoney or totalMoney IS 0...
                    System.err.println("INSUFFICIENT Funds.."); //prints error
                }
            }else{  //if the item's getAmount is 0 ....
                System.err.println("Item is SOLD OUT.."); //prints error
            }
        } else{  //if the map does not contain the ID stored in String choice....
           System.err.println("item ID is NOT VALID.."); //print error
        }
    }

    public static void addItemToCart(int position){
        //**Debug**
        //System.out.println("Cart size is: "+Cart.size());

        Cart.add(getInventory().get(position)); //adds the item from the inventory List to the cart List
        getInventory().get(position).setAmount(getInventory().get(position).getAmount() - 1); // subtracts the item from the inventory List
        Money.setRemainingMoney(Money.getTotalMoney() - Cart.get(0).getPrice()); // subtracts the price from your balance
        System.out.printf(Cart.get(0).getName() + TextColors.getGreenTxt() +" $"+ "%.2f" + TextColors.getResetTxtColor() +" Balance remaining:"+ TextColors.getGreenTxt() +"$%.2f" + TextColors.getResetTxtColor() + "\n", Cart.get(0).getPrice(),Money.getRemainingMoney()); //prints out name,price and balance after you buy.
        System.out.println(TextColors.getPurpleTxt() + Cart.get(0).printMsg() + TextColors.getResetTxtColor()); // prints out the items message
        Money.setTotalMoney(Money.getRemainingMoney());  //updates the balance to reflect charges
        Log.log(Cart.get(0).getName(),Cart.get(0).getPrice(), Money.getTotalMoney() ); //logs the action
        Cart.clear(); //clears the cart list for the next item
    }





    public static void finalizeTrans(){
        //logger log
        System.out.println(new String(Money.changeDue(Money.getTotalMoney()))); //prints out correct change
        Money.setTotalMoney(0);
        Log.log("GIVE CHANGE:",Money.getRemainingMoney(), Money.getTotalMoney() ); //logs the action
        VendingMachineCLI.run(); //returns to the main menu after giving the change.



    }



}