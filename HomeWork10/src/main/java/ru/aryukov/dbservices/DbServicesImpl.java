package ru.aryukov.dbservices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.aryukov.domain.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

/**
 * Created by dev on 17.07.17.
 */
public class DbServicesImpl implements DbServices {
    private static SessionFactory sessionFactory;

    public static void startup() throws SQLException {
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

    @Override
    public String getLocalStatus() {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    @Override
    public void save(UserEntity userEntity) {
        /*try (Session session = sessionFactory.openSession()){

        }*/
    }

    @Override
    public UserEntity read(long id) {
        return null;
    }

    @Override
    public UserEntity readByName(String name) {
        return null;
    }

    @Override
    public List<UserEntity> readAll() {
        return null;
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
    }
}
