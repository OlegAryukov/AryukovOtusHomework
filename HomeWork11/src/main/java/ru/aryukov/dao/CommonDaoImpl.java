package ru.aryukov.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.aryukov.cache.CacheElement;
import ru.aryukov.cache.CacheEngine;
import ru.aryukov.cache.cacheImpl.CacheEngineImpl;
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
    protected CacheEngine<ID, T> dataCache;

    public static final int MAX_ELEMENTS = 300;
    public static final long LIFE_TIMES_MS = 10000;
    public static final long IDLE_TIME_MS = 10000;

    public void startUp(){
        dataCache = new CacheEngineImpl<>(MAX_ELEMENTS, LIFE_TIMES_MS, IDLE_TIME_MS, false);
    }

    @Override
    public void save(T entity) {
        this.getSession().beginTransaction();
        this.getSession().saveOrUpdate(entity);
        this.getSession().getTransaction().commit();
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
    public T find(ID id) {
        CacheElement<T> element = dataCache.get(id);
        T obj = null;
        if (element != null) {
            obj = element.getValue();
        }
        if (obj == null) {
            obj = findByID(obj.getClass(), id);
            dataCache.put(id, new CacheElement<>(obj));
        }
        return obj;
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
    public T findByID(Class clazz, ID id) {
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
