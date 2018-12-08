package org.rit.swen440;

import org.rit.swen440.presentation.MenuManager;

import java.io.FileInputStream;
import java.io.IOException;

public class OrderSystem {
    public static void main(String[] args) {

        MenuManager mgr = new MenuManager();
        int currentLevel = 0;
        boolean done = false;
        do {
            done = mgr.loadLevel(currentLevel);
        } while (!done);

        System.out.println("Thank you for shopping at Hippolyta.com!");

    }
}