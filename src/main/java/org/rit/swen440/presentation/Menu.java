package org.rit.swen440.presentation;

import java.util.List;
import java.util.Scanner;

class Menu {
    private List<String> menuList;
    private Scanner sc;

    Menu() {
        sc = new Scanner(System.in);
    }

    void loadMenu(List<String> menuItems) {
        menuList = menuItems;
    }

    void addMenuItem(String item) {
        menuList.add(item);
    }

    void printMenu() {
        System.out.println("");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(i + ": " + menuList.get(i));
        }
        System.out.println("");
    }

    String getSelection() {
        String result = "x";

        sc.reset();
        result = sc.nextLine();
        return result;
    }
}