package org.rit.swen440.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.rit.swen440.control.CategoryController;
import org.rit.swen440.control.ProductController;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.dataLayer.User;


public class MenuManager {
    private int currentLevel = 0;
    private String currentCategoryName;
    private String currentItemName;

    private CategoryController categoryController;
    private ProductController productController;

    private User currentUser;

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
        Menu menu = new Menu();

        menu.addMenuItem("'0' to Login");
        menu.addMenuItem("'1' to Create Account");
        menu.addMenuItem("'q' to Exit");

        menu.printMenu();
        String result = "0";
        try {
            result = menu.getSelection();
        } catch (Exception e) {
            result = "q";
        }
        try {
            Integer.parseInt(result);
        } catch (NumberFormatException ex) {
            result = "q";
        }
        if (Objects.equals(result, "q")) {
            currentLevel--;
        } else {
            int iSel = Integer.parseInt(result);
            currentLevel++;
            if (iSel == 0) {
                LevelLogin();
            } else if (iSel == 1) {
                LevelCreateAccount();
            } else {
                System.out.println("Selection Invalid...Try Again");
                currentLevel--;
            }

        }
    }

    private void LevelLogin() {
        System.out.println("User is logged in");
    }

    private void LevelCreateAccount() {
        System.out.println("User account created and logged in");
    }

    private void Level1() {
        Menu m = new Menu();
        List<Category> categories = categoryController.getCategories();

        for(Category cat: categories){
            m.addMenuItem(cat.getName());
        }

        m.addMenuItem("'q' to Logout");
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


    private void Level2() {
        Menu m = new Menu();


        List<Product> productList = productController.getProducts(currentCategoryName);
        System.out.println();

        String yeet;
        for (Product product: productList){
            yeet = product.getTitle() + "($" + product.getCost() + ")";
            m.addMenuItem(yeet);
        }

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