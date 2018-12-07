package org.rit.swen440.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.rit.swen440.dataLayer.*;

class DBSession {

    private static SessionFactory sessionFactoryObj;

    // This Method Is Used To Create The Hibernate's SessionFactory Object
    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configuration = new Configuration();
        configuration.addResource("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(WishList.class);
        configuration.addAnnotatedClass(OrderHistory.class);
        configuration.configure();

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configuration.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    static org.hibernate.Session getSession(){
        if (sessionFactoryObj == null || sessionFactoryObj.isClosed()) {
            buildSessionFactory();
        }
        org.hibernate.Session sessionObj = sessionFactoryObj.openSession();
        return sessionObj;
    }

    static void closeSessionFactory() {
        sessionFactoryObj.close();
        sessionFactoryObj = null;
    }
}
