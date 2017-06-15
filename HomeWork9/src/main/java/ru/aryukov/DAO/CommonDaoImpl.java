package ru.aryukov.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by oaryukov on 08.06.2017.
 */
public abstract class CommonDaoImpl <T, ID extends Serializable> implements ICommonDao<T,ID> {

    @PersistenceContext
    protected EntityManager em;


    public void create(T entity) {
        em.persist(entity);
    }

    public void update(T entity) {
        em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));

    }

    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.getResultList();
        return t;
    }

    public T findByID(Class clazz, int id) {
        T o = (T) em.find(clazz, id);
        return o;
    }

    public List findAll(Class clazz) {
        List<T>  list = em.createQuery(" from " + clazz.getName()).getResultList();
        return list;
    }
}