package com.epam.Ecommerece.BasicEcommerce;

import com.epam.Ecommerce.ServiceProvider.CategoryServiceProvider;
import com.epam.Ecommerce.ServiceProvider.ProductServiceProvider;

public class Admin extends CategoryServiceProvider {
	private static String password;
	private static Admin admin;
	static {
		password = "00000";
	}

	public static Admin validate(String entered_password) {
		if (password.hashCode() == entered_password.hashCode()) {
			admin = new Admin();
			return admin;
		} else
			return null;
	}

	public static Admin getAdminAccess() {
		return admin;
	}

	public void insertNewCategory(String new_category) {
		addCategory(new_category);
	}

	public void removeCategory() {
		deleteCategory();
	}

	public void includeNewProduct() {
		String name = UserInterface.getProductName();
		double price = UserInterface.getProductPrice();
		int quantity = UserInterface.getProductquantity();
		ProductServiceProvider productseviceprovider = new ProductServiceProvider();
		productseviceprovider.addNewProduct(name, price, quantity);
	}
}
