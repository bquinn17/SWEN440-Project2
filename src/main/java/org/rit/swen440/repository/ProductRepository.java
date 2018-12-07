package org.rit.swen440.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.rit.swen440.dataLayer.Product;

public class ProductRepository {

    public static void createRecord(Product product) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        int count = 0;
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            sessionObj.save(product);

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
    public static List<Product> getAllRecords() {
        List productList = new ArrayList();
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            productList = sessionObj.createQuery("FROM Product").list();
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
        return productList;
    }

    // Method 3: This Method Is Used To Update A Record In The Database Table
    public static void updateRecord(Product product) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            Product productObj = (Product) sessionObj.get(Product.class, product.getSkuCode());
            productObj = product;

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
    public static void deleteRecord(Product product) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Product productObj = (Product) sessionObj.get(Product.class, product.getSkuCode());
            sessionObj.delete(productObj);

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

            Query queryObj = sessionObj.createQuery("DELETE FROM Product");
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

