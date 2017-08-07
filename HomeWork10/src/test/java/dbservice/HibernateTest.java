package dbservice;

import org.junit.Before;
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

    @Before
    public void prepeareTable(){
        UserEntityDao ued = new UserEntityDao();
        UserPhoneEntityDao uped = new UserPhoneEntityDao();
        UserAddressEntityDao uaed = new UserAddressEntityDao();

        HibernateUtil.getSession().beginTransaction();
        List<UserPhoneEntity> phoneEntities = uped.findAll(UserPhoneEntity.class);
        if(!phoneEntities.isEmpty()){
            for (UserPhoneEntity upe : phoneEntities) {
                uped.delete(upe);
            }
        }
        HibernateUtil.getSession().getTransaction().commit();
        HibernateUtil.getSession().close();

        HibernateUtil.getSession().beginTransaction();
        List<UserEntity> userEntityList = ued.findAll(UserEntity.class);
        if(!userEntityList.isEmpty()){
            for (UserEntity ue : userEntityList) {
                ued.delete(ue);
            }
        }
        HibernateUtil.getSession().getTransaction().commit();
        HibernateUtil.getSession().close();

        HibernateUtil.getSession().beginTransaction();
        List<UserAddressEntity> addressEntities = uaed.findAll(UserAddressEntity.class);
        if(!addressEntities.isEmpty()){
            for (UserAddressEntity uae : addressEntities) {
                uaed.delete(uae);
            }
        }
        HibernateUtil.getSession().getTransaction().commit();
        HibernateUtil.getSession().close();

    }

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
        UserEntity userEntity1 = userEntityList.get(0);


        System.out.println(userEntity1.getUserPhoneEntityList().get(0).toString());



        assertEquals(userEntity1.getName(), userEntity.getName());
        assertEquals(userEntity1.getAge(), userEntity.getAge());

        assertEquals(userEntity1.getUserAddressEntity().getIndex(), userAddressEntity.getIndex());
        assertEquals(userEntity1.getUserAddressEntity().getStreet(), userAddressEntity.getStreet());

        assertEquals(userEntity.getUserPhoneEntityList().size(), 2);
        assertEquals( userEntity.getUserPhoneEntityList().get(0), userPhoneEntity);
    }

}
