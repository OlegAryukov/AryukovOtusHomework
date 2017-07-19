package ru.aryukov;

import ru.aryukov.dao.UserAddressEntityDao;
import ru.aryukov.dao.UserEntityDao;
import ru.aryukov.dao.UserPhoneEntityDao;
import ru.aryukov.domain.UserAddressEntity;
import ru.aryukov.domain.UserEntity;
import ru.aryukov.domain.UserPhoneEntity;
import ru.aryukov.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 17.07.17.
 */
public class AppMain {
    public static void main(String[] args) {
        //Session session = DbServicesImpl.getSessionFactory().openSession();
        UserEntityDao userEntityDao = new UserEntityDao();
        UserPhoneEntityDao userPhoneEntityDao = new UserPhoneEntityDao();
        UserAddressEntityDao userAddressEntityDao= new UserAddressEntityDao();

        HibernateUtil.getSession().beginTransaction();
        final UserEntity userEntity = new UserEntity();
        userEntity.setName("Oleg");
        userEntity.setAge(32);

        UserPhoneEntity userPhoneEntity = new UserPhoneEntity();
        userPhoneEntity.setNumber("987-654-32");
        userPhoneEntity.setCode(812);
        UserPhoneEntity userPhoneEntity1 = new UserPhoneEntity();
        userPhoneEntity1.setNumber("123-456-78");
        userPhoneEntity1.setCode(490);

        userEntity.addPhone(userPhoneEntity);
        userEntity.addPhone(userPhoneEntity1);

        HibernateUtil.getSession().save(userEntity);
        HibernateUtil.getSession().getTransaction().commit();

        HibernateUtil.getSession().beginTransaction();
        List<UserEntity> userEntityList = userEntityDao.findAll(UserEntity.class);
        UserEntity userEntity1 = userEntityList.get(0);


        System.out.println(userEntity1.getUserPhoneEntityList().get(0).toString());

        /*final UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.setIndex(11);
        userAddressEntity.setStreet("Kultury street");
        userAddressEntity.setUserEntity(userEntity1);

        HibernateUtil.getSession().save(userAddressEntity);
        HibernateUtil.getSession().getTransaction().commit();*/

    }
}
