package ru.aryukov.dao;


import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.aryukov.cache.CacheElement;
import ru.aryukov.cache.CacheEngine;
import ru.aryukov.cache.UserEntityCache;
import ru.aryukov.cache.cacheImpl.CacheEngineImpl;
import ru.aryukov.domain.UserEntity;
import ru.aryukov.util.HibernateUtil;

/**
 * Created by dev on 17.07.17.
 */
@Configurable
public class UserEntityDao extends CommonDaoImpl<UserEntity, Integer> {

    @Autowired
    private UserEntityCache dataCache;

    public UserEntityCache getCache(){
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

    @Override
    public void startUp() {

    }
}
