package com.epam.Ecommerce.ServiceProvider;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.Ecommerce.Integrator.ProductsAndCategoryManager;
import com.epam.Ecommerce.Storage.Categories;
import com.epam.Ecommerce.Storage.Products;
import com.epam.Ecommerece.BasicEcommerce.UserInterface;

public class ProductServiceProvider {

	private static HashMap<String, Products> products;

	public ProductServiceProvider() {
		// TODO Auto-generated constructor stub
		if (products == null)
			products = new HashMap<String, Products>(3);
	}

	public static Products getObject(String product_name) {
		return products.get(product_name);
	}

	protected Products generateProductObject(String product_name) {
		products.put(product_name, new Products());
		return products.get(product_name);
	}

	public void addNewProduct(String name, double price, int quantity) {
		if (CategoryServiceProvider.hasCategories()) {
			Products product = generateProductObject(name);
			product.setProduct_name(name);
			product.setPrice(price);
			product.setQuantity(quantity);

			String current_category = "EcommerceMainCategory";
			Categories category = CategoryServiceProvider.getObject(current_category);
			while (category.hasSubCategories()) {
				UserInterface.display(category.getSubCategories());
				current_category = UserInterface.getSelectedCategory();// change here
				category = CategoryServiceProvider.getObject(current_category);
			}
			ProductsAndCategoryManager manager = new ProductsAndCategoryManager();
			manager.addProductToCategory(current_category, name);
		}
	}

	public static String getProductDetails(String product_name) {
		Products product = ProductServiceProvider.getObject(product_name);
		String product_details = product.getProduct_name() + " ->" + product.getPrice();
		if(product.getQuantity()<=0)
			product_details+=" "+"Out Of Stock";
		else
			product_details+=" "+product.getQuantity();
		return product_details;
	}

	public static void updateProductsQuantity(ArrayList<String> selected_products) {
		for (String product : selected_products) {
			String product_name = product.substring(0, product.indexOf("->")).trim();
			Products product_Object = ProductServiceProvider.getObject(product_name);
			product_Object.setQuantity(product_Object.getQuantity() - 1);
		}
	}

	public static void deleteProduct(String product_name) {
		products.remove(product_name);
	}

	public static void updateCanceledProductsQuantity(ArrayList<String> selected_products) {
		for (String product : selected_products) {
			String product_name = product.substring(0, product.indexOf("->")).trim();
			Products product_Object = ProductServiceProvider.getObject(product_name);
			product_Object.setQuantity(product_Object.getQuantity() + 1);
		}
	}
}
