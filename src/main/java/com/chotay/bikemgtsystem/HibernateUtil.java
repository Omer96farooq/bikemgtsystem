package com.chotay.bikemgtsystem;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private HibernateUtil(){}
    
    public static boolean setSessionFactory() {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            System.out.println(ex);
            return false;
            
        }
        
        return true;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
