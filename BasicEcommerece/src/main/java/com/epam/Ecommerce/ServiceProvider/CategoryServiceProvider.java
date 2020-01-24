package com.epam.Ecommerce.ServiceProvider;

import java.util.HashMap;

import com.epam.Ecommerce.Storage.Categories;
import com.epam.Ecommerece.BasicEcommerce.UserInterface;

public class CategoryServiceProvider {
	private static HashMap<String, Categories> categories;
	public CategoryServiceProvider() {
		categories = new HashMap<>(3);
	}

	public static Categories getObject(String category) {
		return categories.get(category);
	}

	public void addCategory(String new_category) {
		String current_category = "EcommerceMainCategory";
		if (!hasCategories())
			categories.put(current_category, new Categories());
		Categories category = getObject(current_category);
		while (category.hasSubCategories()) {
			
			UserInterface.display(category.getSubCategories());
			String user_input = UserInterface.getSelectedCategory("Insert");
			if (user_input.equals("Insert Here"))
				break;
			category = CategoryServiceProvider.getObject(user_input);
		}
		categories.put(new_category, new Categories());
		category.addSubCategories(new_category);
		UserInterface.display("Insertion Successful");
	}

	public void deleteCategory() {
		String current_category = "EcommerceMainCategory";
		Categories category = getObject(current_category);
		while (category.hasSubCategories()) {
			UserInterface.display(category.getSubCategories());
			String user_input = UserInterface.getSelectedCategory("Delete");
			if (user_input.equals("Delete Here"))
				break;
			category = CategoryServiceProvider.getObject(user_input);
		}
		UserInterface.display("enter category name you want o delete");
		String category_to_delete=UserInterface.getUserInput();
		if (!categories.get(category_to_delete).hasSubCategories()) {
		category.deleteSubCategory(category_to_delete);
		categories.remove(category_to_delete);
		UserInterface.display("----" + category + " is deleted----");
		}else {
		UserInterface.display("----" + category + " has subCategories can't delete----");
	}
	}

	public static boolean hasCategories() {
		if (categories.size() > 0)
			return true;
		return false;
	}

	public void deleteCategoryFromSubCategoryList(String category_to_delete) {
		String current_category = "EcommerceMainCategory";
		Categories category = getObject(current_category);
		while (category.hasSubCategories()) {
			UserInterface.display(category.getSubCategories());
			String user_input = UserInterface.getSelectedCategory("Delete");
			if (user_input.equals("Delete Here"))
				break;
			category = CategoryServiceProvider.getObject(user_input);
		}
		UserInterface.display("enter category name you want o delete");
		category_to_delete=UserInterface.getUserInput();
		if (!categories.get(category_to_delete).hasSubCategories()) {
		category.deleteSubCategory(category_to_delete);
		categories.remove(category_to_delete);
		UserInterface.display("----" + category + " is deleted----");
		}else {
		UserInterface.display("----" + category + " has subCategories can't delete----");
	}
	}
}
