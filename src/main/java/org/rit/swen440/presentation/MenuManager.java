package org.rit.swen440.presentation;

import org.rit.swen440.control.CategoryController;
import org.rit.swen440.control.OrderController;
import org.rit.swen440.control.ProductController;
import org.rit.swen440.control.UserController;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.dataLayer.User;

import java.util.List;
import java.util.Objects;


public class MenuManager {
    private int currentLevel = 0;
    private String currentCategoryName;
    private String currentItemName;

    private CategoryController categoryController;
    private ProductController productController;
    private UserController userController;
    private OrderController orderController;

    private User currentUser;

    public MenuManager() {
        userController = new UserController();
        categoryController = new CategoryController();
        productController = new ProductController();
        orderController = new OrderController();

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
            if (iSel == 0) {
                LevelLogin();
            } else if (iSel == 1) {
                LevelCreateAccount();
            } else {
                System.out.println("Selection Invalid...Try Again");
            }

        }
    }

    private void LevelLogin() {
        Menu menu = new Menu();

        menu.addMenuItem("'q' to Cancel");
        menu.addMenuItem("Type username <enter> password to login:");
        menu.printMenu();
        String username = "";
        String password = "";
        try {
            username = menu.getSelection();
        } catch (Exception e) {
            username = "q";
        }
        if (Objects.equals(username, "q")) {
            System.out.println("Canceled");
        } else {
            password = menu.getSelection();
            User user = userController.login(username, password);
            if (user == null) {
                System.out.println("\nYour credentials were invalid, try again...");
            } else {
                System.out.println("\nWelcome " + user.getFullName());
                currentUser = user;
                currentLevel++;
                System.out.println("User is logged in");
            }
        }
    }

    private void LevelCreateAccount() {
        Menu menu = new Menu();

        menu.addMenuItem("'q' to Cancel");
        menu.addMenuItem("Type you full name <enter> username <enter> password to create an account:");
        menu.printMenu();
        String fullName = "";
        String username = "";
        String password = "";
        try {
            fullName = menu.getSelection();
        } catch (Exception e) {
            fullName = "q";
        }
        if (Objects.equals(fullName, "q")) {
            System.out.println("Canceled");
        } else {
            username = menu.getSelection();
            password = menu.getSelection();
            User user = userController.createAccount(username, password, fullName);
            if (user == null) {
                System.out.println("\nSomething went wrong, try again...");
            } else {
                System.out.println("\nWelcome " + user.getFullName());
                currentUser = user;
                currentLevel++;
                System.out.println("User account created and logged in");
            }
        }
    }

    private void Level1() {
        Menu m = new Menu();
        List<Category> categories = categoryController.getCategories();

        for(Category cat: categories){
            m.addMenuItem(cat.getName());
        }

        m.addMenuItem("'q' to Logout");
        System.out.println("The following categories are available");
        m.printMenu();
        String result = "0";
        try {
            result = m.getSelection();
        } catch (Exception e) {
            result = "q";
        }
        if (Objects.equals(result, "q")) {
            currentLevel--;
            currentUser = null;
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

        orderController.createOrder(Integer.parseInt(result), currentUser, product);
    }
}