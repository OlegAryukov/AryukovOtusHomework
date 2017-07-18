package ru.aryukov.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.aryukov.dbservices.DbServicesImpl;
import ru.aryukov.util.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * Created by dev on 17.07.17.
 */
public abstract class CommonDaoImpl<T, ID extends Serializable> implements CommonDao<T, ID> {
    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    @Override
    public void save(T entity) {
        HibernateUtil.beginTransaction();
        this.getSession().saveOrUpdate(entity);
        this.getSession().flush();
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
    }

    @Override
    public void merge(T entity) {
        this.getSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        this.getSession().delete(entity);
    }

    @Override
    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }

    @Override
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }

    @Override
    public List findAll(Class clazz) {
        List T = null;
        Query query = this.getSession().createQuery("from " + clazz.getName());
        T = query.list();
        return T;
    }

    @Override
    public T findByID(Class clazz, int id) {
        T t = null;
        t = (T) this.getSession().get(clazz, id);
        return t;
    }

    private <R> R runInSession(Function<Session, R> function) {
        HibernateUtil.beginTransaction();
        R result = function.apply(this.getSession());
        HibernateUtil.commitTransaction();
        return result;
    }
}
