package com.epam.Ecommerce.Storage;

import java.util.ArrayList;

public class Categories {
	private ArrayList<String> sub_categories;

	public Categories() {
		sub_categories = new ArrayList<String>(3);
	}

	public ArrayList<String> getSubCategories() {
		return sub_categories;
	}

	public void addSubCategories(String category) {
		this.sub_categories.add(category);
	}

	public boolean hasSubCategories() {
		if (this.sub_categories.size() > 0)
			return true;
		return false;
	}

	public void deleteSubCategory(String sub_category) {
		sub_categories.remove(sub_category);
	}
}
