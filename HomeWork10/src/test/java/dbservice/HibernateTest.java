package dbservice;

import org.junit.Test;
import ru.aryukov.dao.UserAddressEntityDao;
import ru.aryukov.dao.UserEntityDao;
import ru.aryukov.dao.UserPhoneEntityDao;
import ru.aryukov.dbservices.DbServices;
import ru.aryukov.dbservices.DbServicesImpl;
import ru.aryukov.domain.UserAddressEntity;
import ru.aryukov.domain.UserEntity;
import ru.aryukov.domain.UserPhoneEntity;
import ru.aryukov.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dev on 17.07.17.
 */
public class HibernateTest {

    @Test
    public void save() throws Exception {

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

        UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.setIndex(11);
        userAddressEntity.setStreet("Kultury street");
        userEntity.setUserAddressEntity(userAddressEntity);


        HibernateUtil.getSession().save(userEntity);
        HibernateUtil.getSession().getTransaction().commit();

        HibernateUtil.getSession().beginTransaction();
        List<UserEntity> userEntityList = userEntityDao.findAll(UserEntity.class);
        UserEntity factUser = userEntityList.get(0);



        assertEquals(userEntity.getName(), factUser.getName());
        assertEquals(userEntity.getAge(), factUser.getAge());

        assertEquals(userEntity.getUserAddressEntity().getIndex(), factUser.getUserAddressEntity().getIndex());
        assertEquals(userEntity.getUserAddressEntity().getStreet(), factUser.getUserAddressEntity().getStreet());

        assertEquals(userEntity.getUserPhoneEntityList().size(), factUser.getUserPhoneEntityList().size());
        assertEquals( userEntity.getUserPhoneEntityList().get(0), factUser.getUserPhoneEntityList().get(0));
    }
}
