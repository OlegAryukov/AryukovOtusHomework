package ru.aryukov.DAO;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by oaryukov on 08.06.2017.
 */
public interface ICommonDao<T, ID extends Serializable> {
    public void create(T entity);

    public void update(T entity);

    public void delete(T entity);

    public List<T> findMany(Query query);

    public List findAll(Class clazz);

    public T findByID(Class clazz, int id);
}
