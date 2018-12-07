package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.repository.CategoryRepository;

import java.util.*;

public class CategoryController {

    public CategoryController() {
        if (CategoryRepository.getAllRecords().size() == 0) {
            initializeCategories();
        }
    }

    private void initializeCategories() {
        Category eight_track_cat = new Category();
        eight_track_cat.setName("Eight Track Tapes");
        eight_track_cat.setDescription("Our collection of the best 8-track tapes of era.  Listen to the BeeJees, Peter Frampton and other classics.");
        CategoryRepository.createRecord(eight_track_cat);

        Category toys_cat = new Category();
        toys_cat.setName("Toys");
        toys_cat.setDescription("Our collection of the best 8-track tapes of era.  Listen to the BeeJees, Peter Frampton and other classics.");
        CategoryRepository.createRecord(toys_cat);
    }

    /**
     * Get a list of all category names
     *
     * @return list of categories
     */
    public List<Category> getCategories() {
        return CategoryRepository.getAllRecords();
    }

    /**
     * Get the category that matches the provided category name
     *
     * @param name
     * @return Category, if present
     */
    public Category findCategory(String name) {
        Category matchingCategory = null;
        List<Category> categories = CategoryRepository.getAllRecords();
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                matchingCategory = category;
            }
        }
        return matchingCategory;
    }

    /**
     * Loop through all our categories and write any product records that
     * have been updated.
     */
    public void updateCategories(List<Category> categories) {
        for (Category category : categories) {
            CategoryRepository.updateRecord(category);
        }
    }

}
