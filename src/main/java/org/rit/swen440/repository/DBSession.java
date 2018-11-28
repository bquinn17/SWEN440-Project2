package org.rit.swen440.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.dataLayer.User;

public class DBSession {

    private static SessionFactory sessionFactoryObj;
    private static org.hibernate.Session sessionObj;

    // This Method Is Used To Create The Hibernate's SessionFactory Object
    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configuration = new Configuration();
        configuration.addResource("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(User.class);
        configuration.configure();

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configuration.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static org.hibernate.Session getSession(){

        if (sessionObj == null) {
            buildSessionFactory();
            sessionObj = buildSessionFactory().openSession();
        }

        return sessionObj;
    }

    public static void closeSession() {
        sessionObj.close();
        sessionObj.clear();
        sessionFactoryObj.close();
        sessionObj = null;
        sessionFactoryObj = null;
    }
}
