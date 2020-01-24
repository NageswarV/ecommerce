package com.epam.Ecommerece.BasicEcommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.Ecommerce.Integrator.ProductsAndCategoryManager;
import com.epam.Ecommerce.ServiceProvider.CategoryServiceProvider;
import com.epam.Ecommerce.ServiceProvider.ProductServiceProvider;
import com.epam.Ecommerce.Storage.Categories;

public class ServiceToUI {
	public static void main(String[] args) {
		UserInterface.display("------Welcome to Epam Service-----------");

		int option;
		do {
			UserInterface.display("---select Option---\n1.user\n2.admin\n >2.to exit ");
			option = UserInterface.getSelectedOption();
			switch (option) {
			case 1:
				userRole();
				break;
			case 2:
				adminRole();
				break;
			}
		} while (option < 3);
	}

	private static void addToCart(ArrayList<String> selected_products) {
		UserInterface.display("-----Products in cart------");
		List<String> placed_order = selected_products.stream().filter(product -> !product.endsWith("Out Of Stock"))
				.map(product -> product.substring(0, product.lastIndexOf(' '))).collect(Collectors.toList());
		UserInterface.display((ArrayList<String>) placed_order);

		if (!UserInterface.gotConfirmationYes("-----confirm your order by entering 1 --and any number to cancel--")) {
			ProductServiceProvider.updateCanceledProductsQuantity(selected_products);
			UserInterface.display("Your Oreder has Canceled successfully");
		} else {
			UserInterface.display("--Your Order has Succesfully placed--");
		}
	}

	private static void userRole() {
		ArrayList<String> selected_products = new ArrayList<>();
		ArrayList<String> product_details = new ArrayList<>();
		try {
		do {
			ArrayList<String> current_products = new ArrayList<>();
			UserInterface.display("------Please select option from menu----");

			String current_category = "EcommerceMainCategory";
			Categories category = CategoryServiceProvider.getObject(current_category);
			while (category.hasSubCategories()) {
				UserInterface.display(category.getSubCategories());
				current_category = UserInterface.getSelectedCategory();
				category = CategoryServiceProvider.getObject(current_category);
			}
			ProductsAndCategoryManager manager = new ProductsAndCategoryManager();
			ArrayList<String> products = manager.getProductsFromCategory(current_category);
			for (String product : products)
				product_details.add(ProductServiceProvider.getProductDetails(product));
			UserInterface.display(product_details);
			current_products.addAll(UserInterface.getSelectedProducts());
			selected_products.addAll(current_products);
			ProductServiceProvider.updateProductsQuantity(current_products);
			UserInterface.display("----do u want continue shopping-----");
		} while (UserInterface.gotConfirmationYes(
				"-----confirm your response by entering 1 to continue----and any number to end-----"));
		addToCart(selected_products);
		}catch(NullPointerException exception){
			UserInterface.display("Products are not yet introduced --");
		}
	}

	private static void adminRole() {
		try {
			Object admin = Admin.validate(UserInterface.getCredentials());
			Admin adminAccess = (Admin) admin;
			if (admin.getClass().getSimpleName().equalsIgnoreCase("admin")) {
				int choice;
				
				do {
					UserInterface
							.display("---select option---\n1.new Category\n2.remove category\n3.add Product\n >4.exit");
					choice = UserInterface.getSelectedOption();
					switch (choice) {
					case 1:
						UserInterface.display("Enter Category name");
						adminAccess.insertNewCategory(UserInterface.getUserInput());
						break;
					case 2:
						UserInterface.display("Enter Category name");
						adminAccess.removeCategory();
						break;
					case 3:
						adminAccess.includeNewProduct();
						break;
					}
				} while (choice >= 4);
			}
		} catch (NullPointerException e) {
			UserInterface.display("--------Authentication problem------");
		}
	}
}
