package org.rit.swen440.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.rit.swen440.dataLayer.OrderHistory;

public class OrderHistoryRepository {

    public static void createRecord(OrderHistory orderHistory) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        int count = 0;
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            sessionObj.save(orderHistory);

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
    public static List<OrderHistory> getAllRecords() {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            orderHistoryList = sessionObj.createQuery("FROM OrderHistory").list();
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
        return orderHistoryList;
    }

    // Method 3: This Method Is Used To Update A Record In The Database Table
    public static void updateRecord(OrderHistory orderHistory) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            OrderHistory orderHistoryObj = (OrderHistory) sessionObj.get(OrderHistory.class, orderHistory.getId());
            orderHistoryObj = orderHistory;

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
    public static void deleteRecord(OrderHistory orderHistory) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            OrderHistory orderHistoryObj = (OrderHistory) sessionObj.get(OrderHistory.class, orderHistory.getId());
            sessionObj.delete(orderHistoryObj);

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

            Query queryObj = sessionObj.createQuery("DELETE FROM OrderHistory");
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
