package ru.aryukov.dao;


import org.hibernate.query.Query;
import ru.aryukov.cache.CacheElement;
import ru.aryukov.cache.CacheEngine;
import ru.aryukov.cache.cacheImpl.CacheEngineImpl;
import ru.aryukov.domain.UserEntity;
import ru.aryukov.util.HibernateUtil;

/**
 * Created by dev on 17.07.17.
 */
public class UserEntityDao extends CommonDaoImpl<UserEntity, Integer> {

    public static final int MAX_ELEMENTS = 300;
    public static final long LIFE_TIMES_MS = 10000;
    public static final long IDLE_TIME_MS = 10000;

    protected CacheEngine<Integer, UserEntity> dataCache;

    public void startUp(){
        dataCache = new CacheEngineImpl<>(MAX_ELEMENTS, LIFE_TIMES_MS, IDLE_TIME_MS, false);
    }

    public CacheEngine<Integer, UserEntity> getCache(){
        return dataCache;
    }

    public UserEntity find(Integer id) {
        CacheElement<UserEntity> element = dataCache.get(id);
        UserEntity obj = null;
        if (element != null) {
            obj = element.getValue();
        }
        if (obj == null) {
            obj = findByID(UserEntity.class, id);
            dataCache.put(id, new CacheElement<>(obj));
        }
        return obj;
    }

    public UserEntity findByName(String name) {
        UserEntity userEntity = null;
        Query query = HibernateUtil.getSession().createQuery("from UserEntity where name = :name").setParameter("name", name);
        userEntity = findOne(query);
        return userEntity;
    }
}
