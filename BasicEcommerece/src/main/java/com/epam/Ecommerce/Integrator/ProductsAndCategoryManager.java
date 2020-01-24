package com.epam.Ecommerce.Integrator;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.Ecommerce.ServiceProvider.ProductServiceProvider;

public class ProductsAndCategoryManager extends ProductServiceProvider {
	private static HashMap<String, ProductsInCategory> products;

	class ProductsInCategory {
		private ArrayList<String> products_names;

		public ProductsInCategory() {
			products_names = new ArrayList<String>();
		}

		public ArrayList<String> getProductsNames() {
			return this.products_names;
		}

		public void addProductsNames(String productName) {
			this.products_names.add(productName);
		}
	}

	public ProductsAndCategoryManager() {
		if (products == null)
			products = new HashMap<>(3);
	}

	public ArrayList<String> getProductsFromCategory(String category) {
		return products.get(category).getProductsNames();
	}

	public void addProductToCategory(String category, String product) {
		if (!products.containsKey(category))
			products.put(category, new ProductsInCategory());
		products.get(category).addProductsNames(product);

	}
}
