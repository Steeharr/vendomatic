package com.techelevator;

import com.techelevator.view.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = TextColors.getYellowTxt() + "Display Vending Machine Items" + TextColors.getResetTxtColor();
	private static final String MAIN_MENU_OPTION_PURCHASE = TextColors.getGreenTxt() + "Purchase" + TextColors.getResetTxtColor();
	private static final String MAIN_MENU_OPTION_Exit =  TextColors.getRedTxt() + "Exit" + TextColors.getResetTxtColor();

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_Exit };

	private static List<Items> inventory = new ArrayList<>();

	public static List<Items> getInventory() {
		return inventory;
	}

	private static Menu menu;

	public VendingMachineCLI(Menu menu) {
		VendingMachineCLI.menu = menu;
	}


	static File file = new File("vendingmachine.csv");  //creates a file obj to refrence vendingMachine.csv.

	public static void run() { //this is the main start menu

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS); // sets String choice equal to String casted result of the method menu.getChoiceFromOptions

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { //if user selects first option...
				printInventory(); // calls the printInventory from within the class to display items

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) { //if user selects the second option...
				menu2(); // calls for menu2 to be ran from SecondMenu.

			}else if (choice.equals(MAIN_MENU_OPTION_Exit)) { //if user selects the third option...
				System.exit(1); //application exits...
			}

		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		addItemsToInventory(file); //restocks the vendingMachine

		for (int i = 0; i < getInventory().size(); i++) { //loops through the inventory to add items to map
			SecondMenu.itemKeys.put(getInventory().get(i).getId(),getInventory().get(i)); //add elements to map {key = items ID value = Item object}
		}
		cli.run();
	}

	//menu2 method
	public static void menu2(){
		Menu menu2 = new Menu(System.in, System.out); //creates a menu obj to receive input and output.
		SecondMenu second = new SecondMenu(menu2); // creates a second menu object and passes the menu2.
		second.run2(); // calls the run2 method from SecondMenu to display other options.
	}

	public static void addItemsToInventory(File file){

		try(Scanner inventoryScanner = new Scanner(file)){   //creates scanner obj to scan the csv file.
		int counter = 0;
			while(inventoryScanner.hasNextLine()){   //while the csv file has a nextline.
				String line = inventoryScanner.nextLine();  // assign that line of txt to a string var named line.
				String[] bucket = line.split("\\|");  //create a String[] called bucket and add the result of line split by the "|".
				String q = bucket[0];  //assigns the 0 position of i to String q which is the ID.
				String w = bucket[1];  //assigns the 1 position of i to String w which is the Name.
				double e = Double.parseDouble(bucket[2]); //assigns the 2 position of i to Double e which is the Price.
				String r = bucket[3]; //assign the 3 position of i to String r which is the type of item it is.
				int t = counter; // counter to assign the position of the item to later use in the addItemToCart method.
				switch (r) {  //switch depends on the String r
					case "Chip":  // the String r equals type chip.
						Chips newChips = new Chips(q, w, e, r, t); // create a chip object along with id name and price.
						inventory.add(newChips); // add the chip item to inventory.
						counter++; //increment counter by 1
						break; //break from switch.
					case "Candy": //the String r equals type candy.
						Candy newCandy = new Candy(q, w, e, r, t); // // create a candy object along with id name and price.
						inventory.add(newCandy); //add the candy item to inventory.
						counter++; //increment counter by 1
						break; //break from switch.
					case "Drink": //the String r equals type drink.
						Drink newDrink = new Drink(q, w, e, r,t); // // create a drink object along with id name and price.
						inventory.add(newDrink); //add the candy item to inventory.
						counter++; //increment counter by 1
						break; //break from switch.
					case "Gum": //the String r equals type gum.
						Gum newGum = new Gum(q, w, e, r, t);  // create a gum object along with id name and price.
						inventory.add(newGum); // add the gum item to inventory.
						counter++; //increment counter by 1
						break; //break from switch
				}
			}

		}catch(FileNotFoundException fnaf){ //catches the FileNotFoundException
			System.err.println("File was NOT FOUND..."); //prints out file was not found
			System.out.println("Now exiting..."); // warns that application is going to exit
			System.exit(404); // exits with a 404 code for NotFound
		}
	}

	public static void printInventory(){ //prints out the inventory
		for (int i=0; i < inventory.size(); i++){ //for i = 0, i is less than the size of inventory, increment i by 1
			//displays the Item in inventory's i position and returns the ID, the name, the price and the amount of that item.
			System.out.printf(TextColors.getPurpleTxt() + inventory.get(i).getId() + TextColors.getResetTxtColor() + " " + inventory.get(i).getName()+ " " + TextColors.getGreenTxt() + "%.2f" + TextColors.getResetTxtColor() +" ("+inventory.get(i).getAmount()+")" + "\n" , inventory.get(i).getPrice() );
		}
	}



}

