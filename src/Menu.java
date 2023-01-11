package com.techelevator.view;

import com.techelevator.Money;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

    public Menu(Menu pMenu) {
    }

    public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {

		//Displays the balance and Title if balance is 0 its red and more than 0 its green.
		System.out.println("------------------------------------");
		System.out.println(TextColors.getCyantxt() + "         Vendo-Matic 800" + TextColors.getResetTxtColor());
		System.out.println("------------------------------------");
		if(Money.getTotalMoney() == 0) {
			System.out.printf("\n" + "Current Money Provided: " + TextColors.getRedTxt() + "$ %.2f" + TextColors.getResetTxtColor() + "\n", Money.getTotalMoney());
		}else {
			System.out.printf("\n" + "Current Money Provided: " + TextColors.getGreenTxt() + "$ %.2f" + TextColors.getResetTxtColor() + "\n", Money.getTotalMoney());
		}


		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}
