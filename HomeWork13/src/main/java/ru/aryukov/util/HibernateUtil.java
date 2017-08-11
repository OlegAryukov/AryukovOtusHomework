package ru.aryukov.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by dev on 17.07.17.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
    }
    private static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static Session beginTransaction(){
        Session hibernateSession = HibernateUtil.getSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }
    public static void commitTransaction(){
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void rollbackTransaction(){
        HibernateUtil.getSession().getTransaction().rollback();
    }

    public static void closeSession(){
        HibernateUtil.getSession().close();
    }

    public static Session getSession(){
        Session hiberanateSession = sessionFactory.getCurrentSession();
        return hiberanateSession;
    }
}
