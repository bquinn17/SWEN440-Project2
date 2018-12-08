package org.rit.swen440.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.rit.swen440.control.CategoryController;
import org.rit.swen440.control.ProductController;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;


public class MenuManager {
    private int currentLevel = 0;
    private String currentCategoryName;
    private String currentItemName;

    private CategoryController categoryController;
    private ProductController productController;

    public MenuManager() {

        categoryController = new CategoryController();
        productController = new ProductController();

    }

    public boolean loadLevel(int level) {
//        System.out.println("Loading level:" + currentLevel);
        switch (currentLevel) {
            case -1:
                return true;
            case 0:
                Level0();
                break;
            case 1:
                Level1();
                break;
            case 2:
                Level2();
                break;
            default:
                System.out.println("Returning to main org.rit.swen440.presentation.menu");
                currentLevel = 0;
                Level0();
                break;
        }

        return false;
    }

    private void Level0() {
        Menu m = new Menu();
        List<Category> categories = categoryController.getCategories();

        m.loadMenu();

        for(Category cat: categories){
            m.addMenuItem(cat.getName());
        }

        m.addMenuItem("'q' to Quit");
        System.out.println("The following org.rit.swen440.presentation.Categories are available");
        m.printMenu();
        String result = "0";
        try {
            result = m.getSelection();
        } catch (Exception e) {
            result = "q";
        }
        if (Objects.equals(result, "q")) {
            currentLevel--;
        } else {
            currentLevel++;
            int iSel = Integer.parseInt(result);

            currentCategoryName = categories.get(iSel).getName();
            System.out.println("\nYour Selection was:" + currentCategoryName);
        }
    }

    private void Level1() {
        Menu m = new Menu();


        List<Product> productList = productController.getProducts(currentCategoryName);
        System.out.println();

        String yeet;
        for (Product product: productList){
            yeet = product.getTitle() + "($" + product.getCost() + ")";
            m.addMenuItem(yeet);
        }

        m.loadMenu();
        m.addMenuItem("'q' to quit");
        System.out.println("The following items are available");
        m.printMenu();
        String result = m.getSelection();
        try {
            int iSel = Integer.parseInt(result);//Item  selected
            currentItemName = productList.get(iSel).getTitle();
            //currentItem = productList.get(iSel);
            //Now read the file and print the org.rit.swen440.presentation.items in the catalog
            System.out.println("You want item from the catalog: " + currentItemName);
        } catch (Exception e) {
            result = "q";
        }
        if (result.equals("q"))
            currentLevel--;
        else {
            //currentLevel++;//Or keep at same level?
            OrderQty(currentCategoryName, currentItemName);
        }
    }


    private void Level2() {

    }

    private void OrderQty(String category, String item) {
        System.out.println("Please select a quantity");
        System.out.println("");
        Product product = productController.getProduct(item);
        System.out.println(product.getTitle() + " availability: " + product.getItemCount());
        System.out.print(":");
        Menu m = new Menu();
        String result = m.getSelection();
        System.out.println("You ordered:" + result);
    }
}