package org.rit.swen440.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.rit.swen440.dataLayer.User;

public class UserRepository {

    public static void createRecord(User user) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        int count = 0;
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            sessionObj.save(user);

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
    public static List getAllRecords() {
        List userList = new ArrayList();
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            userList = sessionObj.createQuery("FROM User").list();
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
        return userList;
    }

    // Method 3: This Method Is Used To Update A Record In The Database Table
    public static void updateRecord(User user) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            User userObj = (User) sessionObj.get(User.class, user.getId());
            userObj = user;

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
    public static void deleteRecord(User user) {
        // Getting Session Object From SessionFactory
        Session sessionObj = DBSession.getSession();
        try {
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            User userObj = (User) sessionObj.get(User.class, user.getId());
            sessionObj.delete(userObj);

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

            Query queryObj = sessionObj.createQuery("DELETE FROM User");
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
