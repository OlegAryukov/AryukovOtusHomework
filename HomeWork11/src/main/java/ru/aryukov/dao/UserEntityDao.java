package ru.aryukov.dao;


import org.hibernate.query.Query;
import ru.aryukov.domain.UserEntity;
import ru.aryukov.util.HibernateUtil;

/**
 * Created by dev on 17.07.17.
 */
public class UserEntityDao extends CommonDaoImpl<UserEntity, Integer> {

    public UserEntity findByName(String name) {
        UserEntity userEntity = null;
        Query query = HibernateUtil.getSession().createQuery("from UserEntity where name = :name").setParameter("name", name);
        userEntity = findOne(query);
        return userEntity;
    }
}
