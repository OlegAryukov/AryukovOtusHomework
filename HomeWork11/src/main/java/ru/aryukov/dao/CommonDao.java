package ru.aryukov.dao;

import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dev on 17.07.17.
 */
public interface CommonDao<T, ID extends Serializable> {
    public void save(T entity);

    public void merge(T entity);

    public void delete(T entity);

    public List<T> findMany(Query query);

    public T find(long id);

    public T findOne(Query query);

    public List findAll(Class clazz);

    public T findByID(Class clazz, Long id);
}
