package ru.aryukov;

import ru.aryukov.dao.UserEntityDao;
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
        UserEntity user = new UserEntity();
        user.setName("Oleg");
        user.setAge(32);

        UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.setStreet("Prosvet");
        userAddressEntity.setIndex(11);

        List<UserPhoneEntity> phoneEntities = new ArrayList<>();
        UserPhoneEntity userPhoneEntity = new UserPhoneEntity();
        userPhoneEntity.setNumber("987-654-32");
        userPhoneEntity.setCode(812);
        userPhoneEntity.setUser(user);
        phoneEntities.add(userPhoneEntity);

        user.setUserAddressEntity(userAddressEntity);
        user.setUserPhoneEntity(phoneEntities);

        //HibernateUtil.beginTransaction();
        userEntityDao.save(user);
        UserEntity userEntity = userEntityDao.findByName("Oleg");
        System.out.println(userEntity.toString());
        //HibernateUtil.commitTransaction();
    }
}
