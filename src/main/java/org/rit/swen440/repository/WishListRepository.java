package org.rit.swen440.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.rit.swen440.dataLayer.WishList;

public class WishListRepository {

    public static void createRecord(WishList wishList) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        int count = 0;
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            sessionObj.save(wishList);

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Method 2: This Method Is Used To Display The Records From The Database Table
    public static List<WishList> getAllRecords() {
        List<WishList> wishListList = new ArrayList<>();
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            wishListList = sessionObj.createQuery("FROM WishList").list();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return wishListList;
    }

    // Method 3: This Method Is Used To Update A Record In The Database Table
    public static void updateRecord(WishList wishList) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            WishList wishListObj = (WishList) sessionObj.get(WishList.class, wishList.getUserId());
            wishListObj = wishList;

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
    public static void deleteRecord(WishList wishList) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            WishList wishListObj = (WishList) sessionObj.get(WishList.class, wishList.getUserId());
            sessionObj.delete(wishListObj);

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    // Method 5: This Method Is Used To Delete All Records From The Database Table
    public static void deleteAllRecords() {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Query queryObj = sessionObj.createQuery("DELETE FROM WishList");
            queryObj.executeUpdate();

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
}
