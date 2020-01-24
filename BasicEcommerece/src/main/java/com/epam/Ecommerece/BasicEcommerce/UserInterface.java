package com.epam.Ecommerece.BasicEcommerce;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
	private static ArrayList<String> current_items;
	private static Scanner console = new Scanner(System.in);

	public static void display(String message) {
		System.out.println(message);
	}

	public static void display(ArrayList<String> items) {
		current_items = items;
		int choice = 1;
		for (String item : items) {
			System.out.println(choice + ": " + item);
			choice++;
		}
	}

	public static String getSelectedCategory() {
		UserInterface.display("-------Enter choice to continue-----");
		int choice = console.nextInt();
		if (choice > current_items.size())
			return getSelectedCategory();
		return current_items.get(choice - 1);
	}

	public static ArrayList<String> getSelectedProducts() {
		System.out.println("------Enter product's by seperating choice with space------");
		console.nextLine();
		String[] choices = console.nextLine().trim().split(" ");
		ArrayList<String> selected_products = new ArrayList<>(2);
		for (String choice : choices) {
			String item = current_items.get(Integer.parseInt(choice) - 1);
			selected_products.add(item);
		}
		current_items.clear();
		return selected_products;
	}

	public static boolean gotConfirmationYes(String message) {
		System.out.println(message);
		int confirmation;
		try {
			confirmation = console.nextInt();
		} catch (Exception e) {
			System.out.println("------invalid input----");
			return gotConfirmationYes(message);
		}
		if (confirmation == 1)
			return true;
		return false;
	}

	public static String getProductName() {
		UserInterface.display("enter product name");
		console.nextLine();
		return console.nextLine();
	}

	public static double getProductPrice() {
		UserInterface.display("enter product price");
		return console.nextDouble();
	}

	public static int getProductquantity() {
		UserInterface.display("enter product quanity");
		return console.nextInt();
	}

	public static String getCredentials() {
		System.out.println("--enter password---");
		console.nextLine();
		return console.nextLine();
	}

	public static String getUserInput() {
		//if(!inMainCategory)
			console.nextLine();
		return console.nextLine();
	}

	public static int getSelectedOption() {
		return console.nextInt();
	}

	public static String getSelectedCategory(String message) {
		UserInterface.display("-------Enter \"" + message + "\" to "+message+" in current category-----");
		String choice = console.nextLine();
//		console.next();
		if (Character.isLetter(choice.charAt(0)))
			return message + " Here";
		return current_items.get(Integer.parseInt(choice.trim()) - 1);
	}
}
